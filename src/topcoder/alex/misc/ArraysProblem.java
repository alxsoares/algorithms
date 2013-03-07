package topcoder.alex.misc;

import java.util.Arrays;

public class ArraysProblem {
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
		System.out.printf("%d %d \n", x,y);
	}

	public static void main(String[] args) {
		int a[] = { -1, 12, 9982, 73, 389, 1212, 398294, 1, 2938283, 834834897,
				292392392, 1, 2, 12 };
		// System.out.printf("%d\n", secondMax(a));
		// Arrays.sort(a);
		// System.out.printf("%d\n", a[a.length - 2]);
		find3Numbers(a, 15);
		minAbsSumPair(a);
		int b[] = {1,1,2,2,3,3,4,5,6,6,7,7,8,8};
		get2NonRepetingNumbers(b);
	}

}
