package alex.algorithms.arrays;

public class MaximumRepeating {

	/**
	 * Given an array of size n, the array contains numbers in range from 0 to
	 * k-1 where k is a positive integer and k <= n. Find the maximum repeating
	 * number in this array. Expected time complexity is O(n) and extra space
	 * allowed is O(1). Modifications to array are allowed.
	 */
	public static int maximumRepetingNumber(final int arr[], final int k) {
		//@formatter:off
		// Iterate though input array arr[], for every element arr[i], increment arr[arr[i]%k] by k
		//@formatter:on
		for (int i = 0; i < arr.length; i++) {
			arr[arr[i] % k] += k;
		}
		int index = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > arr[index]) {
				index = i;
			}
		}
		// reconstructing the array
		for (int i = 0; i < arr.length; i++) {
			arr[i] %= k;
		}
		return index;
	}

	

}
