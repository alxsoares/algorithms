package alex.algorithms.arrays;

public class MissingAndDuplicate {

	/**
	 * 1. Given an array of size n, having numbers from 1..n , with one number
	 * missing and one occurring twice. Find the 2 numbers
	 */
	public static void findMissingAndDuplicate(final int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			arr[(arr[i] - 1) % n] += n;
		}
		for (int i = 0; i < n; i++) {
			if (arr[i] <= n) {
				System.out.printf("Missing Number is %d\n", i + 1);
			}
			if (arr[i] > 2 * n) {
				System.out.printf("Duplicated Number is %d\n", i + 1);
			}
	
		}
		// restoring the array
		for (int i = 0; i < n; i++) {
			arr[i] = arr[i] % n;
		}
	}

	

}
