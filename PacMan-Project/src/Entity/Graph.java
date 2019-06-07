package Entity;

import Entity.Game;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Graph extends Game {
    private int vertices;
    private int edges;
    private int row,column;
    private Bag<Node>[][] adjency;
    private Node[][] nodes;

    public Graph(String name){
        In in = new In(new File(name));
        row = in.readInt();
        column = in.readInt();
        vertices = row*column;
        adjency = (Bag<Node>[][]) new Bag[row][column];
        nodes = new Node[row][column];

        // Initial value for nodes and adjency list
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                adjency[i][j] = new Bag<Node>();
                nodes[i][j] = new Node(i,j,in.readInt());
            }
        }



        buildAdjencyList();
    }
    private void buildAdjencyList(){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                makeEdge(i,j);
            }
        }
    }
    private void makeEdge(int currentRow,int currentColumn){
        int count = 0;
        for(int i=currentRow-1;i<=currentRow+1;i++){
            for(int j=currentColumn-1;j<=currentColumn+1;j++){
                if( count%2 != 0){
                    // Make Edge
                    Node.TYPE type = nodes[i][j].getType();
                    if( type != Node.TYPE.WALL ){
                        adjency[currentRow][currentColumn].add(nodes[i][j]);
                    }
                }
            }
        }
    }

    public int getVertices() {
        return vertices;
    }

    public int getEdges() {
        return edges;
    }

    public Bag<Node>[][] getAdjency() {
        return adjency;
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }

    public void setAdjency(Bag<Node>[][] adjency) {
        this.adjency = adjency;
    }

    public void setNodes(Node[][] nodes) {
        this.nodes = nodes;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
