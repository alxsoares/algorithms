package alex.algorithms.math.projecteuler.level3;

public class Euler071 {

	public static void main(String[] args) {
		long m, n;
		long numerator = 0, denominator = 1;
		for (n = 1; n <= 1000000; n++) {
			m = (n * 3 - 1) / 7;
			if (m * denominator > n * numerator) {
				numerator = m;
				denominator = n;
			}
		}
		System.out.printf("%d %d\n", numerator, denominator);
	}
}
