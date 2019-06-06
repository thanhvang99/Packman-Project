package Entity;

import Controller.AnimationController;
import Controller.ObjectController;
import org.lwjgl.util.Rectangle;

import java.util.ArrayList;

public class Entity extends Game{
    private int width,height;
    private int row,column;
    private Animation left,up,down,right;
    public enum DIRECTION{
        LEFT,UP,RIGHT,DOWN;
    }
    private DIRECTION direction = DIRECTION.LEFT;

    public Entity(int row,int column,String keyName,int numberOfFrame,int fps,String format){
        this.row = row;
        this.column = column;

        // Set up default
        width = 25;
        height = 25;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    // Default node_size_width = 30,node_size_height = 30;
    public Rectangle getRect(){
        return new Rectangle(column*30,row*30,width,height);
    }

}
