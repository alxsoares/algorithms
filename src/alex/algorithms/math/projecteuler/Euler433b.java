package alex.algorithms.math.projecteuler;

import topcoder.alex.misc.Pair;

public class Euler433b {
	public static void main(final String[] args) {
		System.out.println(String.valueOf(calculator(10).s(10)));
		System.out.println(String.valueOf(calculator(10).s(100)));
		final Calculator calculator = calculator(10000);
		System.out.println("...");
		System.out.println(String.valueOf(calculator.s(5000000)));
	}

	public interface Calculator {
		long s(int n);
	}

	public static Calculator calculator(final int cacheSize) {
		final long[][] cache = new long[cacheSize + 1][];
		final long[] cacheRowSums = new long[cacheSize + 1];
		for (int y = 1; y <= cacheSize; y++) {
			cache[y] = new long[y];
			long rowSum = 0L;
			for (int x = 0; x < y; x++) {
				cache[y][x] = rowSum;
				rowSum += e(x, y);
			}
			cacheRowSums[y] = rowSum;
		}
		return new Calculator() {
			private long progress = 0;
			private long milestone = 0;

			/**
			 * @return [count, sum of e] in the triangle bounded by [0,0],
			 *         [np/q,0], [nr/s,nr/s], or equivalently by [0,0],
			 *         [np/q,0], [nr/s,0], inclusive of all edges except for the
			 *         x-axis
			 */
			private Pair<? extends Long, ? extends Long> g(final int n,
					final long p, final long q, final long r, final long s) {
				if (n * r <= s * cacheSize) {
					long count = 0L;
					long sum = 0L;
					for (int y = 1; true; y++) {
						// x = floor((nr/s-y)*(np/q) / (nr/s))
						final long numer = n * r - y * s;
						if (numer < 0)
							break;
						final int x = (int) (numer * p / (q * r));
						count += x + 1;
						sum += ((x + 1) / y) * cacheRowSums[y]
								+ cache[y][(x + 1) % y];
					}
					progress += count;
					if (progress > milestone) {
						System.out.println("[" + String.valueOf(milestone)
								+ "]");
						milestone += 10000000000L;
					}
					return Pairs.of(count, sum);
				} else {
					// Partition on the line y=x, and recurse.
					// Intersection is at [nt/u,nt/u] where u/t = q/p + s/r.
					final long t = p * r;
					final long u = p * s + q * r;
					// Firstly, y <= x.
					final Pair<? extends Long, ? extends Long> lo = g(n, p, q,
							t, u);
					// Secondly, y > x. Flip about y=x.
					final Pair<? extends Long, ? extends Long> hi = g(n, r, s,
							t, u);
					// Need to adjust hi to include the y-axis and exclude y=x.
					return Pairs.of(lo.first() + hi.first() + (n * r) / s
							- (n * t) / u,
							lo.second() + hi.second() + hi.first() + (n * r)
									/ s - 2 * ((n * t) / u));
				}
			}

			@Override
			public long s(final int n) {
				progress = 0;
				milestone = 0;

				// return 2L * g(n, 1, 1, 1, 1).first() - n;
				return 2L * g(n, 1, 1, 1, 1).second()
						+ ((long) n * (long) (n - 3L)) / 2L;
			}
		};
	}

	public static int e(int a, int b) {
		int count = 0;
		while (b > 0) {
			final int r = a % b;
			a = b;
			b = r;
			++count;
		}
		return count;
	}
}
