package App;

import Controller.EntityController;
import Controller.ObjectController;
import Entity.DrawableObject;
import Entity.Game;
import Entity.Graph;
import Entity.PacMan;
import UI.UIEntity;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Main {
    public static void main ( String [] args) {
        setUpWindow();
        setUpOpenGL();
        init();
        loopGame();
    }

    private static void init() {
        ArrayList<DrawableObject> list = new ArrayList<DrawableObject>();
        DrawableObject pac = new PacMan(3,3,"pac",11,22,"gif");
        list.add(pac);
        Graph graph = new Graph("maze.txt");
        UIEntity ui = new UIEntity(list, graph);
    }

    private static void setUpWindow() {
        try {
            Display.setDisplayMode(new DisplayMode(550,700));
            Display.setTitle("PacMan");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private static void setUpOpenGL() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,550,700,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
    }

    private static void loopGame() {
        while(!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);
            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                Display.destroy();
                System.exit(1);
            }

            Game.notifyController();
            Game.notifyUI();
            Display.update();
            Display.sync(60);

        }

    }
}