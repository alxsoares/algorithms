package alex.algorithms.arrays;

import java.util.Arrays;
import java.util.Stack;

public class ArraysProblems {
	/**
	 * 
	 Find subarray with given sum
	 * 
	 * Given an unsorted array of nonnegative integers, find a continous
	 * subarray which adds to a given number.
	 */
	public static void subArraySum(final int[] a, final int sum) {
		int start = 0;
		int currSum = a[0];
		for (int i = 1; i < a.length; i++) {
			while (currSum > sum && start < i - 1) {
				currSum -= a[start++];
			}
			if (currSum == sum) {
				System.out.printf("start = %d end = %d\n", start, i - 1);
				return;
			}
			currSum += a[i];
		}
		System.out.println("Subarray not found\n");
	}

	/**
	 * Given an unsorted array arr[0..n-1] of size n, find the minimum length
	 * subarray arr[s..e] such that sorting this subarray makes the whole array
	 * sorted.
	 * 
	 */
	public static void printMinimumUnsorted(final int[] a) {
		int start = 0;
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				start = i;
				break;
			}
		}
		if (start == a.length - 1) {
			System.out.println("Array is sorted");
			return;
		}
		int end = a.length - 1;
		for (int i = a.length - 1; i > 0; i--) {
			if (a[i] < a[i - 1]) {
				end = i;
				break;
			}
		}
		int max = a[start];
		int min = a[start];
		for (int i = start + 1; i <= end; i++) {
			if (a[i] < min) {
				min = a[i];
			}
			if (a[i] > max) {
				max = a[i];
			}
		}
		for (int i = 0; i < start; i++) {
			if (a[i] > min) {
				start = i;
				break;
			}
		}
		for (int i = a.length - 1; i > end; i--) {
			if (a[i] < max) {
				end = i;
				break;
			}
		}
		System.out.printf("Minimum array to be sorted %d %d\n", start, end);
	}

	/**
	 * Kadane's algorithm
	 */
	public static int maxSubArraySum(final int a[]) {
		int max = a[0];
		int maxEndingHere = a[0];
		int startTemp = 0;
		int start = 0;
		int end = 0;
		for (int i = 1; i < a.length; i++) {

			if (maxEndingHere < 0) {
				maxEndingHere = a[i];
				startTemp = i;
			} else {
				maxEndingHere += a[i];
			}
			if (maxEndingHere > max) {
				start = startTemp;
				end = i;
				max = maxEndingHere;
			}
		}
		System.out.printf("%d %d\n", start, end);
		return max;
	}

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

	public static int secondMax(final int a[]) {
		int first = Integer.MIN_VALUE;
		int second = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > first) {
				second = first;
				first = a[i];

			} else if (a[i] > second) {
				second = a[i];
			}
		}
		// for (int i = 0; i < a.length; i++) {
		// if (a[i] > second && a[i]!= first) {
		// second = a[i];
		// }
		// }
		//
		return second;
	}

	public static void find3Numbers(final int a[], final int sum) {
		Arrays.sort(a);
		for (int i = 0; i < a.length - 2; i++) {
			int l = i + 1;
			int r = a.length - 1;
			while (r > l) {
				int tsum = a[i] + a[l] + a[r];
				if (tsum == sum) {
					System.out.printf("%d %d %d \n", a[i], a[l], a[r]);
					l++;
					r--;
				} else if (tsum > sum) {
					r--;
				} else {
					l++;
				}
			}
		}
	}

	public static void minAbsSumPair(final int a[]) {
		Arrays.sort(a);
		int l = 0;
		int r = a.length - 1;
		int min = Integer.MAX_VALUE;
		int imin = 0;
		int jmin = 0;
		while (l < r) {
			int sum = a[l] + a[r];

			if (Math.abs(sum) < Math.abs(min)) {
				min = sum;
				imin = l;
				jmin = r;
			}
			if (sum < 0) {
				l++;
			} else {
				r--;
			}
		}
		System.out.printf("%d %d %d\n", a[imin], a[jmin], a[imin] + a[jmin]);
	}

	public static void get2NonRepeatingNumbers(final int a[]) {
		int xor = 0;

		for (int i = 0; i < a.length; i++) {
			xor = xor ^ a[i];
		}

		int rightMostBitSetInXor = xor & ~(xor - 1);

		int x = 0;
		int y = 0;

		for (int i = 0; i < a.length; i++) {
			if ((a[i] & rightMostBitSetInXor) != 0) {
				x = x ^ a[i];
			} else {
				y = y ^ a[i];
			}
		}
		System.out.printf("%d %d \n", x, y);
	}

	public static int[] getNextGreaterElementArray(final int arr[]) {
		if (arr == null)
			return null;
		int nge[] = new int[arr.length];
		nge[arr.length - 1] = -1;
		int curr = 1;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		while (curr < arr.length) {
			while (!stack.isEmpty() && arr[stack.peek()] < arr[curr]) {
				nge[stack.pop()] = arr[curr];
			}
			stack.push(curr);
			curr++;
		}
		while (!stack.isEmpty()) {
			nge[stack.pop()] = -1;
		}
		return nge;
	}

	/**
	 * An array contains both positive and negative numbers in random order.
	 * Rearrange the array elements so that positive and negative numbers are
	 * placed alternatively. Number of positive and negative numbers need not be
	 * equal. If there are more positive numbers they appear at the end of the
	 * array. If there are more negative numbers, they too appear in the end of
	 * the array.
	 * 
	 * For example, if the input array is [-1, 2, -3, 4, 5, 6, -7, 8, 9], then
	 * the output should be [9, -7, 8, -3, 5, -1, 2, 4, 6]
	 */
	public static void rearrangePositiveNegative(int arr[]) {
		// for(int j=0 ; j < arr.length;j++){
		// if(arr[j]< 0){
		// i++;
		// int aux
		// }
		// }
		int i = 0;
		int j = arr.length - 1;
		while (i < j) {
			while (arr[i] < 0 && i < j)
				i++;
			while (arr[j] > 0 && i < j)
				j--;
			if (i < j) {
				int aux = arr[i];
				arr[i] = arr[j];
				arr[j] = aux;
				i++;
				j--;
			} else {
				j--;
			}
		}
		int pos = i;
		System.out.println(pos);
		int neg = 0;
		while (pos < arr.length && neg < pos && arr[neg] < 0) {
			int aux = arr[neg];
			arr[neg] = arr[pos];
			arr[pos] = aux;
			pos++;
			neg += 2;
		}
	}

	private static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%d ", arr[i]);
		}
		System.out.println();
	}

	/**
	 * You are given an unsorted array with both positive and negative elements.
	 * You have to find the smallest positive number missing from the array in
	 * O(n) time using constant extra space. You can modify the original array.
	 * 
	 */
	public static void smallestPositiveNumberMissingUnsortedArray(int[] arr) {
		int i = 0;
		int j = arr.length - 1;
		// Move non-negative to left
		while (i < j) {
			while (arr[i] > 0 && i < j)
				i++;
			while (arr[j] <= 0 && i < j)
				j--;
			if (i < j) {
				int aux = arr[i];
				arr[i] = arr[j];
				arr[j] = aux;
				i++;
				j--;
			} else {
				j--;
			}
		}
		printArray(arr);
		int size = i;
		for (i = 0; i < size; i++) {
			int index = Math.abs(arr[i]) - 1;
			if (index < size && index >= 0 && arr[index] > 0) {
				arr[index] *= -1;
			}
		}
		printArray(arr);
		for (i = 0; i < size; i++) {
			if (arr[i] > 0) {
				System.out.printf("First positive missing is %d \n", i + 1);
				return;
			}
		}
		System.out.printf("First positive missing is %d \n", i + 1);
	}

	public static void findTwoRepeatingIntegers(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			arr[(arr[i] - 1) % n] += n;
		}
		for (int i = 0; i < n; i++) {
			if (arr[i] > 2 * n) {
				System.out.printf("%d is repeating\n", i + 1);
			}
		}
	}

	/**
	 * Given an array all of whose elements are positive numbers, find the
	 * maximum sum of a subsequence with the constraint that no 2 numbers in the
	 * sequence should be adjacent in the array. So 3 2 7 10 should return 13
	 * (sum of 3 and 10) or 3 2 5 10 7 should return 15 (sum of 3, 5 and
	 * 7).Answer the question in most efficient way.
	 */
	public static int findMaxSumNoAdjacent(int[] arr) {
		int excl = 0;
		int incl = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int aux = Math.max(incl, excl);
			incl = excl + arr[i];// can not be adjacent
			excl = aux;
		}
		return Math.max(incl, excl);
	}

	public static boolean isSubSet(int[] a, int[] b) {
		Arrays.sort(a);
		Arrays.sort(b);
		int i = 0;
		int j = 0;
		while (b[0] > a[j]) {
			j++;
		}
		while (j < a.length && i < b.length) {
			if (a[j] == b[i]) {
				j++;
				i++;
			} else {
				return false;
			}
		}
		if (i != b.length)
			return false;
		return true;
	}

	public static boolean areConsecutive(int[] array) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
			if (array[i] < min) {
				min = array[i];
			}
		}
		int number = max - min + 1;
		if (number == array.length) {
			// normalising to 0...n-1
			for (int i = 0; i < array.length; i++) {
				array[i] -= min;
			}
			for (int i = 0; i < array.length; i++) {
				array[array[i] % array.length] += array.length;
			}
			for (int i = 0; i < array.length; i++) {
				if (array[i] > 2 * array.length) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static int merge(int[] arr, int[] left, int[] right) {
		int i = 0, j = 0, count = 0;
		while (i < left.length || j < right.length) {
			if (i == left.length) {
				arr[i + j] = right[j];
				j++;
			} else if (j == right.length) {
				arr[i + j] = left[i];
				i++;
			} else if (left[i] <= right[j]) {
				arr[i + j] = left[i];
				i++;
			} else {
				arr[i + j] = right[j];
				// right element is lesser than all the rest of array left
				count += left.length - i;
				j++;
			}
		}
		return count;
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		int[] dupMissing = { 1, 1, 3, 4, 5, 6, 7, 8, 9, 10 };
		findMissingAndDuplicate(dupMissing);
		int arr[] = { -1, 2, -3, 4, 5, 6, -7, 8, 9 };
		rearrangePositiveNegative(arr);
		printArray(arr);
		smallestPositiveNumberMissingUnsortedArray(new int[] { 2, 3, -7, 6, 8,
				1, -10, 15 });
		findTwoRepeatingIntegers(new int[] { 4, 2, 4, 5, 2, 3, 1 });
		System.out.printf("Max difference = %d \n",
				MaximumDifference.maxDiff(new int[] { 1, 2, 6, 80, 100 }));
		System.out.printf("Max Sum = %d \n", findMaxSumNoAdjacent(new int[] {
				5, 5, 10, 100, 10, 5 }));
		System.out.printf(
				"Maximum j-i=%d\n",
				MaximumDifference.maxIndexDiff(new int[] { 9, 2, 3, 4, 5, 6, 7,
						8, 18, 0 }));
		System.out.printf(
				"Maximum j-i=%d\n",
				MaximumDifference.maxIndexDiffRec(new int[] { 9, 2, 3, 4, 5, 6,
						7, 8, 18, 0 }, 0, 9));
		int arr1[] = { 11, 1, 13, 21, 3, 7 };
		int arr2[] = { 11, 3, 7, 1 };
		System.out.println(isSubSet(arr1, arr2));
		System.out.println(areConsecutive(new int[] { -3, -1, -2, 0, 1, 2, 3,
				-3 }));
		int[] inv = { 2, 5, 3, 1, 10 };
		System.out.println("Inversoes=" + CountInversions.countInversions(inv));
	}

}
