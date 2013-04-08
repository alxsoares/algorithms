package alex.algorithms.dynamicprogramming;

public class CuttingARod {
	// cutRod(n) = max(price[i] + cutRod(price, n-i-1)) for all i in {0, 1 ..
	// n-1}
	public static int cutRodRecursive(int n, int[] price) {
		if (n <= 0)
			return 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, price[i] + cutRodRecursive(n - i - 1, price));
		}

		return max;
	}

	// Dynamic programming version
	public static int cutRod(int n, int[] price) {
		int[] length = new int[n + 1];
		length[0] = 0;
		for (int i = 1; i <= n; i++) {
			int maxI = Integer.MIN_VALUE;
			for (int j = 0; j < i; j++) {
				maxI = Math.max(maxI, price[j] + length[i - j - 1]);
			}
			length[i] = maxI;
		}
		return length[n];
	}

	public static void main(String[] args) {
		int arr[] = {1, 5, 8, 9, 10, 17, 17, 20};
	    int n = arr.length;
	    System.out.printf("Maximum Profit Value is %d\n", cutRod(n,arr));
	    System.out.printf("Maximum Profit Value is %d\n", cutRodRecursive(n,arr));
	}

}
