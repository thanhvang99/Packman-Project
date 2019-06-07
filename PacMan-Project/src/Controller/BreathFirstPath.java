package Controller;

import Entity.Graph;
import Entity.Node;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreathFirstPath {

    private boolean[][] marked;
    private Node[][] nodeTo;
    private Node source;
    private Node target;

    public BreathFirstPath(Graph g, Node source){
        this.source = source;
        marked = new boolean[g.getRow()][g.getColumn()];
        nodeTo = new Node[g.getRow()][g.getColumn()];
        bfs(g,source);
    }
    public void bfs(Graph g,Node node){
            Queue<Node> queue = new Queue<Node>();
            queue.enqueue(node);
            marked[node.getRow()][node.getColumn()] = true;
            while (!queue.isEmpty()) {
                Node nextNode = queue.dequeue();
                for (Node w : g.getAdjency()[nextNode.getRow()][nextNode.getColumn()]) {
                    if (!marked[w.getRow()][w.getColumn()]) {
                        queue.enqueue(w);
                        marked[w.getRow()][w.getColumn()] = true;
                        nodeTo[w.getRow()][w.getColumn()] = node;
                    }

                }
            }
    }
    public boolean hasPathTo(Node target){
        return marked[target.getRow()][target.getColumn()];
    }
    public Stack<Node> pathTo(Node target){
        Stack<Node> path = new Stack<Node>();
        if(!hasPathTo(target)){
            return null;
        }else{
            for(Node x=target;x != source; x = nodeTo[x.getRow()][x.getColumn()]){
                path.push(x);
            }
            path.push(source);
            return path;
        }
    }
    private void reset(){
    }
}
