package alex.algorithms.math.projecteuler;

import java.util.BitSet;
import java.util.Set;
import java.util.TreeSet;

public class ErastotenesSieve {

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

}
