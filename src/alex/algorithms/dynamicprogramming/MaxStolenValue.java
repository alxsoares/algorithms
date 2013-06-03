package alex.algorithms.dynamicprogramming;

public class MaxStolenValue {
	/**
	 * f(i) = max(v(i) + f(i-2), f(i-1))
	 */
	public static int maxStolenValue(int values[]) {
		if (values == null || values.length == 0)
			return 0;
		int v1 = values[0];
		if (values.length == 1)
			return v1;
		int v2 = Math.max(values[0], values[1]);
		int value = v2;
		for (int i = 2; i < values.length; i++) {
			value = Math.max(v2, v1 + values[i]);
			v1 = v2;
			v2 = value;
		}

		return value;

	}

	public static int maxStolenValueWithArray(int[] values) {
		if (values == null || values.length == 0)
			return 0;
		int v1 = values[0];
		if (values.length == 1)
			return v1;
		int n = values.length;
		int dp[] = new int[n];
		dp[0] = values[0];
		dp[1] = Math.max(values[0], values[1]);
		for (int i = 2; i < n; i++) {
			dp[i] = Math.max(values[i] + dp[i - 2], dp[i - 1]);
		}
		return dp[n - 1];
	}

	public static void main(String[] args) {
		int[] values = { 6, 9, 8, 9, 100, 200 };
		System.out.println(maxStolenValue(values));
		System.out.println(maxStolenValueWithArray(values));
	}

}
