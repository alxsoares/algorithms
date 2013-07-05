package alex.algorithms.math.projecteuler;

/**
 * 
 * The hole has size c^2 - 2ab = (a-b)^2, so it will tile the big square iff
 * (a-b) divides c, equivalently (a-b)^2 divides c^2 or (a-b)^2 divides 2ab,
 * that is the legs of the corresponding primitive differ by 1. Assuming
 * gcd(a,b) = 1 and a odd, we find (a = r^2-s^2, b = 2rs, c = r^2+s^2) (r-s)^2 -
 * 2s^2 = (+/-)1, i.e. r/s is a convergent of 1 + sqrt(2). Then for each such
 * primitive count how many multiples of (a+b+c) = 2r(r+s) are less than 10^8
 * and sum up.
 * 
 */
public class Euler139 {
	public static void main(String[] args) {
		new Euler139().pell();
	}

	public void pell() {

		long result = 0;
		long limit = 100000000;

		long x = 1;
		long y = 1;

		while (x + y < limit) {

			long xnew = 3 * x + 4 * y;
			long ynew = 2 * x + 3 * y;

			x = xnew;
			y = ynew;

			result += limit / (x + y);

		}

		System.out.printf("Number of squares less than 100000000 : %d", result);
	}

	public void BruteForce() {

		long limit = 100000000;

		long result = 0;
		long mlimit = (long) Math.sqrt(limit / 2);

		for (long m = 2; m < mlimit; m++) {
			for (long n = 1; n < m; n++) {
				if (((n + m) % 2) == 1 && gcd(n, m) == 1) {
					long a = 2 * m * n;
					long b = m * m - n * n;
					long c = m * m + n * n;
					long p = a + b + c;
					if (c % (b - a) == 0)
						result += limit / p;
				}
			}
		}

		System.out.printf("Number of squares less than 100000000 : %d", result);
	}

	private long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
