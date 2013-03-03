package alex.algorithms.dynamicprogramming;

public class Partition {
	public static boolean partition(int array[]) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		if (sum % 2 == 1) {
			return false;
		}

		int n = array.length;
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
			
				if (i >= array[j - 1]) {
					partition[i][j] = partition[i][j]
							|| partition[i - array[j - 1]][j - 1];
				}
				
			}
		}

		return partition[sum/2][n];
	}

	public static void main(String[] args) {
		System.out.printf("%b\n", partition(new int[]{3, 1, 1, 2, 2, 1}));
	}

}
