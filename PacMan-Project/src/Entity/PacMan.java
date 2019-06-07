package Entity;

import Controller.AnimationController;
import Controller.ObjectController;
import Controller.StateController;
import org.lwjgl.util.Rectangle;

import java.util.ArrayList;

public class PacMan extends Entity{
    private Animation left,right,up,down;
    public PacMan(int row, int column, String keyName, int numberOfFrame, int fps, String format) {
        super(row, column, keyName, numberOfFrame, fps, format, TYPE.PAC);
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(getX_pixel()+5,getY_pixel()+5,getWidth()-10,getHeight()-10);
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
        switch(getDirection()){
            case LEFT: return left;
            case RIGHT: return right;
            case UP: return up;
            case DOWN: return down;
            default:
                return null;
        }
    }
}


