package Controller;

import Entity.Graph;

public class GraphController extends ObjectController{
    private Graph graph;

    public GraphController(Graph graph){
        this.graph = graph;
        graph.addController(this);
    }

    @Override
    public void update() {

    }
}
