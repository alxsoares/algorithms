package alex.algorithms.dynamicprogramming;

import java.util.Arrays;

public class Knapsack {

    static final int MAX_WEIGHT = 1000;
    static final int MAX_ITEMS = 50;

    int dp[][] = new int[MAX_WEIGHT][MAX_ITEMS];

    int func(final int bagWeight, final int itemsWeight[], final int itemsValue[]) {
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 0);
        }
        int max = Integer.MIN_VALUE;
        for (int w = 1; w < bagWeight; w++) {
            for (int i = 1; i < itemsValue.length; i++) {
                dp[w][i] = dp[w][i - 1];
                if (itemsWeight[i] <= w) {
                    dp[w][i] = Math.max(dp[w][i], dp[w - itemsWeight[i]][i - 1] + itemsValue[i]);
                    if (dp[w][i] > max) {
                        max = dp[w][i];
                    }
                }
            }
        }
        return dp[bagWeight - 1][itemsWeight.length - 1];
    }

    public static void main(final String[] args) {

        int[] intemsWeight = { 1, 1, 2, 3, 4, 5, 5 };
        int[] intemsValue = { 5, 5, 5, 5, 4, 5, 6 };
        Knapsack ks = new Knapsack();
        System.out.printf("%d\n", ks.func(15, intemsWeight, intemsValue));

    }

}
