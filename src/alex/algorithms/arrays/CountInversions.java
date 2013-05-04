package alex.algorithms.arrays;

import java.util.Arrays;

public class CountInversions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int countInversions(int[] arr) {
		if (arr.length < 2)
			return 0;
	
		int m = (arr.length + 1) / 2;
		int left[] = Arrays.copyOfRange(arr, 0, m);
		int right[] = Arrays.copyOfRange(arr, m, arr.length);
	
		return countInversions(left) + countInversions(right) + ArraysProblems.merge(arr, left, right);
	}

}
