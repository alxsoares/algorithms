package alex.algorithms.dynamicprogramming;

import java.util.Arrays;

public class Kadane2D {

	public static void maxSubSum(final int[][] M) {
		int finalLeft = 0, finalRight = 0, finalTop = 0, finalBottom = 0;
		int max = Integer.MIN_VALUE;
		int temp[] = new int[M.length];
		for (int left = 0; left < M[0].length; left++) {
			Arrays.fill(temp, 0);
			for (int right = left; right < M[0].length; right++) {
				for (int i = 0; i < M.length; i++) {
					temp[i] += M[i][right];
				}
				int sum = 0;
				int start = 0;
				int maxSum = Integer.MIN_VALUE;
				int finish = 0;
				for (int j = 0; j < temp.length; j++) {
					sum += temp[j];
					if (sum < 0) {
						sum = 0;
						start = j + 1;
					} else if (sum > maxSum) {
						maxSum = sum;
						finish = j;
					}
				}
				if (maxSum > max) {
					max = maxSum;
					finalLeft = left;
					finalRight = right;
					finalTop = start;
					finalBottom = finish;
				}
			}
		}

		System.out.printf("(Top, Left) (%d, %d)\n", finalTop, finalLeft);
		System.out
				.printf("(Bottom, Right) (%d, %d)\n", finalBottom, finalRight);
		System.out.printf("Max sum is: %d\n", max);
	}

	public static void kadane2D(int[][] M) {
		int a[] = new int[M.length];
		int max = Integer.MIN_VALUE;
		int finalLeft = 0, finalRight = 0, finalTop = 0, finalBottom = 0;
		for (int left = 0; left < M[0].length; left++) {
			Arrays.fill(a, 0);
			for (int right = left; right < M[0].length; right++) {
				for (int i = 0; i < M.length; i++) {
					a[i] += M[i][right];
				}
				int maxSum = Integer.MIN_VALUE;
				int currentSum = 0;
				int finish = 0;
				int start = 0;
				for (int i = 0; i < M.length; i++) {
					currentSum += a[i];
					if (currentSum < 0) {
						currentSum = 0;
						start = i+1;
					} else if (currentSum > maxSum) {
						maxSum = currentSum;
						finish = i;
					}
				}
				if (maxSum > max) {
					max = maxSum;
					finalLeft = left;
					finalTop = start;
					finalRight = right;
					finalBottom = finish;
				}

			}
		}
		System.out.printf("Position of max is %d %d %d %d\n", finalLeft, finalTop, finalBottom, finalRight);
		System.out.printf("Max sum is %d \n", max);
	}

	public static void main(String[] args) {
		//@formatter:off
        int M[][] = 
               {
                {1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
               };
        //@formatter:on
		maxSubSum(M);
		int[][] arr = { { 1, -2, -7, 0 }, { -6, 2, 9, 2 }, { -4, -2, -1, 4 },
				{ -1, -8, 0, -4 } };
		maxSubSum(arr);
		kadane2D(M);
		kadane2D(arr);
	}
}
