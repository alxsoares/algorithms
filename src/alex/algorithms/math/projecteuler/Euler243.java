package alex.algorithms.math.projecteuler;

public class Euler243 {
	
	public static void main(String[] args) {
		Integer[] primes = Eratosthenes.sieve(2, 1000);
		long numerator = 15499;
		long denominator = 94744;
		long val = 1;
		long totient = 1;
		for(int i=0;i<primes.length;i++){
			int p = primes[i];
			long nextVal = val * p;
			long nextTotient = totient * (p - 1);
			if (numerator * (nextVal - 1) > nextTotient * denominator) {
				long d = numerator * val - denominator * totient;
				long n = (numerator + d - 1) / d;
				System.out.println(n * val);
				return;
			}
			val = nextVal;
			totient = nextTotient;
		}
	}

}
