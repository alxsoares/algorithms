package topcoder.alex.misc;

import java.math.BigInteger;

public class MathAlgs {
	public static int GCD(int x, int y) {
		int a, b;
		if (x > y) {
			a = x;
			b = y;
		} else {
			a = y;
			b = x;
		}
		while (true) {
			a = a % b;
			if (a == 0)
				return b;
			b = b % a;
			if (b == 0)
				return a;
		}
	}

	public static void printPI(int arr[], int n) {
		double counter = 0;
		for (int i = 0; i < n; i++) {
			int a = arr[i];
			for (int j = i + 1; j < n; j++) {
				if (GCD(a, arr[j]) == 1) {
					counter++;
				}
			}
		}
		if (counter > 0) {
			double comb = n * (n - 1) / 2;
			double pi = Math.sqrt(6 * comb / counter);
			System.out.printf("%16f\n", pi);
		} else {
			System.out.printf("No estimate for this data set.");
		}
	}

	static BigInteger Exp(BigInteger x, int n) {
		BigInteger result = new BigInteger("1");
		BigInteger fact = x;
		while (n > 0) {
			if ((n & 1) > 0) {
				result = result.multiply(fact);
			}
			n = n >> 1;
			fact = fact.multiply(fact);
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		int arr[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
//		printPI(arr, 3);
		
		System.out.println(Exp(BigInteger.valueOf(2), 11));
	}

}
