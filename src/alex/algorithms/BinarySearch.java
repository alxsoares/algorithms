package alex.algorithms;

public class BinarySearch {

	/**
	 * Given an array of integers which is initially increasing and then
	 * decreasing, find the maximum value in the array.
	 */
	public static int findMax(int[] array, int start, int end) {
		if (start == end) {
			return array[start];
		}
		if (end == start + 1) {
			if (array[start] > array[end]) {
				return array[start];
			}
			if (array[start] < array[end]) {
				return array[end];
			}
		}
		int m = (start + end) / 2;

		if (array[m] > array[m + 1] && array[m] > array[m - 1])
			return array[m];

		if (array[m] > array[m + 1] && array[m] < array[m - 1]) {
			return findMax(array, start, m - 1);
		} else {
			return findMax(array, m + 1, end);
		}
	}

	public static void main(String[] args) {
		int array[] = {1, 3, 50, 51, 9, 7, 6};
		System.out.printf("Maximum is %d\n", findMax(array, 0, array.length-1));
	}

}
