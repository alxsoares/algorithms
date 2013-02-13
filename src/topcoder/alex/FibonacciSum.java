package topcoder.alex;

import java.util.Arrays;

/**
 * 
 * Problem Statement Depicted below is the Fibonacci sequence:
 * 
 * 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
 * 
 * As you can see, each value from 2 on is the sum of the previous two values.
 * Any positive integer can be written as a sum of values taken from the
 * Fibonacci sequence. These values need not be distinct. Return the smallest
 * number of such values that sum to n.
 * 
 * Definition
 * 
 * Class: FibonacciSum Method: howMany Parameters: int Returns: int Method
 * signature: int howMany(int n) (be sure your method is public)
 * 
 * 
 * Constraints - n will be between 1 and 1000000 inclusive.
 * 
 */
public class FibonacciSum {
	int fib[] = new int[101];
	int dp[] = new int[1000001];
	int count;

	private int func(int n) {
		if (n == 0)
			return 0;

		if (n < 0)
			return Integer.MAX_VALUE;

		if (dp[n] != -1)
			return dp[n];

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < count && (n - fib[i]) >= 0; i++) {
			int r = func(n - fib[i]) + 1;
			if (r < min) {
				min = r;
			}
		}
		dp[n] = min;
		return dp[n];
	}

	public int howMany(int n) {
		fib[0] = 1;
		fib[1] = 1;
		count = 2;
		for (int i = 2; fib[i - 1] + fib[i - 2] < 1000000; i++, count++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		Arrays.fill(dp, -1);
		return func(n);
	}

	public static void main(String[] args) {
		FibonacciSum f = new FibonacciSum();
		System.out.printf("%d\n", f.howMany(1));
		System.out.printf("%d\n", f.howMany(7));
		System.out.printf("%d\n", f.howMany(70));
		System.out.printf("%d\n", f.howMany(107));
		System.out.printf("%d\n", f.howMany(1000));
	}

}
