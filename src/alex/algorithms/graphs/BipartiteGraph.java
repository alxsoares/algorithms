package alex.algorithms.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraph {
	public static boolean isBipartite(int graph[][]) {
		int V = graph.length;
		int color[] = new int[V];
		for (int i = 0; i < color.length; i++) {
			color[i] = -1;
		}
		color[0] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v = 0; v < V; v++) {
				if (graph[u][v] > 0 && color[v] == -1) {
					color[v] = 1 - color[u];
					q.add(v);
				} else if (graph[u][v] > 0 && color[v] == color[u]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		//@formatter:on
		int graph[][] = {
				{0, 1, 0, 1},
		        {1, 0, 1, 0},
		        {0, 1, 0, 1},
		        {1, 0, 1, 0}
		    };
		//@formatter:off
		if(isBipartite(graph)){
			System.out.println("Graph is Bipartite");
		}else{
			System.out.println("Graph is not Bipartite");
		}
	}

}
