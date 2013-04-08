package alex.algorithms.dynamicprogramming;

/**
 * Given a string, a partitioning of the string is a palindrome partitioning if
 * every substring of the partition is a palindrome. For example,
 * “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
 * Determine the fewest cuts needed for palindrome partitioning of a given
 * string. For example, minimum 3 cuts are needed for “ababbbabbababa”. The
 * three cuts are “a|babbbab|b|ababa”. If a string is palindrome, then minimum 0
 * cuts are needed. If a string of length n containing all different characters,
 * then minimum n-1 cuts are needed.
 * 
 */
public class PaindromePartitioning {
	//@formatter:off
	/**
	 *minPalPartion(str, i, j) = 0 if i == j. // When string is of length 1.
	 *minPalPartion(str, i, j) = 0 if str[i..j] is palindrome.
     *If none of the above conditions is true, then minPalPartion(str, i, j) can be 
     *calculated recursively using the following formula.
     *minPalPartion(str, i, j) = Min { minPalPartion(str, i, k) + 1 +
                                 minPalPartion(str, k+1, j) } 
                           where k varies from i to j-1 
	 */
	//@formatter:on
	public static int minPalindromePartition(String s) {
		int n = s.length();
		int[][] cuts = new int[n][n];
		boolean[][] pal = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			cuts[i][i] = 0;
			pal[i][i] = true;
		}
		for (int L = 2; L <= n; L++) {
			for (int i = 0; i < n - L + 1; i++) {
				int j = i + L - 1;
				if (L == 2) {
					pal[i][j] = (s.charAt(i) == s.charAt(j));
				} else {
					pal[i][j] = (s.charAt(i) == s.charAt(j)) && pal[i + 1][j - 1];
				}
				if (pal[i][j]) {
					cuts[i][j] = 0;
				} else {
					cuts[i][j] = Integer.MAX_VALUE;
					for (int k = i; k <= j - 1; k++) {
						cuts[i][j] = Math.min(cuts[i][j], cuts[i][k] + cuts[k + 1][j] + 1);
					}
				}
			}
		}

		return cuts[0][n - 1];
	}

	public static void main(String[] args) {
		String s = "ababbbabbababa";
		System.out.println(minPalindromePartition(s));
		
		String ss = "xaabbaayyy";
		System.out.println(minPalindromePartition(ss));
	}

}
