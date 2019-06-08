package Entity;

import Controller.AnimationController;
import Controller.ObjectController;
import org.lwjgl.util.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public abstract class Entity extends DrawableObject{
    // Default value for width,height = 25;
    private int speed = 2;
    public enum DIRECTION{
        LEFT,UP,RIGHT,DOWN,STAND;
        public DIRECTION RandomizeDirection(){
            Random r = new Random();
            switch(r.nextInt(4)){
                case 0: return LEFT;
                case 1: return UP;
                case 2: return DOWN;
                case 3: return RIGHT;
            }
            return null;

        }
    }
    private DIRECTION direction = DIRECTION.LEFT;


    public Entity(int row,int column,String keyName,int numberOfFrame,int fps,String format,GameObject.TYPE type){
        super(row,column,25,25,type);

        setAnimation(keyName,numberOfFrame,fps,format);

    }

    public DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    // Default node_size_width = 30,node_size_height = 30;
    public void setSpeed(int speed){this.speed = speed;}

    public int getSpeed() {
        return speed;
    }

}
