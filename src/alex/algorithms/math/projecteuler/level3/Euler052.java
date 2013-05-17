package alex.algorithms.math.projecteuler.level3;

public class Euler052 {
	public static boolean sameDigits(final long n, final long m) {
		int digits[] = new int[10];
		long nn = n;
		while (nn > 0) {
			digits[(int) (nn % 10)]++;
			nn = nn / 10;
		}
		nn = m;
		while (nn > 0) {
			digits[(int) (nn % 10)]--;
			nn = nn / 10;
		}
		for (int i = 0; i < digits.length; i++) {
			int j = digits[i];
			if (j != 0)
				return false;
		}
		return true;
	}
	public static void main(final String[] args) {
		for (int i = 10;; i++) {
			long x = i;
			if (sameDigits(x, 2 * x) && sameDigits(x, 3 * x)
					&& sameDigits(x, 4 * x) && sameDigits(x, 5 * x)
					&& sameDigits(x, 6 * x)) {
				System.out.printf("%d", x);
				break;
			}
		}
	}
}
