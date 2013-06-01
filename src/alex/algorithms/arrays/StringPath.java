package alex.algorithms.arrays;

public class StringPath {

	public static boolean hasPath(char[][] matrix, char[] str) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0
				|| str == null) {
			return false;
		}
		int rows = matrix.length;
		int cols = matrix[0].length;
		boolean visited[][] = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (hasPath(matrix, str, 0, visited, i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean hasPath(char[][] matrix, char[] str, int pathLength,
			boolean[][] visited, int row, int col) {
		if (pathLength == str.length)
			return true;
		if (row >= 0 && row < matrix.length && col >= 0
				&& col < matrix[row].length
				&& matrix[row][col] == str[pathLength] && !visited[row][col]) {
			pathLength++;
			visited[row][col] = true;
			boolean hasPath = hasPath(matrix, str, pathLength, visited,
					row + 1, col)
					|| hasPath(matrix, str, pathLength, visited, row + 1, col)
					|| hasPath(matrix, str, pathLength, visited, row, col + 1)
					|| hasPath(matrix, str, pathLength, visited, row - 1, col)
					|| hasPath(matrix, str, pathLength, visited, row, col - 1);
			if (!hasPath) {
				visited[row][col] = false;
			}
			return hasPath;
		}
		return false;
	}

	public static void main(String[] args) {
		char [][] matrix = {{'A','A','A','A'},{'A','L','A','A'},{'A','E','A','A'},{'A','X','A','A'}};
		System.out.println(hasPath(matrix, "AAAA".toCharArray()));
	}

}
