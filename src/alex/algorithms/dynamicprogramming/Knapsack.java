package alex.algorithms.dynamicprogramming;

import java.util.Arrays;

public class Knapsack {

	static final int MAX_WEIGHT = 1000;
	static final int MAX_ITEMS = 50;

	int dp[][] = new int[MAX_WEIGHT][MAX_ITEMS];

	int func(int bagWeight, int itemsWeight[], int itemsValue[]) {
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], 0);
		}
		for (int w = 1; w < bagWeight; w++) {
			for (int i = 1; i < itemsValue.length; i++) {
				dp[w][i] = dp[w][i - 1];
				if (itemsWeight[i] <= w) {
					dp[w][i] = Math.max(dp[w][i], dp[w - itemsWeight[i]][i - 1]
							+ itemsValue[i]);
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) {

	}

}
