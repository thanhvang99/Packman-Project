package UI;

import Entity.Animation;
import Entity.State;

import static org.lwjgl.opengl.GL11.*;
import static org.newdawn.slick.opengl.renderer.SGL.GL_QUADS;

public class UIGameOver extends UIState {

    private Animation background ;
    private State state;

    public UIGameOver(State state,int numberOfFrames, String keyName, int fps, String format ) {
        this.state = state;
        background = new Animation (numberOfFrames,fps,keyName,format);
        state.setCurrentUI(this);
    }

    @Override
    public void render() {
        background.getFrames()[background.getPointer()].bind();
        drawBackground();

//        if ( !state.isOn() ){
//            state.setCurrentUI(new UIEntity(state));
//
//        }

    }
    public void drawBackground(){
        glBegin(GL_QUADS);
        glTexCoord2d(0, 0);
        glVertex2i(0, 0);
        glTexCoord2d(1, 0);
        glVertex2i(300, 0);
        glTexCoord2d(1 , 1);
        glVertex2i(300, 300);
        glTexCoord2d(0, 1);
        glVertex2i(0, 300);
        glEnd();
    }
}
