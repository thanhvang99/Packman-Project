package Entity;

import Controller.AnimationController;
import edu.princeton.cs.algs4.Stack;
import org.lwjgl.util.Rectangle;

import java.util.ArrayList;

public class Ghost extends Entity{
    public enum STATE{
        NORMAL,SCARY;
    }
    private Stack<Node> shortestPath;
    private Animation normal,scarry;
    private boolean isUpdatePath = true;
    private STATE state;


    public Ghost(int row,int column,String keyName,int numberOfFrames,int fps,String format){
        super(row,column,keyName,numberOfFrames,fps,format,TYPE.GHOST);
        shortestPath = new Stack<Node>();
        state = STATE.NORMAL;
        setAnimation();
    }

    public Stack<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(Stack<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(getX_pixel(),getY_pixel(),getWidth(),getHeight());
    }
    public void setAnimation(){
        normal = new Animation(3,3,"ghost","gif");
        scarry = new Animation( 6,6,"newDie","gif" );
        ArrayList<Animation> animations = new ArrayList<Animation>();
        animations.add(normal);
        animations.add(scarry);
        new AnimationController(animations);
    }

    @Override
    public void setAnimation(String keyName, int numberOfFrames, int fps, String format) {

    }

    @Override
    public Animation getAnimation(){return state == STATE.NORMAL ? normal : scarry;}

    public boolean isUpdatePath() {
        return isUpdatePath;
    }

    public void setUpdatePath(boolean updatePath) {
        isUpdatePath = updatePath;
    }

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;

    }
}

