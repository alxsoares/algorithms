package alex.algorithms.dynamicprogramming;

public class EggDropping {

	public static int eggDrop(int n, int k) {

		int[][] eggFloor = new int[n + 1][k + 1];

		for (int i = 1; i <= n; i++) {
			eggFloor[i][1] = 1;
			eggFloor[i][0] = 0;
		}
		// We always need j trials for one egg and j floors.
		for (int j = 1; j <= k; j++) {
			eggFloor[1][j] = j;
		}
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= k; j++) {
				eggFloor[i][j] = Integer.MAX_VALUE;
				for (int x = 1; x <= j; x++) {
					int max = 1 + Math.max(eggFloor[i - 1][x - 1],
							eggFloor[i][j - x]);
					if (max < eggFloor[i][j]) {
						eggFloor[i][j] = max;
					}
				}
			}
		}

		return eggFloor[n][k];
	}

	public static void main(String[] args) {
		int n = 2, k = 36;
		System.out
				.printf("\nMinimum number of trials in worst case with %d eggs and %d floors is %d \n",
						n, k, eggDrop(n, k));
	}

}
