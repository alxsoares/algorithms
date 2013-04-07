package alex.algorithms.dynamicprogramming;

public class Partition {
	public static boolean partition(final int array[]) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		if (sum % 2 == 1) {
			return false;
		}

		int n = array.length;
		// part[i][j] = true if a subset of {arr[0], arr[1], ..arr[j-1]} has sum
		// equal to i, otherwise false
		boolean partition[][] = new boolean[sum / 2 + 1][n + 1];

		for (int i = 0; i <= n; i++) {
			partition[0][i] = true;
		}

		for (int i = 1; i <= sum / 2; i++) {
			partition[i][0] = false;
		}
		for (int i = 1; i <= sum / 2; i++) {

			for (int j = 1; j <= n; j++) {

				partition[i][j] = partition[i][j - 1];
				//@formatter:off
                if (i >= array[j - 1]) {
                    partition[i][j] = partition[i][j] || partition[i - array[j - 1]][j - 1];
                }
              //@formatter:on

			}
		}

		return partition[sum / 2][n];
	}

	public static void main(final String[] args) {
		System.out.printf("%b\n", partition(new int[] { 3, 1, 1, 2, 2, 1 }));
	}

}
