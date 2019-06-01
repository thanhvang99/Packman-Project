import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreathFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private int source;
    private Stack<Integer> path = new Stack<Integer>();

    public BreathFirstPath(Graph g,int source){
        this.source = source;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        bfs(g,source);
    }
    public void bfs(Graph g,int vertice){
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(vertice);
        marked[vertice]=true;
        while(!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w : g.adjacencyAt(v)){
                if(!marked[w]){
                    queue.enqueue(w);
                    marked[w]=true;
                    edgeTo[w]=v;
                }

            }
        }
    }
    public boolean hasPathTo(int target){
        return marked[target];
    }
    public Stack<Integer> pathTo(int target){
        if(!hasPathTo(target)){
            return null;
        }else{
            for(int x=target;x != source; x = edgeTo[x]){
                path.push(x);
            }
            path.push(source);
            return path;
        }
    }
}
