package App;

import Controller.BreathFirstPath;
import Controller.GhostController;
import Controller.GraphController;
import Controller.PacController;
import Entity.*;
import UI.UIEntity;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Main {
    public static void main ( String [] args){
        setUpWindow();
        setUpOpenGL();
        init();
        loopGame();
    }
    private void test(){

    }
    private static void init(){
        ArrayList<Ghost> listOfGhosts = new ArrayList<Ghost>();

        Entity pac = new PacMan(3,3,"pac",11,22,"gif");
        Entity ghost = new Ghost(0,4,"ghost",3,3,"gif");

        listOfGhosts.add((Ghost)ghost);

        Graph graph = new Graph("maze.txt");



        new UIEntity(pac,listOfGhosts,graph);
        new PacController(pac);
        new GraphController(graph,listOfGhosts,pac);
        new GhostController(listOfGhosts);

    }
    private static void setUpWindow(){
        try {
            Display.setDisplayMode(new DisplayMode(530,670));
            Display.setTitle("PacMan");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
    private static void setUpOpenGL(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,530,670,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);

    }
    private static void loopGame(){
        while(!Display.isCloseRequested()){
            glClear(GL_COLOR_BUFFER_BIT);
            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
                Display.destroy();
                System.exit(1);
            }

            GameObject.notifyController();
            GameObject.notifyUI();
            Display.update();
            Display.sync(60);

        }

    }
}
