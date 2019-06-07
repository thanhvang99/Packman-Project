package Entity;

import Controller.AnimationController;
import Controller.ObjectController;
import org.lwjgl.util.Rectangle;

import java.util.ArrayList;

public abstract class Entity extends DrawableObject{
    // Default value for width,height = 25;
    private Animation left,up,down,right;

    private int speed = 3;
    public enum DIRECTION{
        LEFT,UP,RIGHT,DOWN,STAND;
    }
    private DIRECTION direction = DIRECTION.LEFT;


    public Entity(int row,int column,String keyName,int numberOfFrame,int fps,String format,GameObject.TYPE type){
        super(row,column,25,25,type);

        // Set up default
        setAnimation(keyName,numberOfFrame,fps,format);

    }
    public void setAnimation(String keyName,int numberOfFrame,int fps,String format){
        ArrayList<Animation> animations = new ArrayList<Animation>();
        left = new Animation(numberOfFrame,fps,keyName+"_left",format);
        right = new Animation(numberOfFrame,fps,keyName+"_right",format);
        up= new Animation(numberOfFrame,fps,keyName+"_up",format);
        down= new Animation(numberOfFrame,fps,keyName+"_down",format);

        animations.add(left);
        animations.add(right);
        animations.add(up);
        animations.add(down);

        ObjectController controller = new AnimationController(animations);
    }

    public Animation getAnimation() {
        switch(direction){
            case LEFT: return left;
            case RIGHT: return right;
            case UP: return up;
            case DOWN: return down;
            default:
                return null;
        }
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
