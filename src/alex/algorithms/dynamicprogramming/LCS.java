package alex.algorithms.dynamicprogramming;

public class LCS {
	public static int lcsR(final char x[], final char y[], final int xx,
			final int yy) {
		if (xx == -1 || yy == -1) {
			return 0;
		}
		if (x[xx] == y[yy])
			return 1 + lcsR(x, y, xx - 1, yy - 1);
		return Math.max(lcsR(x, y, xx - 1, yy), lcsR(x, y, xx, yy - 1));
	}

	public static int lcs(final String x, final String y) {

		int dp[][] = new int[x.length() + 1][y.length() + 1];
		int p[][] = new int[x.length() + 1][y.length() + 1];

		for (int i = 0; i <= x.length(); i++) {
			dp[i][0] = 0;

		}

		for (int i = 0; i <= y.length(); i++) {
			dp[0][i] = 0;
		}
		for (int i = 1; i <= x.length(); i++) {

			for (int j = 1; j <= y.length(); j++) {
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					p[i][j] = 1;
				} else if (dp[i - 1][j] >= dp[i][j - 1]) {
					// desce
					dp[i][j] = dp[i - 1][j];
					p[i][j] = 2;
				} else {
					// frente
					dp[i][j] = dp[i][j - 1];
					p[i][j] = 3;
				}
			}
		}
		// imprimir a LCS
		int i = x.length();
		int j = y.length();
		StringBuilder sb = new StringBuilder();
		while (i != 0 && j != 0) {
			if (p[i][j] == 1) {
				sb.append(x.charAt(i - 1));
				i--;
				j--;
			} else if (p[i][j] == 2) {
				i--;
			} else if (p[i][j] == 3) {
				j--;
			}
		}
		System.out.println(sb.reverse().toString());
		return dp[x.length()][y.length()];
	}

	/**
	 * @param args
	 */

	public static void main(final String[] args) {
		String s1 = "Avlvevxxx";
		char x[] = s1.toCharArray();
		String s2 = "Axlxenxxx";
		char y[] = s2.toCharArray();
		System.out.println(lcsR(x, y, x.length - 1, y.length - 1));
		System.out.println(lcs(s1, s2));

	}
}
