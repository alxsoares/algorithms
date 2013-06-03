package alex.algorithms;

import java.util.Arrays;

public class BinarySearchInShiftedArray {

	public static int findPivot(int arr[], int start, int end) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;
		if (mid == 0 || mid == end)
			return mid;
		if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
			return mid;
		}
		if (arr[mid] < arr[start]) {
			return findPivot(arr, start, mid - 1);
		}
		if (arr[mid] >= arr[end]) {
			return findPivot(arr, mid + 1, end);
		}
		return -1;
	}

	public static int binarySearchShifted(int[] arr, int k) {
		if (arr == null || arr.length == 0)
			return -1;
		int index = findPivot(arr, 0, arr.length - 1);
		if (arr[0] <= k && k <= arr[index]) {
			return Arrays.binarySearch(arr, 0, index + 1, k);
		} else {
			return Arrays.binarySearch(arr, index + 1, arr.length, k);
		}
	}

	public static int binarySearchShifted(int a[], int key, int start, int end) {
		if (start > end)
			return -1;
		int mid = (start + end) >>> 1;
		if (a[mid] == key)
			return mid;

		if (a[start] <= a[mid]) {
			if (a[start] <= key && key < a[mid]) {
				return binarySearchShifted(a, key, start, mid - 1);
			}
			return binarySearchShifted(a, key, mid + 1, end);
		} else if (a[mid] <= a[end]) {
			if (a[mid] < key && key <= a[end]) {
				return binarySearchShifted(a, key, mid + 1, end);
			}
			return binarySearchShifted(a, key, start, mid - 1);
		}
		assert (false);
		return -1;
	}

	public static int binarySearchShiftedIterative(int a[], int key, int start,
			int end) {
		while (start <= end) {
			int mid = (start + end) >>> 1;
			if (a[mid] == key)
				return mid;
			if (a[start] <= a[mid]) {
				if (a[start] <= key && key < a[mid]) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			} else if (a[mid] <= a[end]) {
				if (a[mid] < key && key <= a[end]) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int arr[] = { 2, 2, 2, 2, 2, 3, 10, 1, 2 };
		System.out.println(arr[binarySearchShifted(arr, 3)]);
		System.out
				.println(arr[binarySearchShifted(arr, 10, 0, arr.length - 1)]);
		System.out.println(arr[binarySearchShiftedIterative(arr, 10, 0,
				arr.length - 1)]);
		System.out.println(arr[binarySearchShiftedIterative(arr, 3, 0,
				arr.length - 1)]);
		System.out.println(arr[binarySearchShiftedIterative(arr, 2, 0,
				arr.length - 1)]);
	}

}
