package alex.algorithms.math.projecteuler.level1;

import java.util.HashMap;

public class Euler014 {
	/**
	 * 
	 * 
	 The following iterative sequence is defined for the set of positive
	 * integers:
	 * 
	 * n → n/2 (n is even) n → 3n + 1 (n is odd)
	 * 
	 * Using the rule above and starting with 13, we generate the following
	 * sequence: 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
	 * 
	 * It can be seen that this sequence (starting at 13 and finishing at 1)
	 * contains 10 terms. Although it has not been proved yet (Collatz Problem),
	 * it is thought that all starting numbers finish at 1.
	 * 
	 * Which starting number, under one million, produces the longest chain?
	 * 
	 * NOTE: Once the chain starts the terms are allowed to go above one
	 * million.
	 */

	public static void main(String[] args) {
		long number = 999999;
		long highestSequence = 0;
		long numberWithHighestSequence = number;
		long thisSequence;
		long numberToTest;
		HashMap<Long, Long> cache = new HashMap<>();
		while (number > 1) {

			thisSequence = 0;
			numberToTest = number;
			while (numberToTest != 1 || cache.containsKey(numberToTest)) {

				if (numberToTest % 2 == 0) {
					numberToTest = numberToTest / 2;
				} else {
					numberToTest = ((3 * numberToTest) + 1);
				}

				thisSequence++;

			}
			if(cache.containsKey(numberToTest)){
				thisSequence+=cache.get(numberToTest);
			}
			cache.put(number, thisSequence);
			if (thisSequence > highestSequence) {
				highestSequence = thisSequence;
				numberWithHighestSequence = number;
			}

			number--;
		}
		System.out
				.printf("%d %d\n", highestSequence, numberWithHighestSequence);
	}

}
