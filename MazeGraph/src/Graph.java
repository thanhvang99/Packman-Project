import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {
    private Bag<Integer>[] adj;
    private Maze maze;
    private static int V;
    private static int E;

    public Graph(int V){
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int i=0;i<V;i++){
            adj[i] = new Bag<Integer>();
        }
    }
    public Graph(Maze maze){
        this(maze.getColumn() * maze.getRow());
        this.maze = maze;
        for(int i=0;i<maze.getRow();i++){
            for(int j=0;j<maze.getColumn();j++){
                makeEdge(i,j);
            }
        }
    }
    public void makeEdge(int row,int column){
        makeLeft(row,column);
        makeRight(row,column);
        makeDown(row,column);
        makeUp(row,column);

    }
    private void makeLeft(int row,int column){
        if(column > 0 && maze.valueAt(row,column-1) == 0){
            int from = maze.convertToIndex1D(row,column);
            int to = maze.convertToIndex1D(row,column-1);
            addEdge(from,to);
        }

    }
    private void makeRight(int row,int column){
        if(column < maze.getColumn()-1 && maze.valueAt(row,column+1) == 0){
            int from = maze.convertToIndex1D(row,column);
            int to = maze.convertToIndex1D(row,column+1);
            addEdge(from,to);
        }

    }

    private void makeUp(int row,int column){
        if(row > 0 && maze.valueAt(row-1,column) == 0){
            int from = maze.convertToIndex1D(row,column);
            int to = maze.convertToIndex1D(row-1,column);
            addEdge(from,to);
        }

    }
    private void makeDown(int row,int column){
        if(row < maze.getRow()-1 && maze.valueAt(row+1,column)==0){
            int from = maze.convertToIndex1D(row,column);
            int to = maze.convertToIndex1D(row+1,column);
            addEdge(from,to);
        }
    }
    public void addEdge(int from,int to){
        adj[from].add(to);
        E++;
    }
    public int V(){return V;}
    public int E(){return E;}
    public Bag<Integer> adjacencyAt(int v){return adj[v];}
    public Maze getMaze(){return maze;}
}
