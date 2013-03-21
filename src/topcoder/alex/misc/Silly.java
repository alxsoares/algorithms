package topcoder.alex.misc;

public class Silly {

	public static int[] sumOthers(int[] array) {
		int memo[] = new int[array.length];
		int sum = 0;
		for (int i = array.length - 1; i >= 0; i--) {
			memo[i] = sum + array[i];
			sum += array[i];
		}
		sum = 0;
		for (int i = 0; i < array.length - 1; i++) {
			memo[i] = sum + memo[i + 1];
			sum += array[i];
		}
		memo[array.length - 1] = sum;
		return memo;
	}

	// B[i] = min{A[i], A[i+1], A[i+2], A[i+3], ……., A[i+k]}
	public static int[] mink(int[] a, int k) {
		int r[] = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = i; j < i + k && j < a.length; j++) {
				if (a[i] < min) {
					min = a[i];
				}
			}
			r[i] = min;
		}
		return r;
	}

	void process2(int M[][], int A[], int N) {
		int i, j;

		// initialize M for the intervals with length 1
		for (i = 0; i < N; i++)
			M[i][0] = i;
		// compute values from smaller to bigger intervals
		for (j = 1; (1 << j) <= N; j++)
			for (i = 0; i + (1 << j) - 1 < N; i++)
				if (A[M[i][j - 1]] < A[M[i + (1 << (j - 1))][j - 1]])
					M[i][j] = M[i][j - 1];
				else
					M[i][j] = M[i + (1 << (j - 1))][j - 1];
	}

	public static void main(String[] args) {
		int[] others = { 1, 2, 3, 4 };
		int[] array = sumOthers(others);
		for (int i = 0; i < array.length; i++) {
			System.out.printf("%d ", array[i]);
		}
		System.out.println();
	}

}
