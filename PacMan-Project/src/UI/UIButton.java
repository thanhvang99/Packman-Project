package UI;

import Entity.Animation;
import Entity.Button;

import static org.lwjgl.opengl.GL11.*;

public class UIButton {

    private Animation texture;
    private Button button;

    public UIButton(Button button, int numberOfFrames, String keyName, int fps, String format) {
        this.button = button;
        texture = new Animation(numberOfFrames, fps, keyName, format);
    }

    public void render() {
        texture.getFrames()[texture.getPointer()].bind();
        drawButton();
    }

    public void drawButton() {
        glTexCoord2d(0, 0);
        glVertex2d(button.getxPosition(), button.getyPosotion());
        glTexCoord2d(1, 0);
        glVertex2i(button.getxPosition() + button.getWidth(), button.getyPosotion());
        glTexCoord2d(1, 1);
        glVertex2i(button.getxPosition() + button.getWidth(), button.getyPosotion() + button.getHeight());
        glTexCoord2d(0, 1);
        glVertex2i(button.getxPosition() + button.getHeight(), button.getyPosotion());
        glEnd();
    }
}
