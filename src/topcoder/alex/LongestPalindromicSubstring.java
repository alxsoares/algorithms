package topcoder.alex;

import java.util.Arrays;

public class LongestPalindromicSubstring {
	int max = 1000;
	boolean[][] DP = new boolean[max][max];

	public String longest(String s) {
		int n = s.length();
		int longestBegin = 0;
		int maxLen = 1;

		for (int i = 0; i < DP.length; i++) {
			Arrays.fill(DP[i], false);
		}
		for (int i = 0; i < n; i++) {
			DP[i][i] = true;
		}
		for (int i = 1; i < n - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				DP[i][i + 1] = true;
				longestBegin = i;
				maxLen = 2;
			}
		}
		for (int len = 3; len <= n; len++) {
			for (int i = 0; i < n - len + 1; i++) {
				int j = i + len - 1;
				if (s.charAt(i) == s.charAt(j) && DP[i + 1][j - 1]) {
					DP[i][j] = true;
					longestBegin = i;
					maxLen = len;
				}
			}
		}
		return s.substring(longestBegin,maxLen+1);
	}

	public static void main(String[] args) {
		LongestPalindromicSubstring l = new LongestPalindromicSubstring();
		System.out.println(l.longest("aBccAAAAAAAAAccBksdlskd"));
	}

}
