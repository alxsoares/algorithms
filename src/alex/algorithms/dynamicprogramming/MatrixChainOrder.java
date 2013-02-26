package alex.algorithms.dynamicprogramming;

public class MatrixChainOrder {

	public static int[][] matrixChainOrder(int[] p) {
		int n = p.length - 1;
		int[][] m = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			m[i][i] = 0;
		}
		for (int l = 2; l <= n; l++) {
			for (int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				m[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j - 1; k++) {
					int q = m[i][k] + m[k + 1][j] + p[j - 1] * p[k] * p[i];
					if (q < m[i][j]) {
						m[i][j] = q;
					}
				}
			}
		}

		return m;
	}

	public static void main(String[] args) {

	}

}
