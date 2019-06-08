package Controller;

import Entity.*;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.Random;

public class GraphController extends ObjectController{
    private long currentTime,lastTime,delta;
    private BreathFirstPath bfp;
    private boolean isPacMove = false;
    private Graph graph;
    private ArrayList<Ghost> listGhosts;
    private Entity pac;
    private int previousRow,previousColumn;

    public GraphController(Graph graph, ArrayList<Ghost> list, Entity pac){
        lastTime = Sys.getTime();
        currentTime = 0;
        delta = 0;
        this.graph = graph;
        this.listGhosts = list;
        this.pac = pac;
        GameObject.addController(this);
    }

    @Override
    public void update() {
        check(pac,listGhosts);
        updateShortestPath();
        randomizeCherry(6);
    }
    // check collision && update new Position
    public void check(GameObject o,ArrayList<Ghost> ghosts) throws ArrayIndexOutOfBoundsException{
        checkGhost(ghosts,o);
        checkPac(ghosts,o);
    }
    public void checkPac(ArrayList<Ghost> ghosts,GameObject o){
        // Check PacMan vs Maze
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
                                case CHERRY:
                                case DOT:
                                case NORMAL:
                                    Random r = new Random();
                                    GameObject nextObject = nodes[i][j];
                                    GameObject currentObject = o;
                                    if (nextObject.getRect().contains(currentObject.getRect())) {
                                        if( nextObject.getType() == GameObject.TYPE.DOT ){
                                            GameObject.setScore(GameObject.getScore() + r.nextInt(10));
                                        }else if( nextObject.getType() == GameObject.TYPE.CHERRY ){
                                            for( Ghost g : ghosts ){
                                                g.setState(Ghost.STATE.SCARY);
                                            }
                                        }

                                        // Update dot,cherry --> normal
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

    }
    public void checkGhost(ArrayList<Ghost> ghosts,GameObject o){
        // Check Ghost && update possition
        Node[][] nodes = graph.getNodes();
        int row = o.getRow();
        int column = o.getColumn();

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
                                    case CHERRY:
                                        GameObject nextObject = nodes[i][j];
                                        GameObject currentObject = g;
                                        // Update possition
                                        if (nextObject.getRect().contains(currentObject.getRect())) {
                                            g.setUpdatePath(true);
                                            currentObject.setColumn(nextObject.getColumn());
                                            currentObject.setRow(nextObject.getRow());
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
                if( g.getState() == Ghost.STATE.NORMAL){
                    pac.setDied(true);
                }else
                    g.setDied(true);
            }
        }

    }
    public void updateShortestPath(){
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
    public void randomizeCherry(int time){
        boolean isRandom = false;
        currentTime = Sys.getTime();
        delta += (currentTime-lastTime);
        if( delta >= time*1000 ){
            delta = 0;
            Random r = new Random();
            while(!isRandom) {
                System.out.println("check");
                int rColumn = r.nextInt(graph.getColumn());
                int rRow = r.nextInt(graph.getRow());
                switch (graph.getNodes()[rRow][rColumn].getType()) {
                    case WALL:
                    case DOT:
                        break;
                    default:
                        graph.getNodes()[rRow][rColumn].setType(GameObject.TYPE.CHERRY);
                        isRandom = true;
                }
            }
        }
        lastTime = currentTime;
    }


}

