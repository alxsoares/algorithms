package alex.algorithms.math.projecteuler;

public class Euler137 {

	public static void main(String[] args) {
		long result = 0;

		for (int k = 1; k < 16; k++) {
			result = fibonacci(2 * k) * fibonacci(2 * k + 1);
			System.out.println(result);
		}

		System.out.printf("The 15th nugget: %d", result);
	}


	public static long fibonacci(long k) {
		double sqrt5 = Math.sqrt(5);
		return (long) ((Math.pow((1 + sqrt5) / 2, k) - Math.pow(
				(1 - sqrt5) / 2, k)) / sqrt5);
	}

}
