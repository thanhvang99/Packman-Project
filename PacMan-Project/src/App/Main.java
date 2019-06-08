package App;

import Controller.GhostController;
import Controller.GraphController;
import Controller.PacController;
import Entity.*;
import UI.UIGame;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Main {
    private static TrueTypeFont font;
    public static void main ( String [] args){
        setUpWindow();
        newSetUpGL();
        init();
        loopGame();
    }
    private void test(){

    }
    private static void init(){
        ArrayList<Ghost> listOfGhosts = new ArrayList<Ghost>();

        Entity pac = new PacMan(3,3,"pac",11,22,"gif");
        Entity ghost = new Ghost(0,4,"ghost",3,3,"gif");
        Entity ghost1 = new Ghost(0,16,"ghost",3,3,"gif");
        Entity ghost2 = new Ghost(21,16,"ghost",3,3,"gif");
        Entity ghost3 = new Ghost(19,3,"ghost",3,3,"gif");
        pac.setSpeed(2);
        ghost.setSpeed(1);
        ghost1.setSpeed(1);




        listOfGhosts.add((Ghost)ghost);
//        listOfGhosts.add((Ghost)ghost1);
//        listOfGhosts.add((Ghost)ghost2);
//        listOfGhosts.add((Ghost)ghost3);

        Graph graph = new Graph("maze.txt");




        new UIGame(pac,listOfGhosts,graph);
        new PacController(pac);
        new GraphController(graph,listOfGhosts,pac);
        new GhostController(listOfGhosts,graph);

    }
    private static void setUpWindow(){
        try {
            Display.setDisplayMode(new DisplayMode(530,700));
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
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);

    }
    private static void newSetUpGL(){
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glClearDepth(1);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glViewport(0,0,530,700);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 530, 700, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);


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
