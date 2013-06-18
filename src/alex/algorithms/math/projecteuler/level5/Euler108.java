package alex.algorithms.math.projecteuler.level5;

import alex.algorithms.math.projecteuler.Eratosthenes;

public class Euler108 {

	private static int numberOfDoubleSquares(long number, Integer[] primes) {
		int numberOfDivisors = 1;
		long exponent;
		long remain = number;
		for (int i = 0; i < primes.length; i++) {
			if (primes[i] * primes[i] > number) {
				return numberOfDivisors * 2;
			}
			exponent =1;
			while(remain%primes[i]==0){
				exponent+=2;
				remain=remain/primes[i];
			}
			numberOfDivisors*=exponent;
			if(remain==1){
				return numberOfDivisors;
			}
		}
		return numberOfDivisors;
	}

	public static void main(String[] args) {
		Integer[] primes = Eratosthenes.sieve(2, 17);
		long result=0;
		long limit =1000;
		long n=1;
		while(true){
			if((numberOfDoubleSquares(n, primes)+1)/2 > limit){
				result=n;
				break;
			}
			n++;
		}
		System.out.printf("%d\n", result);
	}

}
