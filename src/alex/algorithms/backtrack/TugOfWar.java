package alex.algorithms.backtrack;

public class TugOfWar {
	static int min_diff = Integer.MAX_VALUE;

	private static void tugOfWar(int[] array, int n, boolean[] currentSelected,
			int numberOfSelected, boolean[] solution, int sum, int currentSum,
			int currentPosition) {
		// checks whether the it is going out of bound
		if (currentPosition == n)
			return;

		// checks that the numbers of elements left are not less than the
		// number of elements required to form the solution
		if ((n / 2 - numberOfSelected) > (n - currentPosition))
			return;

		// consider the cases when current element is not included in the
		// solution
		tugOfWar(array, n, currentSelected, numberOfSelected, solution, sum,
				currentSum, currentPosition + 1);

		// add the current element to the solution
		numberOfSelected++;
		currentSum = currentSum + array[currentPosition];
		currentSelected[currentPosition] = true;

		// checks if a solution is formed
		if (numberOfSelected == n / 2) {
			// checks if the solution formed is better than the best solution so
			// far
			if (Math.abs(sum / 2 - currentSum) < min_diff) {
				min_diff = Math.abs(sum / 2 - currentSum);
				for (int i = 0; i < n; i++)
					solution[i] = currentSelected[i];
			}
		} else {
			// consider the cases where current element is included in the
			// solution
			tugOfWar(array, n, currentSelected, numberOfSelected, solution,
					sum, currentSum, currentPosition + 1);
		}

		// removes current element before returning to the caller of this
		// function
		currentSelected[currentPosition] = false;
	}

	public static void tugOfWar(int[] array) {
		int n = array.length;
		boolean[] curr_elements = new boolean[n];

		boolean[] solution = new boolean[n];

		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += array[i];
			curr_elements[i] = solution[i] = false;
		}

		// Find the solution using recursive function TOWUtil()
		tugOfWar(array, n, curr_elements, 0, solution, sum, 0, 0);

		// Print the solution
		System.out.println("The first subset is: ");
		for (int i = 0; i < n; i++) {
			if (solution[i] == true)
				System.out.printf("%d ", array[i]);
		}
		System.out.println("\nThe second subset is: ");
		for (int i = 0; i < n; i++) {
			if (solution[i] == false)
				System.out.printf("%d ", array[i]);
		}
		System.out.println();
	}

	static int dp[][] = new int[20][1000];

	// Not the same as above
	public static void balancedPartition(int array[]) {
		int n = array.length;
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += array[i];

		dp[0][0] = 1;
		for (int i = 1; i < n; i++)
			for (int j = 0; j < sum + 1; j++)
				if (j < array[i])
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j] | dp[i - 1][j - array[i]];

		int firstPartition = 0, secondPartition = 0;
		int mindiff = 1 << 30;
		for (int i = 0; i < sum + 1; i++)
			if (dp[n - 1][i] != 0)
				if (Math.abs(2 * i - sum) < mindiff) {
					mindiff = Math.abs(2 * i - sum);
					firstPartition = i;
				}
		secondPartition = sum - firstPartition;
		System.out.printf("First partition: %d \n", firstPartition);
		System.out.printf("Second partition: %d\n", secondPartition);
	}

	public static void main(String[] args) {
		int arr[] = { 23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4 };
		tugOfWar(arr);
		balancedPartition(arr);
	}

}
