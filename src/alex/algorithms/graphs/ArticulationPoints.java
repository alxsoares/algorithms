package alex.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Tarjan’s algorithm for finding articulation points.
 * 
 */
public class ArticulationPoints {
	int V;
	List<Integer> adj[];
	static int time = 0;

	public ArticulationPoints(int V) {
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

	// A recursive function that find articulation points using DFS traversal
	// u --> The vertex to be visited next
	// visited[] --> keeps tract of visited vertices
	// disc[] --> Stores discovery times of visited vertices
	// parent[] --> Stores parent vertices in DFS tree
	// ap[] --> Store articulation points
	public void dfs(int u, boolean visited[], int disc[], int low[],
			Integer parent[], boolean ap[]) {
		int children = 0;
		visited[u] = true;
		disc[u] = low[u] = ++time;
		List<Integer> adjList = adj[u];
		for (Integer v : adjList) {
			if (!visited[v]) {
				children++;
				parent[v] = u;

				dfs(v, visited, disc, low, parent, ap);
				// Check if the subtree rooted with v has a connection to
				// one of the ancestors of u
				low[u] = Math.min(low[u], low[v]);
				// u is an articulation point in following cases

				// (1) u is root of DFS tree and has two or more chilren.
				if (parent[u] == null && children > 1) {
					ap[u] = true;
				}
				// (2) If u is not root and low value of one of its child is
				// more
				// than discovery value of u.
				if (parent[u] != null && low[v] >= disc[u]) {
					ap[u] = true;
				}
			}
			// Update low value of u for parent function calls.
			else if (v != parent[u]) {
				low[u] = Math.min(low[u], disc[v]);
			}
		}

	}

	public void articulationPoint(){
		// Mark all the vertices as not visited
	    boolean[] visited = new boolean[V];
	    int[] disc = new int[V];
	    int[] low = new int[V];
	    Integer[] parent = new Integer[V];
	    boolean [] ap = new boolean[V]; // To store articulation points
	 
	    // Initialize parent and visited, and ap(articulation point) arrays
	    for (int i = 0; i < V; i++)
	    {
	        parent[i] = null;
	        visited[i] = false;
	        ap[i] = false;
	    }
	 
	    // Call the recursive helper function to find articulation points
	    // in DFS tree rooted with vertex 'i'
	    for (int i = 0; i < V; i++)
	        if (visited[i] == false)
	            dfs(0, visited, disc, low, parent, ap);
	 
	    // Now ap[] contains articulation points, print them
	    for (int i = 0; i < V; i++)
	        if (ap[i] == true)
	    		System.out.printf("%d ", i);
	}

	public static void main(String[] args) {
		 // Create graphs given in above diagrams
	    System.out.printf("\nArticulation points in first graph \n");
	    ArticulationPoints g1= new ArticulationPoints(5);
	    g1.addEdge(1, 0);
	    g1.addEdge(0, 2);
	    g1.addEdge(2, 1);
	    g1.addEdge(0, 3);
	    g1.addEdge(3, 4);
	    g1.articulationPoint();
	 
	    System.out.printf("\nArticulation points in second graph \n");
	    ArticulationPoints g2 = new ArticulationPoints(5);
	    g2.addEdge(0, 1);
	    g2.addEdge(1, 2);
	    g2.addEdge(2, 3);
	    g2.addEdge(3, 4);
	    g2.articulationPoint();
	 
	    System.out.printf("\nArticulation points in third graph \n");
	    ArticulationPoints g3= new ArticulationPoints(7);
	    g3.addEdge(0, 1);
	    g3.addEdge(1, 2);
	    g3.addEdge(2, 0);
	    g3.addEdge(1, 3);
	    g3.addEdge(1, 4);
	    g3.addEdge(1, 6);
	    g3.addEdge(3, 5);
	    g3.addEdge(4, 5);
	    g3.articulationPoint();
	}

}
