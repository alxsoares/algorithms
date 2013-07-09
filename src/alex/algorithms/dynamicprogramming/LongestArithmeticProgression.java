package alex.algorithms.dynamicprogramming;

public class LongestArithmeticProgression {
	// Returns length of the longest AP subset in a given set
	static int lenghtOfLongestAP(int set[]) {
		if (set == null)
			return 0;
		int n = set.length;

		if (n <= 2)
			return n;

		int L[][] = new int[n][n];
		int llap = 2;

		for (int i = 0; i < n; i++)
			L[i][n - 1] = 2;

		for (int j = n - 2; j >= 1; j--) {
			int i = j - 1, k = j + 1;
			while (i >= 0 && k <= n - 1) {
				if (set[i] + set[k] < 2 * set[j])
					k++;
				else if (set[i] + set[k] > 2 * set[j]) {
					L[i][j] = 2;
					i--;
				}

				else {
					L[i][j] = L[j][k] + 1;

					llap = Math.max(llap, L[i][j]);
					i--;
					k++;
				}
			}
			while (i >= 0) {
				L[i][j] = 2;
				i--;
			}
		}
		return llap;
	}

	public static void main(String[] args) {
		int set1[] = { 1, 7, 10, 13, 14, 19 };
		System.out.println(lenghtOfLongestAP(set1));

		int set2[] = { 1, 7, 10, 15, 27, 29 };
		System.out.println(lenghtOfLongestAP(set2));

		int set3[] = { 2, 4, 6, 8, 10 };
		System.out.println(lenghtOfLongestAP(set3));
	}
}
