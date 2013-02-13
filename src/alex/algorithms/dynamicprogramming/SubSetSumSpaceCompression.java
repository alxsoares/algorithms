package alex.algorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

public class SubSetSumSpaceCompression {

	private static final int M = 10000;
	static int dp[][] = new int[2][M + 1];

	static void sum(ArrayList<Integer> itens, int totalSum) {
		Arrays.fill(dp, -1);
		for (int i = 1; i < M + 1; i++) {
			dp[0][i] = 0;
		}
		dp[0][0] = 1;
		for (int i = 1; i <= itens.size(); i++) {
			for (int j = 0; j <= totalSum; j++) {
				dp[i & 1][j] = dp[(i - 1) & 1][j];
				if (j - itens.get(i - 1) >= 0) {
					// decisao: usar ou nao item i para achar soma de exatamente
					// j
					// F(i, k) = max { F(i-1, k - A[i]), F(i-1, k) }
					dp[i & 1][j] = Math.max(dp[i & 1][j], dp[(i - 1) & 1][j
							- itens.get(i - 1)]);
				}
			}
		}
	}

	public static void main(String[] args) {

	}

}
