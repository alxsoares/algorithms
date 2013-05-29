package alex.algorithms.math.projecteuler;

public class Euler381 {

	/**
	 * Wilson's Theorem: http://mathworld.wolfram.com/WilsonsTheorem.html
	 */
	public static void main(String[] args) {
		int N = 100000000;
		Integer[] primes = Eratosthenes.sieve(2, N);
		long sum = 0;
		for (int i = 2; i < primes.length; i++) {
			int p = primes[i];
			int count = (3 * p - 1) / 2;
			int y = 0;
			for (int k = 1;; k++) {
				if ((k * primes[i] + 1) % 8 == 0) {
					y = (k * primes[i] + 1) / 8;
					break;
				}
			}
			count = (count + y) % p;
			sum = sum + count;
		}
		System.out.println(sum);

	}

}
