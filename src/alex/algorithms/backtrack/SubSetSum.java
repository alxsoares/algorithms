package alex.algorithms.backtrack;

import java.util.Arrays;

public class SubSetSum {
	public static int totatlNodes = 0;

	public static void subSetSum(int set[], int subSet[], int subSetSize,
			int sum, int index, int targetSum) {
		totatlNodes++;

		if (targetSum == sum) {
			printSubset(subSet, subSetSize);

			if (index + 1 < set.length
					&& sum - set[index] + set[index + 1] <= targetSum) {
				subSetSum(set, subSet, subSetSize - 1, sum - set[index],
						index + 1, targetSum);
			}
			return;
		} else {
			if (index < set.length && sum + set[index] <= targetSum) {
				for (int i = index; i < set.length; i++) {
					subSet[subSetSize] = set[i];

					if (sum + set[i] <= targetSum) {
						subSetSum(set, subSet, subSetSize + 1, sum + set[i],
								i + 1, targetSum);
					}
				}
			}
		}
	}

	static private void printSubset(int[] A, int size) {
		for (int i = 0; i < size; i++) {
			System.out.printf("%d ", A[i]);
		}

		System.out.printf("\n");

	}

	public static void generateSubsets(int s[], int targetSum) {
		int[] subSet = new int[s.length];

		int total = 0;
		Arrays.sort(s);
		for (int i = 0; i < s.length; i++) {
			total += s[i];
		}

		if (s[0] <= targetSum && total >= targetSum) {

			subSetSum(s, subSet, 0, 0, 0, targetSum);

		}

	}

	public static void main(String[] args) {
		totatlNodes = 0;
		int weights[] = { 15, 22, 14, 26, 32, 9, 16, 8 };
		int target = 53;

		generateSubsets(weights, target);

		System.out.printf("Nodes generated %d\n", totatlNodes);

	}

}
