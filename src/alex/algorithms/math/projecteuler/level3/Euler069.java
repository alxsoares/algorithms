package alex.algorithms.math.projecteuler.level3;

import alex.algorithms.math.projecteuler.Eratosthenes;

public class Euler069 {

	public static void main(String[] args) {
		int result = 1;
		Integer[] primes = Eratosthenes.sieve(2, 200);
		int i = 0;
		while(result* primes[i] < 1000000){
		    result *= primes[i];
		    i++;
		}
		System.out.printf("%d\n", result);
	}

}
