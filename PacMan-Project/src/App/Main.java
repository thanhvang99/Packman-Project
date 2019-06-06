package App;

import Controller.EntityController;
import Controller.ObjectController;
import Entity.Entity;
import Entity.Game;
import Entity.PacMan;
import UI.UIEntity;
import UI.UIState;
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
    private static void init(){
        ArrayList<Entity> list = new ArrayList<Entity>();
        Entity pac = new PacMan(3,3,"pac",11,15,"gif");
        list.add(pac);
        UIEntity ui = new UIEntity(list);
        ObjectController controller = new EntityController(pac);
    }
    private static void setUpWindow(){
        try {
            Display.setDisplayMode(new DisplayMode(550,700));
            Display.setTitle("PacMan");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
    private static void setUpOpenGL(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,550,700,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);

    }
    private static void loopGame(){
        while(!Display.isCloseRequested()){
            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
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
