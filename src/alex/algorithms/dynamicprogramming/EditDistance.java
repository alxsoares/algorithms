package alex.algorithms.dynamicprogramming;

public class EditDistance {

	public static int editDistance(String X, String Y) {
		int m = Y.length() + 1;
		int n = X.length() + 1;
		int T[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				T[i][j] = -1;
			}
		}
		// T[i][0] = i
		for (int i = 0; i < n; i++) {
			T[i][0] = i;
		}
		// T[0][j] = j
		for (int j = 0; j < m; j++) {
			T[0][j] = j;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				int left = T[i][j - 1] + 1;
				int top = T[i - 1][j] + 1;
				int corner = T[i - 1][j - 1];
				if (X.charAt(i-1) != Y.charAt(j-1))
					corner += 1;
				T[i][j] = Math.min(left, Math.min(top, corner));
			}
		}

		return T[n - 1][m - 1];
	}

	public static void main(String[] args) {
		System.out.printf("%d", editDistance("aaaBcc", "AaaaBc"));
	}

}
