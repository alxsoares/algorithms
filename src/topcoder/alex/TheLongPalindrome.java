package topcoder.alex;

import java.util.Arrays;

/**
 * 
 * Problem Statement      John and Brus are studying string theory at the
 * university. Their task is to create a list of all the palindromes that
 * contain between 1 and n lowercase letters ('a'-'z'), inclusive. A palindrome
 * is a string that reads the same forward and backward. Additionally, each
 * palindrome in their list must contain no more than k distinct letters. Return
 * the number of palindromes in the list modulo 1234567891. Definition     
 * Class: TheLongPalindrome Method: count Parameters: int, int Returns: int
 * Method signature: int count(int n, int k) (be sure your method is public)
 *     
 * 
 * Constraints - n will be between 1 and 1,000,000,000, inclusive. - k will be
 * between 1 and 26, inclusive. 
 */
public class TheLongPalindrome {
	private static final long P = 1234567891;
	final int maxN = 30;
	long matrix[][] = new long[maxN][maxN];
	long[] f = new long[maxN];
	long[][] c = new long[maxN][maxN];

	public int count(final int n, final int k) {
		long[][] A = new long[maxN][maxN];
		Arrays.fill(f, 0);
		for (int i = 0; i < A.length; i++) {
			Arrays.fill(A[0], 0);
		}
		f[0] = 1;
		for (int i = 1; i < f.length; i++) {
			f[i] = (f[i - 1] * i) % P;

		}
		for (int i = 0; i < c.length; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % P;
			}
		}

		for (int i = 0; i <= k; i++) {
			A[i][i] = i;
			if (i > 0) {
				A[i - 1][i] = 1;
			}
		}
		long[][] B = sum(getGeom(A, ((n + 1) / 2) + 1), getGeom(A, (n / 2) + 1));
		long res = 0;
		for (int i = 1; i <= k; i++) {
			long s = B[0][i];
			long coef = (c[26][i] * f[i]) % P;
			res += (coef * s) % P;
			res = res % P;
		}
		return (int) (res % P);
	}

	private long[][] sum(long[][] A, long[][] B) {
		long[][] res = new long[maxN][maxN];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				res[i][j] = A[i][j] + B[i][j];
				res[i][j] %= P;
			}
		}
		return res;
	}

	private long[][] pow(long[][] A, int n) {
		if (n == 0) {
			long[][] res = new long[maxN][maxN];
			for (int i = 0; i < maxN; i++) {
				for (int j = 0; j < maxN; j++) {
					res[i][j] = (i == j) ? 1 : 0;
				}
			}
			return res;
		}
		long[][] B = pow(A, n / 2);
		B = multiply(B, B);
		if (n % 2 == 0) {
			return B;
		} else {
			return multiply(A, B);
		}

	}

	private long[][] multiply(long[][] A, long[][] B) {
		long[][] res = new long[maxN][maxN];
		for (int i = 0; i < maxN; i++) {
			for (int j = 0; j < maxN; j++) {
				res[i][j] = 0;
				for (int k = 0; k < maxN; k++) {
					res[i][j] += A[i][k] * B[k][j];
					res[i][j] %= P;
				}
			}

		}
		return res;
	}

	private long[][] getGeom(long[][] A, int n) {
		if (n == 1)
			return pow(A, 0);
		long[][] B = getGeom(A, n / 2);
		long[][] multiply = multiply(pow(A, n / 2), B);
		if (n % 2 == 0) {
			return sum(B, multiply);
		} else {
			return sum(sum(B, pow(A, n - 1)), multiply);
		}
	}

	public static void main(String[] args) {
		TheLongPalindrome tp = new TheLongPalindrome();
		System.out.println(tp.count(1, 1));
	}
}
