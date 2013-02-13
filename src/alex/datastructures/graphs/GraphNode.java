package alex.datastructures.graphs;

public class GraphNode {

    public int data;
    public State state;

    public GraphNode(int d) {
        this.data = d;
        this.state = State.UnVisited;
    }
}