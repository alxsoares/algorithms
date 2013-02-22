package alex.algorithms.math;

public class FastFibonacci {
	public static int[][] multiply(int A[][], int B[][], int n, int m) {
		int res[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int sum = 0;
				for (int k = 0; k < B.length; k++) {
					sum += A[i][k] * B[k][j];
				}
				res[i][j] = sum;
			}
		}
		return res;
	}

	public static int[][] pow(int a[][], int n) {
		if (n == 1)
			return a;
		int B[][] = pow(a, n / 2);// if n is odd
		if (n % 2 == 0) {
			return multiply(B, B, a.length, a.length);
		} else {
			return multiply(a, multiply(B, B, a.length, a.length), a.length,
					a.length);
		}
	}

	public static long fastFib(int nfib) {
		int[][] base = { { 1, 1 }, { 1, 0 } };
		int f[][] = pow(base, nfib);
		return f[0][1];
	}

	public static long fastFibIterative(int n) {
		long i = 1;
		long h = 1;
		long j = 0;
		long k = 0;
		long t;
		while (n > 0) {
			if (n % 2 == 1) { // if n is odd
				t = j * h;
				j = i * h + j * k + t;
				i = i * k + t;
			}
			t = h * h;
			h = 2 * k * h + t;
			k = k * k + t;
			n = (int) n / 2;
		}
		return j;
	}

	public static void main(String[] args) {
		System.out.println(fastFib(1));
		System.out.println(fastFib(2));
		System.out.println(fastFib(3));
		System.out.println(fastFib(4));
		System.out.println(fastFib(5));
		System.out.println(fastFib(6));
		System.out.println(fastFib(7));
		System.out.println(fastFib(8));
		System.out.println(fastFib(9));
		System.out.println(fastFib(10));
		
		System.out.println(fastFibIterative(1));
		System.out.println(fastFibIterative(2));
		System.out.println(fastFibIterative(3));
		System.out.println(fastFibIterative(4));
		System.out.println(fastFibIterative(5));
		System.out.println(fastFibIterative(6));
		System.out.println(fastFibIterative(7));
		System.out.println(fastFibIterative(8));
		System.out.println(fastFibIterative(9));
		System.out.println(fastFibIterative(100));
	}

}
