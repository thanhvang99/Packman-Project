import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;


public class Main {
    private static PacMan pac ;
    public static void main(String[] args){
        try {
            Display.setDisplayMode(new DisplayMode(640,640));
            Display.setTitle("Entities");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,640,640,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        pac = new PacMan(100,100,PacMan.DIRECTION.LEFT);

        // Loop for game
        while(!Display.isCloseRequested()){
            glClear(GL_COLOR_BUFFER_BIT);

            pac.draw();
            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
                Display.destroy();
                System.exit(1);

            }
            checkInput();
            pac.update();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();

    }
    public static void checkInput(){
        while(Keyboard.next()) {
            pac.setSpeed(5);
            if (Keyboard.getEventKey() == Keyboard.KEY_UP && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                pac.setDirection(PacMan.DIRECTION.UP);
            } else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                pac.setDirection(PacMan.DIRECTION.DOWN);
            } else if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                pac.setDirection(PacMan.DIRECTION.LEFT);
            } else if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                pac.setDirection(PacMan.DIRECTION.RIGHT);
            }
        }
    }

}

