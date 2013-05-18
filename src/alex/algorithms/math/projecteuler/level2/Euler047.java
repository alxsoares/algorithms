package alex.algorithms.math.projecteuler.level2;

import java.util.Set;

import alex.algorithms.math.projecteuler.Eratosthenes;

public class Euler047 {
	public static int numberOfPrimeFactors(int n, Set<Integer> primes) {
		int res = 0;
		for (Integer prime : primes) {
			if (prime * prime > n) {
				return ++res;
			}
			boolean isPrimeFactor = false;
			while (n % prime == 0) {
				isPrimeFactor = true;
				n = n / prime;
			}
			if (isPrimeFactor)
				res++;
			if (n == 1)
				return res;
		}
		return res;
	}

	public static void main(String[] args) {
		Set<Integer> sieve = Eratosthenes.sieve(10000);
		int res = 2 * 3 * 5 * 7;
		int consecutives = 1;
		while (consecutives < 4) {
			res++;
			if (numberOfPrimeFactors(res, sieve) >= 4) {
				consecutives++;
			} else {
				consecutives = 0;
			}
		}
		System.out.printf("%d\n", res-3);
	}

}
