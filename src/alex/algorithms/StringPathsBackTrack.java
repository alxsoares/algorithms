package alex.algorithms;

public class StringPathsBackTrack {
	public static boolean hasPath(char[][] matrix, String str) {
		boolean visited[][] = new boolean[matrix.length][matrix[0].length];
		int pathLength = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (hasPath(matrix, str, pathLength, visited, i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean hasPath(char[][] matrix, String str, int pathLength,
			boolean[][] visited, int row, int col) {
		if (pathLength == str.length())
			return true;
		if (row >= 0 && row < matrix.length && col >= 0
				&& col < matrix[row].length
				&& matrix[row][col] == str.charAt(pathLength)
				&& !visited[row][col]) {
			pathLength++;
			visited[col][row] = true;
			boolean hasPath = hasPath(matrix, str, pathLength, visited, row,
					col)
					|| hasPath(matrix, str, pathLength, visited, row - 1, col)
					|| hasPath(matrix, str, pathLength, visited, row, col - 1)
					|| hasPath(matrix, str, pathLength, visited, row + 1, col)
					|| hasPath(matrix, str, pathLength, visited, row, col + 1);
			if(!hasPath){
				visited[col][row] = false;
			}
			return hasPath;
		}
		return false;
	}

	public static void main(String[] args) {
		char [][] matrix = {{'A','A','A','A'},{'A','L','A','A'},{'A','E','A','A'},{'A','X','A','A'}};
		System.out.println(hasPath(matrix, "ALA"));
	}

}
