package alex.algorithms.graphs;

public class ConnectedComponents {

	public static boolean isSafe(int M[][], int row, int col,
			boolean visited[][]) {
		int COL = M.length;
		int ROW = M[0].length;
		return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL)
				&& (M[row][col] > 0 && !visited[row][col]);
	}

	public static void DFS(int M[][], int row, int col, boolean visited[][]) {
		int rowNbr[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int colNbr[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
		visited[row][col] = true;
		for (int i = 0; i < colNbr.length; i++) {
			if (isSafe(M, row + rowNbr[i], col + colNbr[i], visited)) {
				DFS(M, row + rowNbr[i], col + colNbr[i], visited);
			}
		}
	}

	public static int countConnectedComponents(int M[][]) {
		int COL = M.length;
		int ROW = M[0].length;
		boolean visited[][] = new boolean[ROW][COL];

		int count = 0;
		for (int row = 0; row < M.length; row++) {
			for (int column = 0; column < M[row].length; column++) {
				if (M[row][column] > 0 && !visited[row][column]) {
					DFS(M, row, column, visited);
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		//@formatter:off
		 int M[][]= {  
				    {1, 1, 0, 0, 0},
			        {0, 1, 0, 0, 1},
			        {1, 0, 0, 1, 1},
			        {0, 0, 0, 0, 0},
			        {1, 0, 1, 0, 1}
			    };
		 //@formatter:on
		 System.out.printf("Number of connected components=%d\n", countConnectedComponents(M));
	}

}
