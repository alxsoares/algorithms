//package alex.algorithms.math.projecteuler;
//
//public class Euler427b {
//	public static void main(final String[] args) {
//		System.out.println(String.valueOf(f(1000000009L, 3, 3L)));
//		System.out.println(String.valueOf(f(1000000009L, 7, 7L)));
//		System.out.println(String.valueOf(f(1000000009L, 11, 11L)));
//		System.out.println(String.valueOf(f(1000000009L, 7500000, 7500000L)));
//	}
//
//	/**
//	 * @param mod
//	 *            modulo; assumed prime
//	 * @param n
//	 *            sequence length
//	 * @param v
//	 *            alphabet size
//	 * @return sum of scores (modulo mod) for all sequences of length n over an
//	 *         alphabet of size v
//	 */
//	public static long f(final long mod, final int n, final long v) {
//		// Precalculate factorials and their inverses.
//		final long[] fact = new long[n + 1];
//		final long[] inv_fact = new long[n + 1];
//		fact[0] = 1L;
//		inv_fact[0] = 1L;
//		for (int i = 0; i < n; i++) {
//			fact[i + 1] = ((long) (i + 1) * fact[i]) % mod;
//			inv_fact[i + 1] = Euler.inverse(mod, fact[i + 1]);
//		}
//
//		// Consider sequence (of length n-1) of successive differences (mod v)
//		// between terms.
//		// A zero in that sequence corresponds to equal adjacent terms in the
//		// original sequence.
//		// i consecutive zeros corresponds to a run of length i+1 in the
//		// original sequence.
//		long sum = 0L;
//		int i = 0;
//		while (i <= n - 1) {
//			// How many sequences have at most i consecutive zero?
//
//			// Want to take x[n-1] in the following, but it's too slow to
//			// compute naïvely:
//			// x[i] = i == 0 ? 1L : (v * x[i-1]) % mod;
//			// for (int j = i+1; j <= n-1; j ++)
//			// x[j] = (v * x[j-1] + (j==i+1 ? mod-1L : (mod-(v-1L)) * x[j-i-2]))
//			// % mod;
//
//			// Transform y_{j} = v^j x_{j}
//			// y_{j} = y_{j-1} - c y_{n-2-i}
//			// where c = (v-1)/v^{2+i}
//			final long c = Euler
//					.quotient(mod, v - 1L, modPow(mod, v, 2 + i));
//
//			// Let y_{j} = (v z_{j+1} - z_{j})/(v-1)
//			// z satisfies same recurrence, but has simpler starting condition :
//			// z_{0} = 1; z_{-ve} = 0
//			// z_n = sum_j binom(n-(i+2)j,j) (-c)^j
//
//			final long zn = zk(mod, fact, inv_fact, i, c, n);
//			final long zn1 = zk(mod, fact, inv_fact, i, c, n - 1);
//			final long yn1 = (((v * zn + mod - zn1) % mod) * Euler.inverse(mod,
//					v - 1)) % mod;
//			final long xn1 = (yn1 * modPow(mod, v, n - 1)) % mod;
//
//			sum += xn1;
//			sum %= mod;
//			++i;
//			if (i < 100 || i < 1000 && i % 10 == 0 || i % 100 == 0)
//				System.out.println("[" + String.valueOf(i) + "  "
//						+ String.valueOf(sum) + "]");
//		}
//
//		// So far we have scored n-i for a sequence whose longest run has length
//		// i+1.
//		// Transform to a complementary scoring for the problem.
//		final long comp_sum = (modPow(mod, v, n - 1) * (n + 1) + mod - sum)
//				% mod;
//
//		// First term may take any value.
//		return (v * comp_sum) % mod;
//	}
//
//	private static long zk(final long mod, final long[] fact,
//			final long[] inv_fact, final int i, final long c, final int k) {
//		long zn = 0L;
//		long pow = 1L;
//		for (int j = 0; k - (i + 1) * j >= j; j++) {
//			// Binomial coefficient ( k-(i+1)j , j ).
//			final long binom = (((fact[k - (i + 1) * j] * inv_fact[j]) % mod) * inv_fact[k
//					- (i + 1) * j - j])
//					% mod;
//			zn += pow * binom;
//			zn %= mod;
//			pow = ((mod - c) * pow) % mod;
//		}
//		return zn;
//	}
//	 static long modPow(long b, long e, final long m) {
//	        long r = 1;
//	        while (e > 0) {
//	            if ((e & 1) != 0)
//	                r = r * b % m;
//	            {
//	                b = b * b % m;
//	                e >>= 1;
//	            }
//	        }
//	        return r;
//	    }
//
//}
