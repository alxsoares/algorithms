package topcoder.alex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Problem Statement You have a piece of paper with exactly D positions laid out
 * in a horizontal row. Each position looks like the following:
 * 
 * _ |_| |_|
 * 
 * There are 7 line segments in each position, and each line segment can hold
 * exactly one match. Matches cannot be placed anywhere except on the line
 * segments.
 * 
 * You are given an integer N containing exactly D digits (with no leading
 * zeroes). Spell out the number using matches on the paper. Each digit must
 * occupy a single position. The following diagram shows how each digit should
 * be formed:
 * 
 * _ _ _ _ _ _ _ _ 0 - | | 1 - | 2 - _| 3 - _| 4 - |_| 5 - |_ 6 - |_ 7 - | 8 -
 * |_| 9 - |_| |_| _| |_ _| | _| |_| | |_| _|
 * 
 * After you lay out the initial arrangement, you are allowed to move up to K
 * matches. You cannot discard matches or add new matches. After you make all
 * your moves, the final arrangement must be valid (as described above) and the
 * integer formed by the arrangement must contain the same number of digits as
 * the original integer. Leading zeroes are allowed. Return the number of
 * distinct integers that can be formed in this manner. Note that the original
 * integer counts toward the total because it always obtainable by making 0
 * moves.
 * 
 * Definition
 * 
 * Class: NumbersAndMatches Method: differentNumbers Parameters: long, int
 * Returns: long Method signature: long differentNumbers(long N, int K) (be sure
 * your method is public)
 * 
 * 
 * Constraints - N will be between 1 and 10^18 - 1, inclusive.
 * 
 * - K will be between 1 and 126, inclusive.
 * 
 * 
 * 
 */
public class NumbersAndMatches {

	class Pair<K, V> implements Entry<K, V> {

		@Override
		public K getKey() {
			return null;
		}

		@Override
		public V getValue() {
			return null;
		}

		@Override
		public V setValue(V value) {
			return null;
		}

	}

	private static final int SURPLUS_MOD = 72;

	// @formatter:off
	int display[][] = {//
			{ 1, 1, 1, 0, 1, 1, 1 }, // 0
			{ 0, 0, 1, 0, 0, 1, 1 }, // 1
			{ 1, 0, 1, 1, 1, 0, 1 }, // 2
			{ 1, 0, 1, 1, 0, 1, 1 }, // 3
			{ 0, 1, 1, 1, 0, 1, 0 }, // 4
			{ 1, 1, 0, 1, 0, 1, 1 }, // 5
			{ 1, 1, 0, 1, 1, 1, 1 }, // 6
			{ 1, 0, 1, 0, 0, 1, 0 }, // 7
			{ 1, 1, 1, 1, 1, 1, 1 }, // 8
			{ 1, 1, 1, 1, 0, 1, 1 } // 9
	};
	// @formatter:on
	int total[] = { 6, 3, 5, 5, 4, 5, 6, 3, 7, 6 };// numero de fosforos para
													// digito i
	long dp[][][] = new long[19][155][155];
	int cost[][] = new int[10][10];
	int sd[][] = new int[10][10];
	String num;

	public long differentNumbers(long N, int K) {
		long res = 0;
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		// compute the cost function for every digit -> digit transition
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				int c = 0;
				for (int k = 0; k < 7; k++) {
					if (display[i][k] != display[j][k])
						c++;
				}
				int s = total[i] - total[j];
				cost[i][j] = (c + s) / 2;
				sd[i][j] = s;
			}
		}
		
		// decompose the number into a string
		num="";
		long vN = N;
		while (vN > 0) {
			num += (vN % 10);
			vN /= 10;
		}
		num = reverse(num);
		// calculate the number of ways
		return res = func(0, K, 0);

	}

	String reverse(String s) {
		char[] chars = s.toCharArray();
		int l = chars.length;
		for (int i = 0; i < l / 2; i++) {
			char aux = chars[i];
			chars[i] = chars[l - i - 1];
			chars[l - i - 1] = aux;
		}
		return new String(chars);
	}

	long func(int idx, int moves, int surplus) {
		// offset due to the possibility of negative surplus
		int surplusMod = surplus + SURPLUS_MOD;
		if (idx >= num.length()) {
			// requires the surplus amount of matches to be 0 otherwise its not
			// valid
			if (moves >= 0 && surplus == 0)
				return 1;
			return 0;
		}
		if (dp[idx][moves][surplusMod] != -1)
			return dp[idx][moves][surplusMod];
		long res = 0;
		// try each digit
		int curDig = num.charAt(idx) - '0';
		for (int dig = 0; dig <= 9; dig++) {
			int c = cost[curDig][dig];
			int d = sd[curDig][dig];
			if (moves - c >= 0) {
				res += func(idx + 1, moves - c, surplus + d);
			}
		}
		return dp[idx][moves][surplusMod] = res;
	}

	public static void main(String[] args) {
		NumbersAndMatches nm = new NumbersAndMatches();
		System.out.printf("%d\n", nm.differentNumbers(888888888, 100));
		System.out.printf("%d\n", nm.differentNumbers(444444444, 2));
		System.out.printf("%d\n", nm.differentNumbers(66, 2));
	}

}
