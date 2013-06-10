package alex.algorithms.arrays;

import java.util.Arrays;

public class FindTripletsWithSumLessThanK {
	// O(n^2)
	public static int countTripletsWithSumLessThan(int a[], int k) {
		if (a == null || a.length < 3)
			return 0;
		int count = 0;
		Arrays.sort(a);
		for (int begin = 0; begin < a.length - 3; begin++) {
			for (int left = begin + 1, right = a.length - 1; left < right; left++) {
				while (a[begin] + a[left] + a[right] > k) {
					right--;
				}
				if (right > left) {
					count += (right - left);
					for (int j = left + 1; j <= right; j++)
						System.out
								.printf("%d %d %d\n", a[begin], a[left], a[j]);
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 7, 8,2 };
		System.out.println(countTripletsWithSumLessThan(a, 11));
	}

}
