package Controller;


import Entity.Entity;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;

public abstract class EntityController extends ObjectController{
    public void move(Entity e){
        e.setPreviousX_pixel(e.getX_pixel());
        e.setPreviousY_pixel(e.getY_pixel());
        switch (e.getDirection()) {
            case LEFT:
                e.setX_pixel(e.getX_pixel() - e.getSpeed());
                break;
            case RIGHT:
                e.setX_pixel(e.getX_pixel() + e.getSpeed());
                break;
            case UP:
                e.setY_pixel(e.getY_pixel() - e.getSpeed());
                break;
            case DOWN:
                e.setY_pixel(e.getY_pixel() + e.getSpeed());
                break;
            default:
                // Nothing
        }

    }
    public boolean isInWindow(Entity e){
        return new Rectangle(10,10, 530-30,670-20).contains(e.getRect());
    }


}
