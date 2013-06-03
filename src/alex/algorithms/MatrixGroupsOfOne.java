package alex.algorithms;

public class MatrixGroupsOfOne {

	public static int countGroups(int[][] m) {
		int count = 0;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] == 1) {
					flipOnes(m, i, j);
					count++;
				}
			}
		}
		return count;
	}

	private static void flipOnes(int[][] m, int i, int j) {
		if (i >= 0 && i < m.length && j >= 0 && j < m[i].length) {
			if (m[i][j] == 1) {
				m[i][j] = 0;
				flipOnes(m, i - 1, j);
				flipOnes(m, i, j - 1);
				flipOnes(m, i + 1, j);
				flipOnes(m, i, j + 1);
			}
		}
	}

	public static void main(String[] args) {
		int [][] m ={{0,0,0,0,1},{0,0,0,1,0},{0,0,0,1,0},{0,0,1,1,1}};
		System.out.println(countGroups(m));
	}

}
