package alex.algorithms.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DAGShortestPath {
	int V;
	List<AdjacentNode> adj[];

	public DAGShortestPath(int V) {
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

	public void topologicalDFS(int v, boolean visited[], Stack<Integer> top) {
		visited[v] = true;
		List<AdjacentNode> adjList = adj[v];
		for (AdjacentNode node : adjList) {
			if (!visited[node.v]) {
				topologicalDFS(node.v, visited, top);
			}
			top.push(v);
		}

	}

	public void shortestPath(int source) {
		Stack<Integer> top = new Stack<Integer>();
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				topologicalDFS(i, visited, top);
			}
		}
		int dist[] = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;
		while (!top.isEmpty()) {
			int u = top.pop();
			if (dist[u] != Integer.MAX_VALUE) {
				List<AdjacentNode> neighbors = adj[u];
				for (AdjacentNode adjacentNode : neighbors) {
					if (dist[adjacentNode.v] > dist[u] + adjacentNode.weight) {
						dist[adjacentNode.v] = dist[u] + adjacentNode.weight;
					}
				}
			}
		}
		for (int i = 0; i < dist.length; i++) {
			if (dist[i] == Integer.MAX_VALUE)
				System.out.printf("INF ");
			else
				System.out.printf("%d ", dist[i]);
			
		}
		System.out.println();
	}

	public static void main(String[] args) {
		DAGShortestPath g = new DAGShortestPath(6);
	    g.addEdge(0, 1, 4);
	    g.addEdge(0, 2, 7);
	    g.addEdge(1, 3, 8);
	    g.addEdge(1, 2, 3);
	    g.addEdge(2, 4, 9);
	    g.addEdge(2, 5, 1);
	    g.addEdge(2, 3, 9);
	    g.addEdge(3, 4, -10);
	    g.addEdge(4, 5, -12);
	 
	    int source = 1;
	    g.shortestPath(source);
	}

}

class AdjacentNode {
	int weight;
	int v;

	public AdjacentNode(int v, int weight) {
		this.v = v;
		this.weight = weight;
	}
}