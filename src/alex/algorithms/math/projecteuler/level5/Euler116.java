package alex.algorithms.math.projecteuler.level5;

import java.util.Arrays;

public class Euler116 {
	static long dp[]=new long[51];

	private static long G(int m, int n) {
		long s = 0;
		if (n > m)
			return s;
		if (dp[m] != 0)
			return dp[m];
		for (int pos = 0; pos + n <= m; pos++) {
			s++;
			s += G(m - pos - n, n);
		}
		dp[m] = s;
		return dp[m];
	}

	public static void main(String[] args) {
		long sol=0;
		for(int i=2;i<=4;i++){
			Arrays.fill(dp, 0);
			sol+=G(50,i);
		}
		System.out.printf("%d\n", sol);
	}

}
