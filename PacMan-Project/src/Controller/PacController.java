package Controller;

import Entity.Entity;
import Entity.GameObject;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;

public class PacController extends EntityController{
    private Entity e;

    public PacController(Entity e){
        this.e = e;
        GameObject.addController(this);
    }
    @Override
    public void update() {
        e.setPreviousX_pixel(e.getX_pixel());
        e.setPreviousY_pixel(e.getY_pixel());
        move();
        if( !isInWindow() ){
            e.setX_pixel(e.getPreviousX_pixel());
            e.setY_pixel(e.getPreviousY_pixel());
        }
    }

    @Override
    public void move() {
        receiveInput();
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
        }


    }
    private void receiveInput(){
        while(Keyboard.next()){
            if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
                e.setDirection(Entity.DIRECTION.LEFT);
            }else if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
                e.setDirection(Entity.DIRECTION.RIGHT);
            }else if(Keyboard.getEventKey() == Keyboard.KEY_UP && Keyboard.isKeyDown(Keyboard.KEY_UP)){
                e.setDirection(Entity.DIRECTION.UP);
            }else if(Keyboard.getEventKey() == Keyboard.KEY_DOWN && Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
                e.setDirection(Entity.DIRECTION.DOWN);
            }
        }
    }
    private boolean isInWindow(){
        return new Rectangle(10,10,Display.getWidth()-30,Display.getHeight()-20).contains(e.getRect());
    }
}

