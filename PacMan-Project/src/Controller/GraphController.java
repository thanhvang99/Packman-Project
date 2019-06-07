package Controller;

import Entity.*;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;

public class GraphController extends ObjectController{
    private BreathFirstPath bfp;
    private boolean isPacMove = false;
    private Graph graph;
    private ArrayList<Ghost> listGhosts;
    private Entity pac;
    private int previousRow,previousColumn;

    public GraphController(Graph graph, ArrayList<Ghost> list, Entity pac){
        this.graph = graph;
        this.listGhosts = list;
        this.pac = pac;
        GameObject.addController(this);
    }

    @Override
    public void update() {
        try {
            check(pac,listGhosts);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("check");
        }
        if( isPacMove ){
            updateShortestPath();
            isPacMove = false;
        }

    }
    // check collision && update new Position
    public void check(GameObject o,ArrayList<Ghost> ghosts) throws ArrayIndexOutOfBoundsException{
        Node[][] nodes = graph.getNodes();
        int row = o.getRow();
        int column = o.getColumn();

        // Check PacMan vs Maze
        for(int i=row-1;i<=row+1;i++){
            for(int j=column-1;j<=column+1;j++){
                if( i!=row || j!=column ){
                    if( i>=0 && i<graph.getRow() && j>=0 &&j<graph.getColumn() ) {
                        if (nodes[i][j].getRect().intersects(o.getRect())) {
                            switch (nodes[i][j].getType()) {
                                case WALL:
                                    o.setX_pixel(o.getPreviousX_pixel());
                                    o.setY_pixel(o.getPreviousY_pixel());
                                    break;
                                case DOT:
                                case NORMAL:
                                    GameObject nextObject = nodes[i][j];
                                    GameObject currentObject = o;
                                    if (nextObject.getRect().contains(currentObject.getRect())) {
                                        // Update dot --> normal
                                        nextObject.setType(GameObject.TYPE.NORMAL);

                                        // Update possition
                                        isPacMove = true;
                                        currentObject.setColumn(nextObject.getColumn());
                                        currentObject.setRow(nextObject.getRow());
                                        break;
                                    }
                            }
                        }
                    }
                }
            }
        }
        // check ghost && update possition
        for(Ghost g : ghosts) {
            row = g.getRow();
            column = g.getColumn();
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    if (i != row || j != column) {
                        if (i >= 0 && i < graph.getRow() && j >= 0 && j < graph.getColumn()) {
                            if (nodes[i][j].getRect().intersects(g.getRect())) {
                                switch (nodes[i][j].getType()) {
                                    case WALL:
                                    case DOT:
                                    case NORMAL:
                                        GameObject nextObject = nodes[i][j];
                                        GameObject currentObject = g;
                                        // Update possition
                                        if (nextObject.getRect().contains(currentObject.getRect())) {
                                            currentObject.setColumn(nextObject.getColumn());
                                            currentObject.setRow(nextObject.getRow());
                                        }
                                        break;
                                }
                            }
                        }
                    }
                }
            }
            // check collise pac
            if( g.getColumn() == pac.getColumn() && g.getRow() == pac.getRow() ){
                g.setDirection(Entity.DIRECTION.STAND);
            }
        }

    }
    private void updateShortestPath(){
        for( Ghost g : listGhosts ){
            bfp = new BreathFirstPath(graph,new Node(g.getRow(),g.getColumn(), GameObject.TYPE.GHOST));
            g.setShortestPath(bfp.pathTo(new Node(pac.getRow(),pac.getColumn(), GameObject.TYPE.PAC)));
            for( Node node : g.getShortestPath() ){
//                System.out.print("-->"+node.getRow()+","+node.getColumn());
            }
        }
    }
}

