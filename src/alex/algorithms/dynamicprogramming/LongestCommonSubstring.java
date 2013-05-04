package alex.algorithms.dynamicprogramming;

/**
 * 
 * Given two strings ‘X’ and ‘Y’, find the length of the longest common
 * substring.
 */
public class LongestCommonSubstring {
	//@formatter:off
	/**
	 * The longest common suffix has following optimal substructure property
           LCSuff(X, Y, m, n) = LCSuff(X, Y, m-1, n-1) + 1 if X[m-1] = Y[n-1]
                        0  Otherwise (if X[m-1] != Y[n-1])

      The maximum length Longest Common Suffix is the longest common substring.
            LCSubStr(X, Y, m, n)  = Max(LCSuff(X, Y, i, j)) where 1 <= i <= m
                                                     and 1 <= j <= n
	 */
	//@formatter:on
	public static int longestCommonSubstring(String X, String Y) {
		char[] x = X.toCharArray();
		char[] y = Y.toCharArray();
		int n = x.length;
		int m = y.length;
		int L[][] = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			L[i][0] = 0;
		}
		for (int i = 0; i <= m; i++) {
			L[0][i] = 0;
		}
		int res = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (x[i - 1] == y[j - 1]) {
					L[i][j] = L[i - 1][j - 1] + 1;
					res = Math.max(res, L[i][j]);
				} else {
					L[i][j] = 0;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String X = "OldSite:GeeksforGeeks.org";
		String Y = "NewSite:GeeksQuiz.com";

		System.out.println("Length of max common substring is "
				+ longestCommonSubstring(X, Y));
	}

}
