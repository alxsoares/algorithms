package alex.algorithms.math.projecteuler.level3;

public class Euler063 {

	public static void main(String[] args) {
		// 10^(n-1) <= x^n < 10^n
		// lower bound x >=10^(n-1/n).
		// upper bound x < 10.
		int sum = 0;
		int lower = 0;
		for (int n = 1; lower < 10; n++) {
			lower = (int) Math.ceil(Math.pow(10, ((double) (n - 1.0)) / n));
			sum += 10 - lower;
		}
		System.out.printf("%d\n", sum);
	}

}
