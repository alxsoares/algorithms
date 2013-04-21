package alex.algorithms.graphs;

public class TransitiveClosure {

	public static void transitiveClosure(int graph[][]) {
		int V = graph.length;
		boolean reach[][] = new boolean[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (graph[i][j] > 0) {
					reach[i][j] = true;
				}
			}
		}
		for (int k = 0; k < V; k++) {
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					reach[i][j] = reach[i][j] || (reach[i][k] && reach[k][j]);
				}
			}
		}

		System.out.printf("Transitive closure is\n");
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++)
				System.out.printf("%d ", reach[i][j] ? 1 : 0);
			System.out.printf("\n");
		}
	}

	public static void main(String[] args) {
		//@formatter:off
		 int graph[][] = { {1, 1, 0, 1},
                 {0, 1, 1, 0},
                 {0, 0, 1, 1},
                 {0, 0, 0, 1}
               };
		//@formatter:on
		transitiveClosure(graph);
	}

}
