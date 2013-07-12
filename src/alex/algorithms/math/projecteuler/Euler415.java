package alex.algorithms.math.projecteuler;

import java.util.HashMap;
import java.util.Map;

public class Euler415 {
	private static final long MOD = power(10, 8);
	private static final long MOD2 = 2 * MOD;
	private static final long MOD6 = 6 * MOD;
	private static final long n = power(10, 11);
	private static final long N = n + 1;
	private static Map<Long, Long> aCache = new HashMap<Long, Long>();
	private static Map<Long, Long> bCache = new HashMap<Long, Long>();
	private static Map<Long, Long> cCache = new HashMap<Long, Long>();

	public static long powerMod(long p, long n, long mod) {
		long result = 1;
		while (n != 0) {
			if ((n & 1) != 0)
				result = result * p % mod;
			p = p * p % mod;
			n >>= 1;
		}
		return result;
	}

	public static long power(long p, long n) {
		if (n < 0)
			throw new RuntimeException();
		long result = 1;
		while (n != 0) {
			if ((n & 1) != 0)
				result *= p;
			p *= p;
			n >>= 1;
		}
		return result;
	}

	private static long a(long n) // using mobius inversion
	{
		Long result = aCache.get(n);
		if (result != null)
			return result;
		long sum = A(n);
		for (long dmax = n; dmax > 1;) {
			long r = n / dmax;
			long dmin = n / (r + 1); // exclusive
			if (dmin < 1)
				dmin = 1;
			sum = (sum - (dmax - dmin) % MOD * a(r) % MOD + MOD) % MOD;
			dmax = dmin;
		}
		aCache.put(n, sum);
		return sum;
	}

	private static long A(long n) {
		long sum = 0;
		for (long gmax = n; gmax >= 1;) {
			long ng = n / gmax;
			long gmin = n / (ng + 1);
			if (gmin < 0)
				gmin = 0;
			long m = gmax % MOD;
			long c2 = powerMod(2, gmax, MOD) - m - 1;
			m = gmin % MOD;
			long c1 = powerMod(2, gmin, MOD) - m - 1;
			sum = (sum + (c2 - c1 + 2 * MOD) % MOD * (ng % MOD) % MOD
					* (ng % MOD) * 2)
					% MOD;
			gmax = gmin;
		}
		return sum;
	}

	private static long b(long n) {
		Long result = bCache.get(n);
		if (result != null)
			return result;
		long sum = B(n);
		for (long dmax = n; dmax > 1;) {
			long r = n / dmax;
			long dmin = n / (r + 1); // exclusive
			if (dmin < 1)
				dmin = 1;
			long c2 = dmax % MOD2 * ((dmax + 1) % MOD2) / 2 % MOD;
			long c1 = dmin % MOD2 * ((dmin + 1) % MOD2) / 2 % MOD;
			sum = (sum - (c2 - c1 + 2 * MOD) % MOD * b(r) % MOD + MOD) % MOD;
			dmax = dmin;
		}
		bCache.put(n, sum);
		return sum;
	}

	private static long B(long n) {
		long sum = 0;
		for (long gmax = n; gmax >= 1;) {
			long ng = n / gmax;
			long gmin = n / (ng + 1);
			if (gmin < 0)
				gmin = 0;
			long m = gmax % MOD2;
			long c2 = (powerMod(2, gmax + 1, MOD2) - m - 2 + 2 * MOD2) % MOD2
					* (m - 1 + MOD2) / 2 % MOD;
			m = gmin % MOD2;
			long c1 = (powerMod(2, gmin + 1, MOD2) - m - 2 + 2 * MOD2) % MOD2
					* (m - 1 + MOD2) / 2 % MOD;
			sum = (sum + (c2 - c1 + 2 * MOD) % MOD * (ng % MOD) % MOD
					* (ng % MOD) % MOD * ((ng + 1) % MOD) * 2)
					% MOD;
			gmax = gmin;
		}
		sum = (MOD - sum) % MOD;
		return sum;
	}

	private static long c(long n) {
		Long result = cCache.get(n);
		if (result != null)
			return result;
		long sum = C(n);
		for (long dmax = n; dmax > 1;) {
			long r = n / dmax;
			long dmin = n / (r + 1); // exclusive
			if (dmin < 1)
				dmin = 1;
			long c2 = dmax % MOD6 * ((dmax + 1) % MOD6) % MOD6
					* ((2 * dmax + 1) % MOD6) / 6 % MOD;
			long c1 = dmin % MOD6 * ((dmin + 1) % MOD6) % MOD6
					* ((2 * dmin + 1) % MOD6) / 6 % MOD;
			sum = (sum - (c2 - c1 + 2 * MOD) % MOD * c(r) % MOD + MOD) % MOD;
			dmax = dmin;
		}
		cCache.put(n, sum);
		return sum;
	}

	private static long C(long n) {
		long sum = 0;
		for (long gmax = n; gmax >= 1;) {
			long ng = n / gmax;
			long gmin = n / (ng + 1);
			if (gmin < 0)
				gmin = 0;
			long m = gmax % MOD;
			long c2 = (((m - 2 + MOD) * m + 3) % MOD * powerMod(2, gmax, MOD)
					% MOD
					- ((((2 * m) % MOD6 + 3) * m % MOD6 + 1) * m % MOD6 + 18)
					/ 6 % MOD + MOD)
					% MOD;
			m = gmin % MOD;
			long c1 = (((m - 2 + MOD) * m + 3) % MOD * powerMod(2, gmin, MOD)
					% MOD
					- ((((2 * m) % MOD6 + 3) * m % MOD6 + 1) * m % MOD6 + 18)
					/ 6 % MOD + MOD)
					% MOD;
			sum = (sum + 2 * (c2 - c1 + 2 * MOD) % MOD * ng % MOD2 * (ng + 1)
					/ 2 % MOD * ng % MOD2 * (ng + 1) / 2)
					% MOD;
			gmax = gmin;
		}
		return sum;
	}

	public static void main(String[] args) {
		long x0 = powerMod(2, N % MOD * (N % MOD) + MOD, MOD); // all point
																// sets, 2^n
																// periodic
																// 4*5^7 if n >
																// 8
		long x1 = (1 + N % MOD * (N % MOD)) % MOD; // 0 and 1 point sets
		long x2 = (powerMod(2, N, MOD) - 1 - N % MOD - N % MOD2 * (n % MOD2)
				/ 2 % MOD + 3 * MOD)
				% MOD * (2 * N % MOD) % MOD; // 3+ colinear horizontal/vertical
		long a = a(N); // Part #1 of diagonal
		long b = b(N); // Part #2 of diagonal
		long c = c(N); // Part #3 of diagonal
		long sum = (x0 - x1 - x2 - a * (N % MOD) % MOD * (N % MOD) % MOD - b
				* (N % MOD) % MOD - c + 5 * MOD)
				% MOD;
		System.out.println(sum);
	}
}
