package alex.algorithms.math.projecteuler.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class Euler049 {

	public static void main(String[] args) {
		int limit = 10000;
		long result = 0;
		Integer[] primes = sieve(1489, limit);

		for (int i = 0; i < primes.length; i++) {
			for (int j = i + 1; j < primes.length; j++) {
				int k = primes[j] + (primes[j] - primes[i]);
				if (k < limit && Arrays.binarySearch(primes, k) >= 0) {
					if (isPerm(primes[i], primes[j]) && isPerm(primes[i], k)) {
						result = concat(concat(primes[i], primes[j]), k);
						break;
					}
				}
			}
			if (result > 0) {
				break;
			}
		}
		System.out.printf("%d\n",result);
	}

	static long concat(long a, long b) {
		long c = b;
		while (c > 0) {
			a *= 10;
			c /= 10;
		}
		return a + b;
	}

	static boolean isPerm(int m, int n) {
		int[] arr = new int[10];

		int temp = n;
		while (temp > 0) {
			arr[temp % 10]++;
			temp /= 10;
		}

		temp = m;
		while (temp > 0) {
			arr[temp % 10]--;
			temp /= 10;
		}

		for (int i = 0; i < 10; i++) {
			if (arr[i] != 0) {
				return false;
			}
		}
		return true;
	}

	static Integer[] sieve(int lowerLimit, int upperLimit) {

		int sieveBound = (int) (upperLimit - 1) / 2;
		int upperSqrt = ((int) Math.sqrt(upperLimit) - 1) / 2;

		BitSet primes = new BitSet(sieveBound + 1);

		for (int i = 1; i <= upperSqrt; i++) {
			if (!primes.get(i)) {
				for (int j = i * 2 * (i + 1); j <= sieveBound; j += 2 * i + 1) {
					primes.set(j, true);
				}
			}
		}

		List<Integer> numbers = new ArrayList<Integer>(
				(int) (upperLimit / (Math.log(upperLimit) - 1.08366)));

		if (lowerLimit < 3) {
			numbers.add(2);
			lowerLimit = 3;
		}

		for (int i = (lowerLimit - 1) / 2; i <= sieveBound; i++) {
			if (!primes.get(i)) {
				numbers.add((2 * i)+1);
			}
		}

		return numbers.toArray(new Integer[0]);
	}

}
