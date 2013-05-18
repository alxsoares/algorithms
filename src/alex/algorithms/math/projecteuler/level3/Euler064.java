package alex.algorithms.math.projecteuler.level3;

public class Euler064 {

	public static void main(String[] args) {

		int result = 0;

		for (int n = 2; n <= 10000; n++) {
			int limit = (int) Math.sqrt(n);
			if (limit * limit == n)
				continue;

			int period = 0;
			int d = 1;
			int m = 0;
			int a = limit;

			do {
				m = d * a - m;
				d = (n - m * m) / d;
				a = (limit + m) / d;
				period++;
			} while (a != 2 * limit);

			if (period % 2 == 1)
				result++;
		}
		System.out.printf("%d\n", result);
	}

}
