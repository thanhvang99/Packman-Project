package Entity;

import edu.princeton.cs.algs4.Stack;
import org.lwjgl.util.Rectangle;

public class Ghost extends Entity{
    private Stack<Integer> shortestPath;

    public Ghost(int row,int column,String keyName,int numberOfFrames,int fps,String format){
        super(row,column,keyName,numberOfFrames,fps,format,TYPE.GHOST);
        shortestPath = new Stack<Integer>();
    }

    public Stack<Integer> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(Stack<Integer> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }
}

