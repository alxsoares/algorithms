package alex.algorithms.math.projecteuler.level2;

public class Euler038 {

	public static void main(String[] args) {
		long result = 0;
		for (long i = 9387; i >= 9234; i--) {
			result = concat(i, 2 * i);
			if (isPandigital(result)) {
				break;
			}
		}
		System.out.printf("%d\n",result);
	}

	static long concat(long a, long b) {
		long c = b;
		while (c > 0) {
			a *= 10;
			c /= 10;
		}
		return a + b;
	}

	static boolean isPandigital(long n) {
		int digits = 0;
		int count = 0;
		int tmp;

		while (n > 0) {
			tmp = digits;
			digits = digits | 1 << (int) ((n % 10) - 1);//0-base index
			if (tmp == digits) {//verify if the same bit was set
				return false;
			}

			count++;
			n /= 10;
		}

		return digits == (1 << count) - 1;
	}

}
