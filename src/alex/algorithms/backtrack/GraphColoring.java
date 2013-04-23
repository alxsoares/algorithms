package alex.algorithms.backtrack;

public class GraphColoring {

	private static boolean isSafe(int v, int graph[][], int color[], int c) {
		for (int i = 0; i < graph.length; i++)
			if (graph[v][i] != 0 && c == color[i])
				return false;
		return true;
	}

	private static boolean graphColoring(int graph[][], int m, int color[],
			int v) {
		int V = graph.length;
		if (v == V)
			return true;

		for (int c = 1; c <= m; c++) {
			if (isSafe(v, graph, color, c)) {
				color[v] = c;

				if (graphColoring(graph, m, color, v + 1) == true)
					return true;
				color[v] = 0;
			}
		}
		return false;
	}

	public static boolean graphColoring(int graph[][], int m) {
		int V = graph.length;
		int[] color = new int[V];

		if (graphColoring(graph, m, color, 0) == false) {
			System.out.printf("No Solution");
			return false;
		}

		printSolution(color);
		return true;
	}

	private static void printSolution(int[] color) {
		System.out.printf("Assigned colors \n");
		for (int i = 0; i < color.length; i++)
			System.out.printf(" %d ", color[i]);
		System.out.printf("\n");

	}

	public static void main(String[] args) {
		//@formatter:off
		 /* 
	       3)---(2)
	       |   / |
	       |  /  |
	       | /   |
	      (0)---(1)
	    */
	    int graph[][] = {
	    	{1, 1, 1, 1},
	        {1, 1, 1, 0},
	        {1, 1, 1, 1},
	        {1, 0, 1, 1},
	    };
		//@formatter:on
		int m = 3; // Number of colors
		graphColoring(graph, m);
	}

}
