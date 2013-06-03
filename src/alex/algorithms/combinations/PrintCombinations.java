package alex.algorithms.combinations;

import java.util.Arrays;

/**
 * http://en.wikipedia.org/wiki/Pascal%27s_rule
 * 
 */
public class PrintCombinations {

	public static void printCombinations(int array[], int r) {
		int combs[] = new int[r];
		Arrays.sort(array);
		printCombinations(array, r, 0, combs, 0);
	}

	private static void printCombinations(int[] array, int r, int index,
			int[] combs, int i) {
		if (index == r) {
			for (int k = 0; k < r; k++) {
				System.out.printf("%d ", combs[k]);
			}
			System.out.println();
			return;
		}
		if (i >= array.length)
			return;
		combs[index] = array[i];
		// i is included on the combination
		printCombinations(array, r, index + 1, combs, i + 1);
		// removing duplicates.
		while (i < array.length - 1 && array[i] == array[i + 1])
			i++;
		// i is exclude from the combinations
		printCombinations(array, r, index, combs, i + 1);
	}

	public static void main(String[] args) {
		int array[] = { 1, 2, 3, 4, 5, 2, 2,2,3,4,5,6,7,9 };
		printCombinations(array, 5);
	}

}
