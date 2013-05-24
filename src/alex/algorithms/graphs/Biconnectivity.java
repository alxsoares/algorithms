package alex.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

public class Biconnectivity {
	int V;
	List<Integer> adj[];
	static int time = 0;

	public Biconnectivity(int V) {
		this.V = V;
		adj = new List[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}

	void addEdge(int u, int v) {
		adj[v].add(u);
		adj[u].add(v); // Add v to u's list
	}

	public boolean dfs(int u, boolean visited[], int disc[], int low[],
			Integer parent[]) {
		int children = 0;
		visited[u] = true;
		disc[u] = low[u] = ++time;
		List<Integer> adjList = adj[u];
		for (Integer v : adjList) {
			if (!visited[v]) {
				children++;
				parent[v] = u;

				if (dfs(v, visited, disc, low, parent))
					return true;
				low[u] = Math.min(low[u], low[v]);

				if (parent[u] == null && children > 1) {
					return true;
				}
				if (parent[u] != null && low[v] >= disc[u]) {
					return true;
				}
			} else if (v != parent[u]) {
				low[u] = Math.min(low[u], disc[v]);
			}
		}
		return false;
	}

	public boolean biconnect() {
		boolean[] visited = new boolean[V];
		int[] disc = new int[V];
		int[] low = new int[V];
		Integer[] parent = new Integer[V];

		if (dfs(0, visited, disc, low, parent))
			return false;
		// check connectivity
		for (int i = 0; i < V; i++)
			if (!visited[i])
				return false;
		return true;
	}

	public static void main(String[] args) {
		Biconnectivity g1 = new Biconnectivity(5);
		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 1);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		System.out.println(g1.biconnect());

		Biconnectivity g2 = new Biconnectivity(5);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);
		g2.addEdge(3, 4);
		System.out.println(g2.biconnect());

		Biconnectivity g3 = new Biconnectivity(7);
		g3.addEdge(0, 1);
		g3.addEdge(1, 2);
		g3.addEdge(2, 0);
		g3.addEdge(1, 3);
		g3.addEdge(1, 4);
		g3.addEdge(1, 6);
		g3.addEdge(3, 5);
		g3.addEdge(4, 5);
		System.out.println(g3.biconnect());

		Biconnectivity g4 = new Biconnectivity(5);
		g4.addEdge(1, 0);
		g4.addEdge(0, 2);
		g4.addEdge(2, 1);
		g4.addEdge(0, 3);
		g4.addEdge(3, 4);
		g4.addEdge(2, 4);
		System.out.println(g4.biconnect());
	}

}
