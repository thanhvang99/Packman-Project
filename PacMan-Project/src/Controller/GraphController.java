package Controller;

import Entity.GameObject;
import Entity.Graph;
import Entity.Node;

import java.util.ArrayList;

public class GraphController extends ObjectController {
    private int previousX_pixel,previousY_pixel;
    private Graph graph;
    private ArrayList<GameObject> listGameObject;

    public GraphController(Graph graph,ArrayList<GameObject> list){
        this.graph = graph;
        this.listGameObject = list;
        GameObject.addController(this);
    }

    @Override
    public void update() {
        for( GameObject o : listGameObject ) {
            try {
                checkCollise(o);
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }
    }
    public void checkCollise(GameObject o) throws ArrayIndexOutOfBoundsException{
        Node[][] nodes = graph.getNodes();
        int row = o.getRow();
        int column = o.getColumn();
        for(int i=row-1;i<=row+1;i++){
            for(int j=column-1;j<=column+1;j++){
                if( i!=row || j!=column ){
                    if( nodes[i][j].getRect().intersects(o.getRect())){
                        switch(nodes[i][j].getType()){
                            case WALL :
                                o.setX_pixel(o.getPreviousX_pixel());
                                o.setY_pixel(o.getPreviousY_pixel());
                                break;
                            case DOT:
                                o.setType(GameObject.TYPE.NORMAL);
                            case NORMAL:
                                updatePossition(o,nodes[i][j]);
                                System.out.println(o.getRow()+","+o.getColumn());
                                break;
                        }
                    }
                }
            }
        }
    }
    public void updatePossition(GameObject currentObject,GameObject nextObject ){
        if( nextObject.getRect().contains(currentObject.getRect()) ){
            currentObject.setColumn(nextObject.getColumn());
            currentObject.setRow(nextObject.getRow());
        }
    }
}
