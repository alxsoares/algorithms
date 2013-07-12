package alex.algorithms.math.projecteuler;

public class Euler434 {
	 private static final int MOD = 1000000033;

	  public static void main(String[] args) {
	    System.out.println(solve(100));
	  }

	  static public long solve(int n) {
	    long sum = 0L;
	    for (int a = 1; a <= n; a++) {
	      for (int b = 1; b <= n; b++) {
	        sum = (sum + connected(a, b)) % MOD;
	      }
	    }
	    return sum;
	  }

	  static private Long[][] connectedCache = new Long[101][101];

	  static private long connected(int m, int n) {
	    if (connectedCache[m][n] != null) {
	      return connectedCache[m][n];
	    }
	    return connectedCache[m][n] =
	        (allGraphs(m, n) - unconnected(m, n) + MOD) % MOD;
	  }

	  static private long unconnected(int m, int n) {
	    long sum = 0L;
	    for (int a = 1; a <= m; a++) {
	      for (int b = 0; b <= n; b++) {
	        if (a == m && b == n) continue;
	        sum = (sum + comb(m - 1, a - 1) * comb(n, b) % MOD
	            * connected(a, b) % MOD
	            * allGraphs(m - a, n - b) % MOD) % MOD;
	      }
	    }
	    return sum;
	  }

	  static private long allGraphs(int m, int n) {
	    return pow2(m * n);
	  }

	  static private Long[] pow2Cache = new Long[10001];

	  static private long pow2(int n) {
	    if (n == 0) {
	      return 1L;
	    }
	    if (pow2Cache[n] != null) {
	      return pow2Cache[n];
	    }
	    return pow2Cache[n] = 2L * pow2(n - 1) % MOD;
	  }

	 static private Long[][] combCache = new Long[101][101];

	  static private long comb(int n, int k) {
	    if (k == 0 || k == n) {
	      return 1L;
	    }
	    if (combCache[n][k] != null) {
	      return combCache[n][k];
	    }
	    return combCache[n][k] = (comb(n - 1, k - 1) + comb(n - 1, k)) % MOD;
	  }
}
