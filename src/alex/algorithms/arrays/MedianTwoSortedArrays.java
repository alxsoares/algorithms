package alex.algorithms.arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Arrays;

public class MedianTwoSortedArrays {

	static float median(int a, int b) {
		return (float) ((a + b) / 2);
	}

	static float median(int a, float b) {

		return (a + b) / 2;
	}

	static float median(int a, int b, int c) {
		return a + b + c - max(a, max(b, c)) - min(a, min(b, c));
	}

	static float median(int a, int b, int c, int d) {
		int Max = max(a, max(b, max(c, d)));
		int Min = min(a, min(b, min(c, d)));
		return (float) ((a + b + c + d - Max - Min) / 2);
	}

	static float median(int A[], int N, int B[], int M) {
		if (N == 1) {
			if (M == 1)
				return median(A[0], B[0]);
			if ((M & 1) > 0)
				return median(B[M / 2],
						median(A[0], B[M / 2 - 1], B[M / 2 + 1]));
			return median(B[M / 2], B[M / 2 - 1], A[0]);
		}

		else if (N == 2) {
			if (M == 2)
				return median(A[0], A[1], B[0], B[1]);
			if ((M & 1) > 0)
				return median(B[M / 2], max(A[0], B[M / 2 - 1]),
						min(A[1], B[M / 2 + 1]));
			return median(B[M / 2], B[M / 2 - 1], max(A[0], B[M / 2 - 2]),
					min(A[1], B[M / 2 + 1]));
		}

		int idxA = (N - 1) / 2;
		int idxB = (M - 1) / 2;

		if (A[idxA] <= B[idxB])
			return median(Arrays.copyOfRange(A, idxA, A.length),
					N / 2 + 1, B, M - idxA);

		return median(A, N / 2 + 1,
				Arrays.copyOfRange(B, idxA, B.length), M - idxA);
	}
	static float findMedian(int A[],  int B[]) {
		int N = A.length;
		int M = B.length;
		if (N > M)
			return median(B, M, A, N);

		return median(A, N, B, M);
	}

	public static void main(String[] args) {
	    int A[] = {1,900};
	    int B[] = {5, 8, 10, 20};
	 
	 
	    System.out.printf( "%f", findMedian( A,  B ) );
	}

}
