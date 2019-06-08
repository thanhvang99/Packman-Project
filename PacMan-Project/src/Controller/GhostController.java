package Controller;


import Entity.Entity;
import Entity.GameObject;
import Entity.Ghost;
import Entity.Node;
import org.lwjgl.Sys;

import java.util.ArrayList;

public class GhostController extends EntityController{
    private ArrayList<Ghost> list;
    private long currentTime,lastTime,delta;

    public GhostController(ArrayList<Ghost> list){
        currentTime = 0;
        delta = 0;
        this.list = list;
        GameObject.addController(this);

    }
    public void findWayToLive(){
        for( Ghost g : list ){
            move(g);
            if( !isInWindow(g) ){
                g.setX_pixel(g.getPreviousX_pixel());
                g.setY_pixel(g.getPreviousY_pixel());
            }else{

            }

        }

    }
    public void findShortestPath(Ghost g) {
            if( !g.getShortestPath().isEmpty() ){
                Node nextNode = g.getShortestPath().peek();
                Node currentNode = new Node(g.getRow(),g.getColumn(), GameObject.TYPE.GHOST);
                if( nextNode.getRow() == currentNode.getRow() && nextNode.getColumn() == currentNode.getColumn() ){
                    guessNextMove(g);
                }
            }else{
                g.setDirection(Entity.DIRECTION.STAND);
            }
            move(g);
    }
    private void guessNextMove(Ghost g){
        g.getShortestPath().pop();
        if( !g.getShortestPath().isEmpty() ){
            Node nextNode = g.getShortestPath().peek();
            Node currentNode = new Node(g.getRow(),g.getColumn(), GameObject.TYPE.GHOST);
            moveNext(currentNode,nextNode,g);
        }
    }
    private void moveNext(Node currentNode,Node nextNode,Ghost g){
        if( nextNode.getRow() > currentNode.getRow() ){
            g.setDirection(Entity.DIRECTION.DOWN);
        }else if( nextNode.getRow() < currentNode.getRow() ){
            g.setDirection(Entity.DIRECTION.UP);
        }else if( nextNode.getColumn() > currentNode.getColumn() ){
            g.setDirection(Entity.DIRECTION.RIGHT);
        }else if( nextNode.getColumn() < currentNode.getColumn() ){
            g.setDirection(Entity.DIRECTION.LEFT);
        }

    }
    @Override
    public void update() {
        for( Ghost g : list ){
            if( !g.isDied() ) {
                lastTime = Sys.getTime();
                if (g.getState() != Ghost.STATE.SCARY) {
                    findShortestPath(g);
                }
            }else{
                currentTime = Sys.getTime();
                delta += (currentTime-lastTime);
                if( delta >= 3000 ){
                    delta = 0;
                    g.setDied(false);
                    g.setState(Ghost.STATE.NORMAL);
                    g.setRow(8);
                    g.setColumn(8);
                    g.setX_pixel(g.getRow()*30);
                    g.setY_pixel(g.getColumn()*30);
                    g.setDirection(Entity.DIRECTION.RIGHT);
                }
                lastTime = currentTime;

            }

        }

    }
}
