package alex.algorithms.math;

import java.util.Arrays;

public class NextBiggerInteger {

	public static int next(int n) {
		String s = String.valueOf(n);
		for (int i = s.length() - 2; i >= 0; i--) {
			int d1 = s.charAt(i) - '0';
			boolean found = false;
			int min = 10;
			int minIndex = -1;
			for (int j = s.length() - 1; j > i; j--) {
				int d2 = s.charAt(j) - '0';
				if (d1 < d2) {
					found = true;
					if (d2 < min) {
						min = d2;
						minIndex = j;
					}
				}
			}
			if (found) {
				char[] num = s.toCharArray();
				char aux = num[i];
				num[i] = num[minIndex];
				num[minIndex] = aux;
				Arrays.sort(num, i+1, num.length);
				return Integer.valueOf(new String(num));
			}
		}
		// doesn't exist.
		throw new IllegalArgumentException("Number is already the biggest.");
	}

	public static void main(String[] args) {
		System.out.printf("%d \n", next(12345));
		System.out.printf("%d \n", next(next(12345)));
		System.out.printf("%d \n", next(next(next(12345))));
		System.out.printf("%d \n", next(next(next(next(12345)))));
		System.out.printf("%d \n", next(next(next(next(next(12345))))));
	}

}
