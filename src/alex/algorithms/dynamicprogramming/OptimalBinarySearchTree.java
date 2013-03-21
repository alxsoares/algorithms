package alex.algorithms.dynamicprogramming;

public class OptimalBinarySearchTree {

	int optCost(int freq[], int i, int j) {
		// Base cases
		if (j < i) // If there are no elements in this subarray
			return 0;
		if (j == i) // If there is one element in this subarray
			return freq[i];

		// Get sum of freq[i], freq[i+1], ... freq[j]
		int fsum = sum(freq, i, j);

		// Initialize minimum value
		int min = Integer.MAX_VALUE;

		// One by one consider all elements as root and recursively find cost
		// of the BST, compare the cost with min and update min if needed
		for (int r = i; r <= j; ++r) {
			int cost = optCost(freq, i, r - 1) + optCost(freq, r + 1, j);
			if (cost < min)
				min = cost;
		}

		// Return minimum value
		return min + fsum;
	}

	int optimalSearchTreeDP(int keys[], int freq[], int n) {
		/* Create an auxiliary 2D matrix to store results of subproblems */
		int cost[][] = new int[n][n];

		/*
		 * cost[i][j] = Optimal cost of binary search tree that can be formed
		 * from keys[i] to keys[j]. cost[0][n-1] will store the resultant cost
		 */

		// For a single key, cost is equal to frequency of the key
		for (int i = 0; i < n; i++)
			cost[i][i] = freq[i];

		// Now we need to consider chains of length 2, 3, ... .
		// L is chain length.
		// @formatter:off
		for (int L = 2; L <= n; L++) {
			// i is row number in cost[][]
			for (int i = 0; i < n - L + 1; i++) {
				// Get column number j from row number i and chain length L
				int j = i + L - 1;
				cost[i][j] = Integer.MAX_VALUE;
				// Try making all keys in interval keys[i..j] as root
				for (int r = i; r <= j; r++) {
					// c = cost when keys[r] becomes root of this subtree
					int c = ((r > i) ? cost[i][r - 1] : 0) + ((r < j) ? cost[r + 1][j] : 0) + sum(freq, i, j);
					if (c < cost[i][j])
						cost[i][j] = c;
				}
			}
		}
		// @formatter:on
		return cost[0][n - 1];
	}

	// The main function that calculates minimum cost of a Binary Search Tree.
	// It mainly uses optCost() to find the optimal cost.
	int optimalSearchTree(int keys[], int freq[], int n) {
		// Here array keys[] is assumed to be sorted in increasing order.
		// If keys[] is not sorted, then add code to sort keys, and rearrange
		// freq[] accordingly.
		return optCost(freq, 0, n - 1);
	}

	// A utility function to get sum of array elements freq[i] to freq[j]
	int sum(int freq[], int i, int j) {
		int s = 0;
		for (int k = i; k <= j; k++)
			s += freq[k];
		return s;
	}

	public static void main(String[] args) {
		int keys[] = { 10, 12, 20 };
		int freq[] = { 34, 8, 50 };
		OptimalBinarySearchTree obst = new OptimalBinarySearchTree();
		System.out.printf("Cost of Optimal BST is %d ",
				obst.optimalSearchTreeDP(keys, freq, freq.length));
	}

}
