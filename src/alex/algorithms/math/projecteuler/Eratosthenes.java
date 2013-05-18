package alex.algorithms.math.projecteuler;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Eratosthenes {

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

	public static Integer[] sieve(int lowerLimit, int upperLimit) {
		List<Integer> numbers = new ArrayList<Integer>(
				(int) (upperLimit / (Math.log(upperLimit) - 1.08366)));

		BitSet bprimes = new BitSet(upperLimit + 1);
		int sqrt = (int) Math.sqrt(upperLimit);
		for (int i = 2; i <= sqrt; i++) {
			if (!bprimes.get(i)) {
				for (int j = 2 * i; j <= upperLimit; j += i) {
					bprimes.set(j);
				}
			}

		}
		for (int i = lowerLimit; i <= upperLimit; i++) {
			if (!bprimes.get(i)) {
				numbers.add(i);
			}
		}
		return numbers.toArray(new Integer[0]);
	}
}
