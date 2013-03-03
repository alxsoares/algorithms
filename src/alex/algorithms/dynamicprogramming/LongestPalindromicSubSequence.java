package alex.algorithms.dynamicprogramming;

public class LongestPalindromicSubSequence {
	public static int lps(char[] s) {
		int n = s.length;
		int[][] L = new int[n][n];

		for (int i = 0; i < n; i++) {
			L[i][i] = 1;
		}

		for (int len = 2; len <= n; len++) {

			for (int i =0;i <= n-len;  i++) {
				
				int j = i + len - 1;
				
				if (s[i] == s[j] && len == 2) {
					L[i][j] = 2;
				} else if (s[i] == s[j]) {
					L[i][j] = L[i + 1][j - 1] + 2;
				} else {
					//L[i+1][j] was already calculated on the step before.
					L[i][j] = Math.max(L[i][j - 1], L[i + 1][j]);
				}
			}
		}

		return L[0][n - 1];
	}

	public static void main(String[] args) {
		char seq[] = "BBAAaccAAABBkjdkjsdsjjssdkAAAAAAA".toCharArray();
		System.out.printf("%d\n", lps(seq));
	}

}
