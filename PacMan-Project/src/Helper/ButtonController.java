package Helper;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class ButtonController {
    private Button button;

    public ButtonController(Button button) {
        this.button = button;
        button.addButtonController(this);

    }

    public boolean isClicked() {
        if (Mouse.isButtonDown(0)
                && Mouse.getX() >= button.getxPosition()
                && Mouse.getX() <= button.getxPosition() + button.getWidth()
                && Mouse.getY() >= Display.getHeight() - button.getyPosotion() - button.getHeight()
                && Mouse.getY() <= Display.getHeight() - button.getyPosotion())
            return true;
        return false;
    }

    public void update() {
    }
}

