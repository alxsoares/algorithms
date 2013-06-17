package alex.algorithms.math.projecteuler.level5;

public class Euler117 {
	static long dp[] = new long[51];

	private static long F(int m, int nmin, int nmax) {
		long solutions = 1;

		if (nmin > m)
			return solutions;

		if (dp[m] != 0)
			return dp[m];
		
		for (int bs = nmin; bs <= nmax; bs++) {
			for (int startpos = 0; startpos <= m - bs; startpos++) {
				solutions += F(m - startpos - bs, nmin, nmax);
			}
		}

		dp[m] = solutions;
		return dp[m];
	}

	public static void main(String[] args) {
		int m = 50;
		int nmax = 4;
		int nmin = 2;

		long solutions = F(m, nmin, nmax);
		System.out.printf("%d\n", solutions);
	}

}
