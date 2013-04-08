package alex.algorithms.dynamicprogramming;

/**
 * Given a cost matrix cost[][] and a position (m, n) in cost[][], write a
 * function that returns cost of minimum cost path to reach (m, n) from (0, 0).
 * Each cell of the matrix represents a cost to traverse through that cell.
 * Total cost of a path to reach (m, n) is sum of all the costs on that path
 * (including both source and destination). You can only traverse down, right
 * and diagonally lower cells from a given cell, i.e., from a given cell (i, j),
 * cells (i+1, j), (i, j+1) and (i+1, j+1) can be traversed. You may assume that
 * all costs are positive integers.
 * 
 */
public class MinimumCostPath {
	/**
	 * minCost(m, n) = min (minCost(m-1, n-1), minCost(m-1, n), minCost(m, n-1))
	 * + cost[m][n]
	 * 
	 */
	public static int minCost(int cost[][], int m, int n) {
		int rows = cost.length;
		int columns = cost[0].length;
		int[][] minCosts = new int[rows][columns];
		
		minCosts[0][0] = cost[0][0];

		for (int i = 1; i <= m; i++) {
			minCosts[i][0] = minCosts[i - 1][0] + cost[i][0];
		}

		for (int j = 1; j <= n; j++) {
			minCosts[0][j] = minCosts[0][j - 1] + cost[0][j];
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				minCosts[i][j] = Math.min(minCosts[i - 1][j - 1],
						Math.min(minCosts[i - 1][j], minCosts[i][j - 1]))
						+ cost[i][j];
			}
		}

		return minCosts[m][n];
	}

	public static void main(String[] args) {
		//@formatter:off
		int cost[][] = { 
				{1, 2, 3},
                {4, 8, 2},
                {1, 5, 3} 
                };
		//@formatter:on
		System.out.printf(" %d ", minCost(cost, 2, 2));
	}

}
