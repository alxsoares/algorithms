package alex.algorithms.dynamicprogramming;

public class FloydWarshall {

	public static int[][] floydWarshall(int[][] G) {
		int V = G.length;
		int dist[][] = new int[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				dist[i][j] = G[i][j];
			}
		}
		for (int v = 0; v < V; v++) {
			for (int u = 0; u < V; u++) {
				for (int k = 0; k < V; k++) {
					//Avoiding arithmetic overflow
					if (dist[v][k] != Integer.MAX_VALUE
							&& dist[k][u] != Integer.MAX_VALUE
							&& dist[v][u] > dist[v][k] + dist[k][u]) {
						dist[v][u] = dist[v][k] + dist[k][u];
					}
				}
			}
		}

		return dist;
	}

	public static void main(String[] args) {
		int INF = Integer.MAX_VALUE;
		int G[][] = { { 0, 5, INF, 10 }, { INF, 0, 3, INF },
				{ INF, INF, 0, 1 }, { INF, INF, INF, 0 } };
		int dist[][] = floydWarshall(G);
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist[i].length; j++) {
				if (dist[i][j] == INF)
					System.out.print("INF ");
				else
					System.out.printf("%d ", dist[i][j]);
			}
			System.out.println();
		}
	}

}
