package alex.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Tarjan’s algorithm for finding articulation points.
 * 
 */
public class Bridge {
	int V;
	List<Integer> adj[];
	static int time = 0;

	public Bridge(int V) {
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

	public void dfs(int u, boolean visited[], int disc[],
            int low[], Integer[] parent) {
		visited[u] = true;
		disc[u] = low[u] = ++time;
		List<Integer> adjList = adj[u];
		for (Integer v : adjList) {
			if (!visited[v]) {
				parent[v] = u;

				dfs(v, visited, disc, low, parent);
				low[u] = Math.min(low[u], low[v]);
				if(low[v] > disc[u]){
					System.out.printf("%d %d \n",u,v);
				}
			}
			else if (v != parent[u]) {
				low[u] = Math.min(low[u], disc[v]);
			}
		}

	}

	public void bridge(){
	    boolean[] visited = new boolean[V];
	    int[] disc = new int[V];
	    int[] low = new int[V];
	    Integer[] parent = new Integer[V];
	 
	    for (int i = 0; i < V; i++)
	        if (!visited[i])
	            dfs(0, visited, disc, low, parent);
	}

	public static void main(String[] args) {
	    System.out.printf("\nBridges in first graph \n");
	    Bridge g1= new Bridge(5);
	    g1.addEdge(1, 0);
	    g1.addEdge(0, 2);
	    g1.addEdge(2, 1);
	    g1.addEdge(0, 3);
	    g1.addEdge(3, 4);
	    g1.bridge();
	 
	    System.out.printf("\nBridges in second graph \n");
	    Bridge g2 = new Bridge(5);
	    g2.addEdge(0, 1);
	    g2.addEdge(1, 2);
	    g2.addEdge(2, 3);
	    g2.addEdge(3, 4);
	    g2.bridge();
	 
	    System.out.printf("\nBridges in third graph \n");
	    Bridge g3= new Bridge(7);
	    g3.addEdge(0, 1);
	    g3.addEdge(1, 2);
	    g3.addEdge(2, 0);
	    g3.addEdge(1, 3);
	    g3.addEdge(1, 4);
	    g3.addEdge(1, 6);
	    g3.addEdge(3, 5);
	    g3.addEdge(4, 5);
	    g3.bridge();
	}

}
