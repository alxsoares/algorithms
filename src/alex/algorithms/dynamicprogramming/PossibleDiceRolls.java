package alex.algorithms.dynamicprogramming;

public class PossibleDiceRolls {
	/**
	 * Nways(s, n) = Summation{1..a} Nways(s-i, n-1) Base case: Nways(s, 1) = 1
	 * if (s<= a && s>0), 0 otherwise.
	 */
	public static int possibleDiceRolls(int S, int A, int N) {
		int[][] sum = new int[S + 1][N + 1];
		for (int i = 1; i <= S && i <= A; i++) {
			sum[i][1] = 1;
		}
		for (int n = 2; n <= N; n++) {
			for (int i = 1; i <= S; i++) {
				for (int j = 1; j <= A; j++) {
					if (i > j) {
						sum[i][n] += sum[i - j][n - 1];
					}
				}
			}
		}

		return sum[S][N];
	}

	public static void main(String[] args) {
		System.out.println(possibleDiceRolls(6, 6, 3));
	}

}
