package Controller;

import Entity.Entity;
import Entity.GameObject;
import org.lwjgl.input.Keyboard;

public class PacController extends EntityController{
    private Entity e;

    public PacController(Entity e){
        this.e = e;
        GameObject.addController(this);
    }
    @Override
    public void update() {
        move();
    }

    @Override
    public void move() {
        e.setPreviousX_pixel(e.getX_pixel());
        e.setPreviousY_pixel(e.getY_pixel());
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
}

