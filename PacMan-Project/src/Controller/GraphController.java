package Controller;

import Entity.*;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.Random;

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
        updateShortestPath();
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
                                    Random r = new Random();
                                    GameObject nextObject = nodes[i][j];
                                    GameObject currentObject = o;
                                    if (nextObject.getRect().contains(currentObject.getRect())) {
                                        if( nextObject.getType() == GameObject.TYPE.DOT ){
                                            GameObject.setScore(GameObject.getScore() + r.nextInt(10));
                                        }
                                        // Update dot --> normal
                                        nextObject.setType(GameObject.TYPE.NORMAL);

                                        // Update possition
                                        currentObject.setColumn(nextObject.getColumn());
                                        currentObject.setRow(nextObject.getRow());
                                    }
                            }
                        }
                    }
                }
            }
        }
        // Check Ghost && update possition
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
                                        g.setX_pixel(g.getPreviousX_pixel());
                                        g.setY_pixel(g.getPreviousY_pixel());
                                        break;
                                    case DOT:
                                    case NORMAL:
                                        GameObject nextObject = nodes[i][j];
                                        GameObject currentObject = g;
                                        // Update possition
                                        if (nextObject.getRect().contains(currentObject.getRect())) {
                                            g.setUpdatePath(true);
                                            currentObject.setColumn(nextObject.getColumn());
                                            currentObject.setRow(nextObject.getRow());
                                            g.setDirection(g.getDirection().RandomizeDirection());
                                        }
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
            if( g.isUpdatePath() ) {
                bfp = new BreathFirstPath(graph, new Node(g.getRow(), g.getColumn(), GameObject.TYPE.GHOST));
                g.setShortestPath(bfp.pathTo(new Node(pac.getRow(), pac.getColumn(), GameObject.TYPE.PAC)));
                for (Node node : g.getShortestPath()) {
//                System.out.print("-->"+node.getRow()+","+node.getColumn());
                }
                g.setUpdatePath(false);
            }
        }
    }


}

