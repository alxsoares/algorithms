package alex.algorithms.math.projecteuler.level2;

import java.util.BitSet;
import java.util.Set;
import java.util.TreeSet;

public class Euler037 {

	public static boolean isPrimeReverse(int right, Set<Integer> primes) {
		if(right < 0) throw new IllegalArgumentException();
		int left = 0;
		int multiplier = 1;
		boolean bothPrimes = true;
		
		while (right > 0 && bothPrimes) {
			left += multiplier * (right % 10);
			bothPrimes = primes.contains(left) && primes.contains(right);
			right /= 10;
			multiplier *= 10;
		}
		return bothPrimes;
	}

	public static Set<Integer> sieve(final int n) {
		Set<Integer> primes = new TreeSet<>();
		BitSet bprimes = new BitSet(n + 1);
		int sqrt = (int) Math.sqrt(n);
		for (int i = 2; i <= sqrt; i++) {
			if (!bprimes.get(i)) {
				for (int j = 2 * i; j <= n; j += i) {
					bprimes.set(j);
				}
			}

		}
		for (int i = 2; i <= n; i++) {
			if (!bprimes.get(i)) {
				primes.add(i);
			}
		}
		return primes;
	}

	public static void main(String[] args) {
		Set<Integer> primes = sieve(999999);
		int count = 0;
		long sum = 0;
		for (Integer prime : primes) {
			if(prime >=2 && prime <=7) continue;
			if (isPrimeReverse(prime, primes)) {
				count++;
				sum += prime;
			}
			if (count == 11)
				break;
		}
		System.out.printf("%d %d\n", count, sum);
		System.out.println(isPrimeReverse(3797, primes));
	}

}
