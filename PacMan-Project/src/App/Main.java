package App;

import Controller.MenuStateController;
import Entity.GameObject;
import Entity.State;
import UI.UIMenu;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

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
//        ArrayList<Ghost> listOfGhosts = new ArrayList<Ghost>();
//
//        Entity pac = new PacMan(3,3,"pac",11,22,"gif");
//        Entity ghost = new Ghost(0,4,"ghost",3,3,"gif");
//        Entity ghost1 = new Ghost(19,0,"ghost",3,3,"gif");
//        pac.setSpeed(2);
//        ghost.setSpeed(1);
//        ghost1.setSpeed(1);
//
//
//        listOfGhosts.add((Ghost)ghost);
//        listOfGhosts.add((Ghost)ghost1);
//
//        Graph graph = new Graph("maze.txt");
//
//
//
//        new UIEntity(pac,listOfGhosts,graph);
//        new PacController(pac);
//        new GraphController(graph,listOfGhosts,,pac);
//        new GhostController(listOfGhosts);

        UIMenu uiMenu = new UIMenu(1,"menu",1,"jpg");
        MenuStateController menuStateController = new MenuStateController(uiMenu);
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
            State.notifyUI();
            State.notifyStateController();
            Display.update();
            Display.sync(60);

        }

    }
}

