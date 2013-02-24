package alex.algorithms.math;

public class Math {
	private static final double EPSLON = Double.MIN_VALUE;

	public static void IntegerDivision(int numerator, final int denominator) {
		int quot = 0;
		while (numerator > denominator) {
			numerator = numerator - denominator;
			quot++;
		}
		int rest = numerator;

		System.out.println("Quotient=[" + quot + "] and Denominator=[" + rest
				+ "]");
	}

	public static double sqrt(final double n) {
		double x0 = 6 * 100000;
		double variation = Double.MAX_VALUE;
		while (variation > EPSLON) {
			double newX0 = 0.5 * (x0 + n / x0);
			variation = x0 - newX0;
			x0 = newX0;
		}
		System.out.println("Variation = [" + variation + "]");
		return x0;
	}

	public static double pow2(final double n, final int e) {
		if (e == 0)
			return 1;
		double r = n;
		for (int i = 1; i < e; i++) {
			r *= n;
		}
		return r;
	}

	public static double pow(final double n, int e) {
		int one = 0x01;
		double factor = n;
		double res = 1;
		while (e > 0) {
			if ((e & one) == 1) {
				res = res * factor;
			}
			e = e >> 1;
			factor = factor * factor;
		}
		return res;
	}

	public static double pow(final double n, final double e) {
		double decExp = e - (int) e;
		double partialExp = 0.5;
		double res = 1;
		double factor = sqrt(n);
		while (partialExp > Math.EPSLON) {
			if (decExp >= partialExp) {
				res = res * factor;
				decExp -= partialExp;
			}
			partialExp *= 0.5;
			factor = sqrt(factor);
		}

		return res * pow(n, (int) e);
	}

	public static void primes(final int n) {
		int[] p = new int[n + 1];
		p[2] = 0;
		for (int i = 4; i <= n; i += 2) {
			p[i]++;
		}
		for (int i = 3; i * i <= n; i += 2) {
			if (p[i] == 0) {
				for (int j = i * 2; j <= n; j += i) {
					p[j] = p[j] + 1;
				}
			}
		}
		for (int i = 2; i < p.length; i++) {
			int j = p[i];
			if (j == 0) {
				System.out.printf("%d is prime\n", i);
			}
			// else {
			// System.out.printf("%d tem %d divisores\n", i, j);
			// }
		}
	}

	public static int trailingZeroes(int num) {
		int r = 0;
		if (num < 5)
			return r;
		while (num > 0) {
			r += num / 5;
			num /= 5;
		}
		return r;
	}

	public static int numZeros(int num) {
		int count = 0;
		if (num < 0) {
			System.out.println("Factorial is not defined for < 0");
			return 0;
		}
		for (int i = 5; num / i > 0; i *= 5) {
			count += num / i;
		}
		return count;
	}

	public static void main(final String[] args) {
		// IntegerDivision(200, 7);
		// System.out.println(sqrt(36.0));
		// System.out.println(pow(2, 0.5));
		// System.out.println(pow2(2, 3));
		primes(20000);
		System.out.println(trailingZeroes(200));
		System.out.println(numZeros(200));
	}
}
