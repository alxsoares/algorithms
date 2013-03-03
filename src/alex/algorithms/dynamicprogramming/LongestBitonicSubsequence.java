package alex.algorithms.dynamicprogramming;

public class LongestBitonicSubsequence {
	/**
	 * 
	 * Given an array arr[0 ... n-1] containing n positive integers, a
	 * subsequence of arr[] is called Bitonic if it is first increasing, then
	 * decreasing. Write a function that takes an array as argument and returns
	 * the length of the longest bitonic subsequence. A sequence, sorted in
	 * increasing order is considered Bitonic with the decreasing part as empty.
	 * Similarly, decreasing order sequence is considered Bitonic with the
	 * increasing part as empty.
	 * 
	 */
	public static int lbs(int[] array) {
		// lis
		int n = array.length;
		int[] lis = new int[n];
		lis[0] = 1;
		for (int i = 1; i < n; i++) {
			lis[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
				}
			}
		}
		// lds
		int[] lds = new int[n];
		lds[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			lds[i] = 1;
			for (int j = n - 1; j > i; j--) {
				if (array[j] < array[i] && lds[i] < lds[j] + 1) {
					lds[i] = lds[j] + 1;
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int bitonic = lds[i] + lis[i] - 1;
			if (bitonic > max) {
				max = bitonic;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.printf("%d \n", lbs(new int[] { 1, 11, 2, 10, 4, 5, 2, 1 }));
		System.out.printf("%d \n", lbs(new int[] { 12, 11, 40, 5, 3, 1 }));
		System.out.printf("%d \n", lbs(new int[] { 80, 60, 30, 40, 20, 10 }));
		System.out.printf("%d \n", lbs(new int[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 }));
	}

}
