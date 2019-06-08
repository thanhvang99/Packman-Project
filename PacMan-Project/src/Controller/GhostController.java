package Controller;


import App.GameService;
import Entity.*;

import java.security.Provider;
import java.util.ArrayList;

public class GhostController extends EntityController{
    private long lastTime,currentTime,delta;
    private Graph graph;
    private ArrayList<Ghost> list;

    public GhostController(ArrayList<Ghost> list, Graph g){
        this.list = list;
        GameObject.addController(this);
        this.graph = g;
        delta = 0;
        currentTime = 0;
        lastTime = GameService.getTimeInMillisecond();

    }
    public void findWayToLive(){
        for( Ghost g : list ){
            Node node = g.getPreviousNode();
            g.setDirection(Entity.DIRECTION.DOWN);
            System.out.println(g.getDirection().RandomizeDirection());
        }

    }
    public void findWayToLiveNew(){
        for( Ghost g : list ){

            g.setDirection(g.getDirection().RandomizeDirection());
            g.setPreviousX_pixel(g.getX_pixel());
            g.setPreviousY_pixel(g.getY_pixel());

            move(g);
            if (g.collisedWithWall) {
                g.setX_pixel(g.getPreviousY_pixel());
                g.setY_pixel(g.getPreviousY_pixel());
                g.collisedWithWall = false;
            }
        }
    }
    public void findShortestPath() {
        for( Ghost g : list ){
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
//        currentTime = GameService.getTimeInMillisecond();
//        delta += currentTime - lastTime;
//        if( delta >= 10000 ) {
//            delta = 0;
            findWayToLive();
//        }
//        findShortestPath();
//        findWayToLiveNew();
//        lastTime = currentTime;
    }
}
