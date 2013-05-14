package alex.algorithms.math.projecteuler.level2;

public class Euler031 {

	private static final int MAXTOTAL = 1000;

	public static int changeR(int[] coins, int total, int j) {
		if (total == 0)
			return 1;
		if (j >= coins.length)
			return 0;
		if (total < 0)
			return 0;
		return changeR(coins, total - coins[j], j)
				+ changeR(coins, total, j + 1);
	}

	public static void main(String[] args) {
		int[] coins = { 1, 2, 5, 10, 20, 50, 100, 200 };
		long nway[] = new long[MAXTOTAL + 1];
		int total = 200;
		nway[0] = 1;
		for (int i = 0; i < coins.length; i++) {
			int c = coins[i];
			for (int j = c; j <= total; j++)
				nway[j] += nway[j - c];
		}
		System.out.printf("%d\n", nway[total]);
		System.out.printf("%d\n", changeR(coins, total, 0));
	}

}
