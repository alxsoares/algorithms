package alex.algorithms.math.projecteuler.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class Euler050 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int limit = 1000000;
		long result = 0;
		int numberOfPrimes = 0;
		Long[] primes = sieve(1,limit);
		long[] primeSum = new long[primes.length+1];
		 
		primeSum[0] = 0;
		for (int i = 0; i < primes.length; i++) {
		    primeSum[i+1] = primeSum[i] + primes[i];
		}
		 
		for (int i = numberOfPrimes; i < primeSum.length; i++) {
		    for (int j = i-(numberOfPrimes+1); j >= 0; j--) {
		        if (primeSum[i] - primeSum[j] > limit) break;
		 
		        if (Arrays.binarySearch(primes, (primeSum[i] - primeSum[j])) >= 0) {
		            numberOfPrimes = i - j;
		            result = primeSum[i] - primeSum[j];
		        }
		    }
		}
		System.out.printf("%d\n",result);

	}
	static Long[] sieve(int lowerLimit, int upperLimit) {

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

		List<Long> numbers = new ArrayList<Long>(
				(int) (upperLimit / (Math.log(upperLimit) - 1.08366)));

		if (lowerLimit < 3) {
			numbers.add(2L);
			lowerLimit = 3;
		}

		for (int i = (lowerLimit - 1) / 2; i <= sieveBound; i++) {
			if (!primes.get(i)) {
				numbers.add((long) ((2 * i)+1));
			}
		}

		return numbers.toArray(new Long[0]);
	}

}
