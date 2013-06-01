package alex.algorithms.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class ThreeIncreasingElements {
	// FIXME
	public static void printThreeIncreasingElementsWithSorting(
			final Integer arr[]) {
		if (arr == null || arr.length < 3)
			return;
		Integer[] indexes = new Integer[arr.length];
		for (int i = 0; i < indexes.length; i++)
			indexes[i] = i;
		Arrays.sort(indexes, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (arr[o1] == arr[o2]) {
					return o2.compareTo(o1);
				}
				return arr[o1].compareTo(arr[o2]);
			}
		});
		for (int i = 0; i < indexes.length; i++) {
			System.out.printf("%d ", indexes[i]);
		}
		System.out.println();
		for (int i = 0; i < indexes.length - 2; i++) {
			if (indexes[i] < indexes[i + 1] && indexes[i + 1] < indexes[i + 2]) {
				System.out.printf("%d %d %d \n", arr[indexes[i]],
						arr[indexes[i + 1]], arr[indexes[i + 2]]);
			}
		}

	}

	public static void printThreeIncreasingElements(int arr[]) {
		int smallerLeft[] = new int[arr.length];
		smallerLeft[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			smallerLeft[i] = Math.min(smallerLeft[i - 1], arr[i]);
		}
		int greaterRight[] = new int[arr.length];
		greaterRight[arr.length - 1] = arr[arr.length - 1];
		for (int i = arr.length - 2; i >= 0; i--) {
			greaterRight[i] = Math.max(greaterRight[i + 1], arr[i]);
		}
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i] > smallerLeft[i] && arr[i] < greaterRight[i]) {
				System.out.printf("%d %d %d \n", smallerLeft[i], arr[i],
						greaterRight[i]);
			}
		}
	}

	public static void main(String[] args) {
		int arr[] = { 1, 30, 20, 18, 10, 11, 8, 6, 9 };
		printThreeIncreasingElements(arr);
	}

}
