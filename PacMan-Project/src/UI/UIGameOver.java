package UI;

import Entity.Animation;
import Helper.Button;
import Entity.State;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;
import static org.newdawn.slick.opengl.renderer.SGL.GL_QUADS;

public class UIGameOver extends UIState {

    private Animation background ;

    private Button playAgain;

    public UIGameOver(int numberOfFrames, String keyName, int fps, String format ) {
        background = new Animation (numberOfFrames,fps,keyName,format);
        State.setCurrentUI(this);
        init();
    }

    public void init(){
        playAgain = new Button(0,0,550,700);
    }

    @Override
    public void render() {
        background.getFrames()[background.getPointer()].bind();
        drawBackground();
    }

    public void drawBackground(){
        glBegin(GL_QUADS);
        glTexCoord2d(0, 0);
        glVertex2i(0, 0);
        glTexCoord2d(1, 0);
        glVertex2i(Display.getWidth()+200, 0);
        glTexCoord2d(1, 1);
        glVertex2i(Display.getWidth() +200, Display.getHeight()+50 );
        glTexCoord2d(0, 1);
        glVertex2i(0, Display.getHeight() +50);
        glEnd();
    }

    public Button getPlayAgainButton() {
        return playAgain;
    }
}
