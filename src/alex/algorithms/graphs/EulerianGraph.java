package alex.algorithms.graphs;

import java.util.List;

public class EulerianGraph {

	public void dfs(Graph g, int v, boolean[] visited) {
		visited[v] = true;
		List<Integer> adj = g.adj.get(v);
		for (Integer u : adj) {
			if (!visited[u]) {
				dfs(g, u, visited);
			}
		}
	}

	public boolean isConnected(Graph g) {
		boolean[] visited = new boolean[g.V];
		int i = 0;
		for (i = 0; i < g.V; i++) {
			List<Integer> adj = g.adj.get(i);
			if (adj != null && adj.size() != 0)
				break;
		}
		// Empty graph
		if (i == g.V)
			return true;
		dfs(g, i, visited);
		for (i = 0; i < g.V; i++) {
			if (!visited[i] && g.adj.get(i).size() > 0)
				return false;
		}
		return true;
	}

	public int isEulerian(Graph g) {
		if (!isConnected(g))
			return 0;

		int odd = 0;
		for (int i = 0; i < g.V; i++) {
			if (g.adj.get(i).size() % 2 == 1)
				odd++;
		}
		if (odd > 2)
			return 0;
		if (odd> 0)
			return 1;// is Eulerian
		return 2;// semi-Eulerian
	}

	void test(Graph g) {
		int res = isEulerian(g);
		if (res == 0)
			System.out.println("Graph is not Eulerian");
		else if (res == 1)
			System.out.println("Graph has a Euler path");
		else
			System.out.println("Graph has a Euler cycle");
	}

	public static void main(String[] args) {
		EulerianGraph e = new EulerianGraph();
		Graph g1 = new Graph(5);
	    g1.addEdgeUndirected(1, 0);
	    g1.addEdgeUndirected(0, 2);
	    g1.addEdgeUndirected(2, 1);
	    g1.addEdgeUndirected(0, 3);
	    g1.addEdgeUndirected(3, 4);
	    e.test(g1);
	 
	    Graph g2 = new Graph(5);
	    g2.addEdgeUndirected(1, 0);
	    g2.addEdgeUndirected(0, 2);
	    g2.addEdgeUndirected(2, 1);
	    g2.addEdgeUndirected(0, 3);
	    g2.addEdgeUndirected(3, 4);
	    g2.addEdgeUndirected(4, 0);
	    e.test(g2);
	 
	    Graph g3 = new Graph(5);
	    g3.addEdgeUndirected(1, 0);
	    g3.addEdgeUndirected(0, 2);
	    g3.addEdgeUndirected(2, 1);
	    g3.addEdgeUndirected(0, 3);
	    g3.addEdgeUndirected(3, 4);
	    g3.addEdgeUndirected(1, 3);
	    e.test(g3);
	 
	    Graph g4 = new Graph(3);
	    g4.addEdgeUndirected(0, 1);
	    g4.addEdgeUndirected(1, 2);
	    g4.addEdgeUndirected(2, 0);
	    e.test(g4);
	 
	    Graph g5 = new Graph(3);
	    e.test(g5);
	}

}
