package topcoder.alex;

import static java.lang.Math.*;

/**
 * "Consider the following two-player game played with a sequence of N positive
 * integers (2 <= N <= 100) laid onto a game board. Player 1 starts the game.
 * The players move alternately by selecting a number from either the left or
 * the right end of the sequence. That number is then deleted from the board,
 * and its value is added to the score of the player who selected it. A player
 * wins if his sum is greater than his opponents.
 * 
 * Write a program that implements the optimal strategy. The optimal strategy
 * yields maximum points when playing against the "best possible" opponent. Your
 * program must further implement an optimal strategy for player 2."
 * 
 * 
 * D(n,m) = the best score for player 1 in which the board state is a contiguous
 * subsequence between index n and m. Player 1's turn. E(n,m) = the best score
 * for player 1 in which the board state is a contiguous subsequence between
 * index n and m. Player 2's turn.
 * 
 */
public class MinMaxGameDP {
	static Integer[][] dp = new Integer[101][101];
	static int[] A;

	static int func(int n, int m) {
		if (n == m)
			return dp[n][m];
		if (n > m)
			return 0;
		if (dp[n][m] != null)
			return dp[n][m];
		int res = 0;
		res = max(min(func(n + 2, m), func(n + 1, m - 1)) + A[n],
				min(func(n + 1, m - 1), func(n, m - 2)) + A[m]);
		return dp[n][m] = res;
	}

	public static void main(String[] args) {

	}

}
