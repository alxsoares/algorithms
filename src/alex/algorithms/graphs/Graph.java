package alex.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	int V;
	List<List<Integer>> adj;

	public Graph(int V) {
		this.V = V;
		adj = new ArrayList<List<Integer>>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}
	}

	void addEdge(int u, int v) {
		adj.get(u).add(v);
	}
	void addEdgeUndirected(int u, int v) {
		adj.get(u).add(v);
		adj.get(v).add(u);
	}

	public Graph getTranspose() {
		Graph g = new Graph(V);
		for (int v = 0; v < V; v++) {
			for (int i = 0; i < adj.get(v).size(); i++) {
				Integer u = adj.get(v).get(i);
				g.adj.get(u).add(v);
			}
		}
		return g;
	}

}
