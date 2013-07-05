package alex.algorithms.graphs;

import java.util.LinkedList;

public class MaxFlowFordFulkerson {
	static boolean bfs(int g[][], int s, int t, int parent[]) {
		// Create a visited array and mark all vertices as not visited
		int V = g.length;
		boolean visited[] = new boolean[V];

		// Create a queue, enqueue source vertex and mark source vertex
		// as visited
		LinkedList<Integer> q = new LinkedList<>();
		q.push(s);
		visited[s] = true;
		parent[s] = -1;

		// Standard BFS Loop
		while (!q.isEmpty()) {
			int u = q.poll();

			for (int v = 0; v < V; v++) {
				if (!visited[v] && g[u][v] > 0) {
					q.push(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}

		// If we reached sink in BFS starting from source, then return
		// true, else false
		return visited[t];
	}

	// Returns tne maximum flow from s to t in the given graph
	public static int fordFulkerson(int g[][], int s, int t) {
		int u, v;

		// Create a residual graph and fill the residual graph with
		// given capacities in the original graph as residual capacities
		// in residual graph
		int V = g.length;
		int rGraph[][] = new int[V][V]; // Residual graph where
														// rGraph[i][j]
														// indicates
		// residual capacity of edge from i to j (if there
		// is an edge. If rGraph[i][j] is 0, then there is not)
		for (u = 0; u < V; u++)
			for (v = 0; v < V; v++)
				rGraph[u][v] = g[u][v];

		int parent[] = new int[V]; // This array is filled by BFS and to
											// store path

		int maxFlow = 0; // There is no flow initially

		// Augment the flow while there is path from source to sink
		while (bfs(rGraph, s, t, parent)) {
			// Find minimum residual capacity of the edges along the
			// path filled by BFS. Or we can say find the maximum flow
			// through the path found.
			int pathFlow = Integer.MAX_VALUE;
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				pathFlow = Math.min(pathFlow, rGraph[u][v]);
			}

			// update residual capacities of the edges and reverse edges
			// along the path
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				rGraph[u][v] -= pathFlow;
				rGraph[v][u] += pathFlow;
			}

			// Add path flow to overall flow
			maxFlow += pathFlow;
		}

		// Return the overall flow
		return maxFlow;
	}

	public static void main(String[] args) {
		// @formatter:off
		int graph[][] = { { 0, 16, 13, 0, 0, 0 }, { 0, 0, 10, 12, 0, 0 },
				{ 0, 4, 0, 0, 14, 0 }, { 0, 0, 9, 0, 0, 20 },
				{ 0, 0, 0, 7, 0, 4 }, { 0, 0, 0, 0, 0, 0 } };
		// @formatter:on

		System.out.println("The maximum possible flow is "
				+ fordFulkerson(graph, 0, 5));
	}
}
