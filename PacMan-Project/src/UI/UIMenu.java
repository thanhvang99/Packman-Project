package UI;

import Controller.ButtonController;
import Entity.Animation;
import Entity.Button;
import Entity.State;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;
import static org.newdawn.slick.opengl.renderer.SGL.GL_QUADS;

public class UIMenu extends UIState {

    private Animation background;

    private ButtonController startController;
    private ButtonController introductionController;
    private ButtonController exitController;

//    private UIButton uiStart;
//    private UIButton uiIntro;
//    private UIButton uiStart;


    public UIMenu(int numberOfFrames, String keyName, int fps, String format) {
        background = new Animation(numberOfFrames, fps, keyName, format);
        State.setCurrentUI(this);
        init();

    }

    private void init() {
        startController = new ButtonController(new Button(0, 0, 550, 50));
        introductionController = new ButtonController(new Button(0, 650, 550, 50));
//        exitController = new ButtonController(new Button(150, 200, 200, 200));
    }

    @Override
    public void render() {
        background.getFrames()[background.getPointer()].bind();
        drawBackground();
        Button.notifyButtonController();

        if (startController.isClicked()) {
            State
        }
    }

    public void drawBackground() {
//        System.out.println(Mouse.isButtonDown(0));

        glBegin(GL_QUADS);

        int x = 400;

        glTexCoord2d(0, 0);
        glVertex2f(0, 0);
        glTexCoord2d(1, 0);
        glVertex2i(Display.getWidth() + 90, 0);
        glTexCoord2d(1, 1);
        glVertex2i(Display.getWidth(), Display.getHeight() + x);
        glTexCoord2d(0, 1);
        glVertex2i(0, Display.getHeight() + x);
        glEnd();
    }
}
