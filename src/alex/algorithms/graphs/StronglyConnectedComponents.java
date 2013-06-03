package alex.algorithms.graphs;

import java.util.Arrays;

public class StronglyConnectedComponents {
	
	public static void dfs(Graph g, int v, boolean visited[]) {
		visited[v] = true;
		for (int i = 0; i < g.adj.get(v).size(); i++) {
			Integer u = g.adj.get(v).get(i);
			if (!visited[u]) {
				dfs(g, u, visited);
			}
		}
	}

	public static boolean isSC(Graph g) {
		boolean visited[] = new boolean[g.V];
		dfs(g, 0, visited);
		for (int i = 0; i < g.V; i++) {
			if (!visited[i])
				return false;
		}
		Graph gr = g.getTranspose();
		Arrays.fill(visited, false);
		dfs(gr, 0, visited);

		for (int i = 0; i < gr.V; i++) {
			if (!visited[i])
				return false;
		}
		return true;

	}

	public static void main(String[] args) {
		Graph g1 = new Graph(5);
		g1.addEdge(0, 1);
		g1.addEdge(1, 2);
		g1.addEdge(2, 3);
		g1.addEdge(3, 0);
		g1.addEdge(2, 4);
		g1.addEdge(4, 2);
		if (isSC(g1)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

		Graph g2 = new Graph(4);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);
		if (isSC(g2)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

}

