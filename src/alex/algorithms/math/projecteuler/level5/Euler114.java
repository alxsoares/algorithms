package alex.algorithms.math.projecteuler.level5;

public class Euler114 {
	static long[] memo;

	public static void main(String[] args) {
		int m = 50;
		int n = 3;
		memo = new long[m + 1];
		long solutions = fillCount(m, n);
		System.out.println(solutions);
	}

	private static long fillCount(int m, int n) {

		long solutions = 1;

		if (n > m)
			return solutions;

		if (memo[m] != 0)
			return memo[m];

		for (int startpos = 0; startpos <= m - n; startpos++) {
			for (int blocklength = n; blocklength <= m - startpos; blocklength++) {
				solutions += fillCount(m - startpos - blocklength - 1, n);
			}
		}

		memo[m] = solutions;
		return solutions;
	}
}
