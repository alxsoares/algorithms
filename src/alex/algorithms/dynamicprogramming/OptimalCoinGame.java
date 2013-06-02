package alex.algorithms.dynamicprogramming;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.out;

//@formatter:off
/**
* http://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
* 
* F(i, j)  represents the maximum value the user can collect from 
*         i'th coin to j'th coin.
*
*    F(i, j)  = Max(Vi + min(F(i+2, j), F(i+1, j-1) ), 
*                   Vj + min(F(i+1, j-1), F(i, j-2) )) 
*Base Cases
*    F(i, j)  = Vi           If j == i
*    F(i, j)  = max(Vi, Vj)  If j == i+1
*
*/
//@formatter:on
public class OptimalCoinGame {
	public static int optimalStrategyOfGame(int coins[]) {
		int n = coins.length;
		int dp[][] = new int[n][n];
		for (int gap = 0; gap < n; gap++) {
			for (int i = 0, j = gap; j < n; j++, i++) {

				int x = ((i + 2) <= j) ? dp[i + 2][j] : 0;
				int y = ((i + 1) <= (j - 1)) ? dp[i + 1][j - 1] : 0;
				int z = (i <= (j - 2)) ? dp[i][j - 2] : 0;

				dp[i][j] = max(coins[i] + min(x, y), coins[j] + min(y, z));
			}
		}

		return dp[0][n - 1];
	}

	public static void main(String[] args) {
		int arr1[] = { 8, 15, 3, 7 };
		out.printf("%d\n", optimalStrategyOfGame(arr1));

		int arr2[] = { 2, 2, 2, 2 };
		out.printf("%d\n", optimalStrategyOfGame(arr2));

		int arr3[] = { 20, 30, 2, 2, 2, 10 };
		out.printf("%d\n", optimalStrategyOfGame(arr3));
	}

}
