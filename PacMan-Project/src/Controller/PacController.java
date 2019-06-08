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
        receiveInput();
        move(e);
        if( !isInWindow(e) ){
            e.setX_pixel(e.getPreviousX_pixel());
            e.setY_pixel(e.getPreviousY_pixel());
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

