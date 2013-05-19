package alex.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

public class CycleDFS {
	int V;
	List<AdjacentNode> adj[];

	public CycleDFS(int V) {
		this.V = V;
		adj = new List[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<AdjacentNode>();
		}
	}

	void addEdge(int u, int v, int weight) {
		AdjacentNode node = new AdjacentNode(v, weight);
		adj[u].add(node); // Add v to u's list
	}

	private boolean isCyclic(int v, boolean[] visited, boolean[] stack) {
		if (!visited[v]) {
			visited[v] = true;
			stack[v] = true;
			List<AdjacentNode> n = adj[v];
			for (AdjacentNode adjacentNode : n) {
				if (!visited[adjacentNode.v]
						&& isCyclic(adjacentNode.v, visited, stack)) {
					return true;
				} else if (stack[adjacentNode.v]) {
					return true;
				}
			}
		}
		stack[v] = false;
		return false;
	}

	public boolean isCyclic() {
		boolean[] visited = new boolean[V];
		boolean[] stack = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (isCyclic(i, visited, stack)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		CycleDFS g = new CycleDFS(4);
		g.addEdge(0, 1, 1);
		g.addEdge(0, 2, 1);
		g.addEdge(1, 2, 1);
		g.addEdge(2, 0, 1);
		g.addEdge(2, 3, 1);
		g.addEdge(3, 3, 1);

		if (g.isCyclic())
			System.out.println("Graph contains cycle");
		else
			System.out.println("Graph doesn't contain cycle");
	}

}
