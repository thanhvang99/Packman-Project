package Controller;


import Entity.Entity;
import Entity.GameObject;
import Entity.Ghost;
import Entity.Node;

import java.util.ArrayList;

public class GhostController extends EntityController{
    private ArrayList<Ghost> list;

    public GhostController(ArrayList<Ghost> list){
        this.list = list;
        GameObject.addController(this);

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
    }
    @Override
    public void update() {
        findShortestPath();
    }
}
