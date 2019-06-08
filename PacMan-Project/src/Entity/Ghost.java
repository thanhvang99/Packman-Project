package Entity;

import edu.princeton.cs.algs4.Stack;
import org.lwjgl.util.Rectangle;

public class Ghost extends Entity{
    private Stack<Node> shortestPath;
    private Animation normal;
    private boolean isUpdatePath = true;

    public Ghost(int row,int column,String keyName,int numberOfFrames,int fps,String format){
        super(row,column,keyName,numberOfFrames,fps,format,TYPE.GHOST);
        shortestPath = new Stack<Node>();
    }

    public Stack<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(Stack<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(getX_pixel()+5,getY_pixel()+5,getWidth()-10,getHeight()-10);
    }

    @Override
    public void setAnimation(String keyName, int numberOfFrame, int fps, String format) {
        normal = new Animation(numberOfFrame,fps,keyName,format);
    }
    @Override
    public Animation getAnimation(){return normal;}

    public boolean isUpdatePath() {
        return isUpdatePath;
    }

    public void setUpdatePath(boolean updatePath) {
        isUpdatePath = updatePath;
    }

}

