package alex.datastructures;

public class Graph {

	public int V, E;
	public Edge[] edge;

	public static Graph createGraph(int V, int E) {
		Graph graph = new Graph();
		graph.V = V;
		graph.E = E;

		graph.edge = new Edge[E];
		
		for (int i = 0; i < E; i++) {
			graph.edge[i] = new Edge();
		}

		return graph;
	}

}
