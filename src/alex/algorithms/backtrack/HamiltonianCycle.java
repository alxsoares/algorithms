package alex.algorithms.backtrack;

import java.util.Arrays;

public class HamiltonianCycle {

	public static boolean isSafe(int v, int graph[][], int path[], int pos) {
		// adjacent to the previously added vertex
		if (graph[path[pos - 1]][v] == 0)
			return false;

		// check if v was already add to the cycle
		for (int i = 0; i < pos; i++)
			if (path[i] == v)
				return false;

		return true;
	}

	public static boolean hamCycle(int graph[][], int path[], int pos) {
		int V = graph.length;
		if (pos == V) {
			if (graph[path[pos - 1]][path[0]] == 1)
				return true;
			else
				return false;
		}

		for (int v = 1; v < V; v++) {
			if (isSafe(v, graph, path, pos)) {
				path[pos] = v;
				if (hamCycle(graph, path, pos + 1) == true)
					return true;
				path[pos] = -1;
			}
		}

		return false;
	}

	public static boolean hamCycle(int graph[][]) {
		int V = graph.length;
		int path[] = new int[V];
		Arrays.fill(path, -1);

		path[0] = 0;
		if (hamCycle(graph, path, 1) == false) {
			System.out.println("No Solution");
			return false;
		}

		printSolution(path);
		return true;
	}

	private static void printSolution(int[] path) {
		System.out.println("Hamiltonian Cycle");
		for (int i = 0; i < path.length; i++)
			System.out.printf(" %d ", path[i]);

		System.out.printf(" %d ", path[0]);
		System.out.printf("\n");

	}

	public static void main(String[] args) {
		//@formatter:off
		/* 
	      (0)--(1)--(2)
	       |   / \   |
	       |  /   \  |
	       | /     \ |
	      (3)-------(4)    */
	   int graph[][] = {
			   			  {1, 1, 0, 1, 0},
	                      {1, 1, 1, 1, 1},
	                      {0, 1, 1, 0, 1},
	                      {1, 1, 0, 1, 1},
	                      {0, 1, 1, 1, 1},
	                     };
	 
	    // Print the solution
		//@formatter:on
		hamCycle(graph);
	}

}
