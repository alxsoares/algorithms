package alex.algorithms.math.projecteuler;

import java.util.HashMap;
import java.util.Map;

public class Euler415 {
	private static final long MOD = (long) 10e8;
	private static final long MOD2 = 2 * MOD;
	private static final long MOD6 = 6 * MOD;
	private static final long n = (long) 10e11;
	private static final long N = n + 1;
	private static Map<Long, Long> aMEMO = new HashMap<Long, Long>();
	private static Map<Long, Long> bMEMO = new HashMap<Long, Long>();
	private static Map<Long, Long> cMEMO = new HashMap<Long, Long>();

	public static long modPow(long p, long n, long mod) {
		long result = 1;
		while (n != 0) {
			if ((n & 1) != 0)
				result = (result * p) % mod;
			p = (p * p) % mod;
			n >>= 1;
		}
		return result;
	}

	
	private static long a(long n) {
		Long result = aMEMO.get(n);
		if (result != null)
			return result;
		long sum = A(n);
		for (long dmax = n; dmax > 1;) {
			long r = n / dmax;
			long dmin = n / (r + 1);
			if (dmin < 1)
				dmin = 1;
			sum = (sum - (dmax - dmin) % MOD * a(r) % MOD + MOD) % MOD;
			dmax = dmin;
		}
		aMEMO.put(n, sum);
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
			long c2 = modPow(2, gmax, MOD) - m - 1;
			m = gmin % MOD;
			long c1 = modPow(2, gmin, MOD) - m - 1;
			sum = (sum + (c2 - c1 + 2 * MOD) % MOD * (ng % MOD) % MOD
					* (ng % MOD) * 2)
					% MOD;
			gmax = gmin;
		}
		return sum;
	}

	private static long b(long n) {
		Long result = bMEMO.get(n);
		if (result != null)
			return result;
		long sum = B(n);
		for (long dmax = n; dmax > 1;) {
			long r = n / dmax;
			long dmin = n / (r + 1);
			if (dmin < 1)
				dmin = 1;
			long c2 = dmax % MOD2 * ((dmax + 1) % MOD2) / 2 % MOD;
			long c1 = dmin % MOD2 * ((dmin + 1) % MOD2) / 2 % MOD;
			sum = (sum - (c2 - c1 + 2 * MOD) % MOD * b(r) % MOD + MOD) % MOD;
			dmax = dmin;
		}
		bMEMO.put(n, sum);
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
			long c2 = (modPow(2, gmax + 1, MOD2) - m - 2 + 2 * MOD2) % MOD2
					* (m - 1 + MOD2) / 2 % MOD;
			m = gmin % MOD2;
			long c1 = (modPow(2, gmin + 1, MOD2) - m - 2 + 2 * MOD2) % MOD2
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
		Long result = cMEMO.get(n);
		if (result != null)
			return result;
		long sum = C(n);
		for (long dmax = n; dmax > 1;) {
			long r = n / dmax;
			long dmin = n / (r + 1);
			if (dmin < 1)
				dmin = 1;
			long c2 = dmax % MOD6 * ((dmax + 1) % MOD6) % MOD6
					* ((2 * dmax + 1) % MOD6) / 6 % MOD;
			long c1 = dmin % MOD6 * ((dmin + 1) % MOD6) % MOD6
					* ((2 * dmin + 1) % MOD6) / 6 % MOD;
			sum = (sum - (c2 - c1 + 2 * MOD) % MOD * c(r) % MOD + MOD) % MOD;
			dmax = dmin;
		}
		cMEMO.put(n, sum);
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
			long c2 = (((m - 2 + MOD) * m + 3) % MOD * modPow(2, gmax, MOD)
					% MOD
					- ((((2 * m) % MOD6 + 3) * m % MOD6 + 1) * m % MOD6 + 18)
					/ 6 % MOD + MOD)
					% MOD;
			m = gmin % MOD;
			long c1 = (((m - 2 + MOD) * m + 3) % MOD * modPow(2, gmin, MOD)
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
		long x0 = modPow(2, N % MOD * (N % MOD) + MOD, MOD);
		long x1 = (1 + N % MOD * (N % MOD)) % MOD; //
		long x2 = (modPow(2, N, MOD) - 1 - N % MOD - N % MOD2 * (n % MOD2)
				/ 2 % MOD + 3 * MOD)
				% MOD * (2 * N % MOD) % MOD;
		long a = a(N);
		long b = b(N);
		long c = c(N);
		long sum = (x0 - x1 - x2 - a * (N % MOD) % MOD * (N % MOD) % MOD - b
				* (N % MOD) % MOD - c + 5 * MOD)
				% MOD;
		System.out.println(sum);
	}
}
