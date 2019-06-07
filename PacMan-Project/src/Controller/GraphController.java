package Controller;

import Entity.*;

import java.util.ArrayList;

public class GraphController extends ObjectController{
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
                checkCollise(pac);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("check");
            }

        }
    public void checkCollise(GameObject o) throws ArrayIndexOutOfBoundsException{
        Node[][] nodes = graph.getNodes();
        int row = o.getRow();
        int column = o.getColumn();
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
    }
}
