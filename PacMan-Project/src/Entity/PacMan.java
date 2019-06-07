package Entity;

import Controller.StateController;
import org.lwjgl.util.Rectangle;

public class PacMan extends Entity{
    public PacMan(int row, int column, String keyName, int numberOfFrame, int fps, String format) {
        super(row, column, keyName, numberOfFrame, fps, format, TYPE.PAC);
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(getX_pixel()+5,getY_pixel()+5,getWidth()-10,getHeight()-10);
    }
}


