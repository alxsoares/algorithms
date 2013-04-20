package alex.algorithms;

/**
 * Given an array of n distinct integers sorted in ascending order, write a
 * function that returns a Fixed Point in the array, if there is any Fixed Point
 * present in array, else returns -1. Fixed Point in an array is an index i such
 * that arr[i] is equal to i. Note that integers in array can be negative.
 * 
 */
public class FixedPoint {

	public static int fixedPoint(int array[], int start, int end) {
		if (end < start)
			return -1;
		int mid = (start + end) / 2;
		if (mid == array[mid])
			return mid;
		if (array[mid] > mid) {
			return fixedPoint(array, start, mid - 1);
		} else {
			return fixedPoint(array, mid + 1, end);
		}

	}

	public static void main(String[] args) {

		int array[] = {-10, -1, 0, 3, 10, 11, 30, 50, 100};
		System.out.printf("Fixed point is %d\n", fixedPoint(array, 0, array.length-1));
	}

}
