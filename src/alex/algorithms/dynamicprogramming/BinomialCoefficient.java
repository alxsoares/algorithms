package alex.algorithms.dynamicprogramming;

public class BinomialCoefficient {

	public static long binomialCoefficientR(int n, int k) {
		if (n == k || k == 0)
			return 1;
		return binomialCoefficientR(n - 1, k - 1)
				+ binomialCoefficientR(n - 1, k);
	}

	public static long binomialCoefficient(int n, int k) {
		long[][] C = new long[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= k && j<=i; j++) {
				// Base
				if (j == 0 || j == i) {
					C[i][j] = 1;
				} else {
					C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
				}
			}
		}
		return C[n][k];
	}

	public static void main(String[] args) {
		System.out.printf("%d\n", binomialCoefficient(100, 5));
		System.out.printf("%d\n", binomialCoefficientR(100, 5));
	}

}
