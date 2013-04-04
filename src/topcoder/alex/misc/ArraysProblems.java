package topcoder.alex.misc;

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
	public static void subArraySum(int[] a, int sum) {
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
	public static void printMinimumUnsorted(int[] a) {
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
	public static int maxSubArraySum(int a[]) {
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
	public static int maximumRepetingNumber(int arr[], int k) {
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

	public static int secondMax(int a[]) {
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

	public static void find3Numbers(int a[], int sum) {
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

	public static void minAbsSumPair(int a[]) {
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

	public static void get2NonRepetingNumbers(int a[]) {
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

	public static int getMedianRec(int ar1[], int ar2[], int left, int right,
			int n) {
		if (left > right) {
			return getMedianRec(ar2, ar1, 0, n - 1, n);
		}
		int i = (left + right) / 2;
		int j = n - i - 1;
		if (ar1[i] > ar2[j] && (j == n - 1 || ar2[j + 1] >= ar1[i])) {
			if (ar2[j] > ar1[i - 1] || i == 0) {
				return (ar1[i] + ar2[j]) / 2;
			} else {
				return (ar1[i] + ar1[i - 1]) / 2;
			}
		} else if (ar1[i] > ar2[j] && (j != n - 1 && ar1[i] > ar2[j + 1])) {
			return getMedianRec(ar1, ar2, left, i - 1, n);
		} else {
			return getMedianRec(ar1, ar2, i + 1, right, n);
		}
	}

	public static int[] getNextGreaterElementArray(int arr[]) {
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
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 15, 2, 4, 8, 9, 5, 10, 23 };
		subArraySum(arr, 23);
		int a[] = { 10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60 };
		printMinimumUnsorted(a);
		System.out.printf("Maxsum = %d\n", maxSubArraySum(new int[] { -2, -3,
				4, -1, -2, 1, 5, -3 }));
		int arr2[] = { 2, 3, 3, 5, 3, 4, 1, 7 };
		System.out.printf("Max repeting number = %d\n",
				maximumRepetingNumber(arr2, 8));

		int a2[] = { -1, 12, 9982, 73, 389, 1212, 398294, 1, 2938283,
				834834897, 292392392, 1, 2, 12 };
		// System.out.printf("%d\n", secondMax(a));
		// Arrays.sort(a);
		// System.out.printf("%d\n", a[a.length - 2]);
		find3Numbers(a2, 15);
		minAbsSumPair(a2);
		int b[] = { 14, 10, 2, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 8 };
		int c[] = { 10, 20, 3, 4, 3, 3, 4, 5, 6, 6, 7, 9, 8, 8 };
		// get2NonRepetingNumbers(b);
		Arrays.sort(b);
		Arrays.sort(c);
		System.out
				.printf("%d\n", getMedianRec(b, c, 0, b.length - 1, b.length));
		int[] nge = getNextGreaterElementArray(new int[] { 4, 5, 2, 25 });
		for (int i = 0; i < nge.length; i++) {
			System.out.printf("%d\n", nge[i]);
		}
	}

}
