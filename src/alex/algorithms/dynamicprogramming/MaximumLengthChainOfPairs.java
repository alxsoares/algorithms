package alex.algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * You are given n pairs of numbers. In every pair, the first number is always
 * smaller than the second number. A pair (c, d) can follow another pair (a, b)
 * if b < c. Chain of pairs can be formed in this fashion. Find the longest
 * chain which can be formed from a given set of pairs.
 * 
 * TODO: solve in O(nlogn)
 */
public class MaximumLengthChainOfPairs {
	public static int maxLengthChain(Pair[] array) {
		int[] length = new int[array.length];
		Arrays.sort(array);
		length[0] = 1;
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < array.length; i++) {
			length[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[i].a > array[j].b && length[i] < length[j] + 1) {
					length[i] = length[j] + 1;
					if (max < length[i]) {
						max = length[i];
					}
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Pair array[] = { new Pair(5, 24), new Pair(15, 25),
				new Pair(27, 40), new Pair(50, 60) };
		System.out.println(maxLengthChain(array));
	}

}

class Pair implements Comparable<Pair> {
	int a, b;

	Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Pair o) {
		return (this.a - o.a);
	}
}
