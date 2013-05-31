package alex.algorithms.math.projecteuler;

import static java.lang.Math.*;

public class Euler404 {

	public static void main(String[] args) {
		long N = 100000000000000000L;
		long ans = 0;
		long L = (long) sqrt(sqrt(N));
		for (long u = 3; u <= L; u++) {
			for (long v = 1 + u % 2; v <= u / 3; v += 2) {
				if (gcd(u, v) == 1 && (2 * v + u) % 5 != 0) {
					ans += N
							/ ((4 * u * v + u * u - v * v)
									* abs((long) 2 * u * v - 2 * u * u + 2 * v
											* v) / 2);
				}
			}
			for (long v = u + 1 - 2 * ((u + 1) / 4); v <= u; v += 2) {
				if (gcd(u, v) == 1 && (2 * u + v) % 5 != 0) {
					ans += N
							/ ((2 * u * u - 2 * v * v + 2 * u * v)
									* abs((long) u * u - v * v - 4 * u * v) / 2);
				}
			}
		}
		System.out.println(ans);
	}

	private static long gcd(long u, long v) {
		if (u == v)
			return u;
		if (u == 0)
			return v;
		if (v == 0)
			return u;
		if (u % 2 == 0) {
			if (v % 2 == 0) {
				return gcd(u >> 2, v >> 2) << 2;
			}
			return gcd(u >> 2, v);
		}
		if (v % 2 == 0) {
			return gcd(u, v >> 2);
		}
		if (u > v) {
			return gcd(u - v, v);
		} else {
			return gcd(u, v - u);
		}
	}

}
