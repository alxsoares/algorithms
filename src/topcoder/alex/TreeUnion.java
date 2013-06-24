package topcoder.alex;

import java.util.Arrays;

public class TreeUnion {
	int dist[][] = new int[300][300];

	// Returns a vector c[] such that c[i] is the number of parts
	// of length c[i] that appear in the given tree.
	// tree is represented as a list of integers as described by the
	// input (For each i there is an edge between i+1 and tree[i])
	int[] countPieces(int[] tree, int K) {
		int N = tree.length + 1;
		// Make distance matrix for Floyd-Warshall:
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], 1000000);
		}
		for (int i = 0; i < N - 1; i++) {
			dist[tree[i]][i + 1] = 1;
			dist[i + 1][tree[i]] = 1;
		}
		// Floyd-Warshall:
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		int c[] = new int[K - 1]; // Valid lengths won't exceed K-2
		// For each pair (i,j), find their distance, if the distance is <= 4
		// increment the count c[ distance + 1] (distance is equal to number of
		// vertices - 1)
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (dist[i][j] <= K - 3) {
					c[dist[i][j] + 1]++;
				}
			}
		}
		return c;
	}

	// assume we concatenated together the vector<string>s from the input and
	// turned them into lists of integers:
	double expectedCycles(int[] tree1, int[] tree2, int K) {
		// Extract the number of times each relevant length appears in each
		// tree:
		int[] c1 = countPieces(tree1, K);
		int[] c2 = countPieces(tree2, K);

		int N = tree1.length + 1;
		double res = 0;
		// For each pair (x,y) : x+y = K:
		for (int x = 2; x <= K - 2; x++) {
			int y = K - x;
			// The probability:
			double p = 1 / (double) (N * (N - 1));
			// There are 2 * c1[x] * c2[y] ways to combine these sequences into
			// cycles
			// of length K:
			res += 2.0 * c1[x] * c2[y] * p;
		}
		return res;
	}

	int[] vec(String[] vs) {
		String s = "";
		for (int i = 0; i < vs.length; i++) {
			s = s + vs[i];
		}
		String t[] = s.split("\\s+");
		int[] v = new int[t.length];
		for (int i = 0; i < t.length; i++) {
			v[i] = Integer.valueOf(t[i]);
		}
		return v;
	}

	public double expectedCycles(String[] tree1, String[] tree2, int K) {
		return expectedCycles(vec(tree1), vec(tree2), K);
	}

	public static void main(String[] args) {
		TreeUnion tu = new TreeUnion();
		double expectedCycles = tu.expectedCycles(new String[] { "0 1 2 0 1 2 0 1 2 5 6 1", "0 11",
				" 4" },

		new String[] { "0 1 1 0 2 3 4 3 4 6 6", " 10 12 12" }, 7);
		System.out.println(expectedCycles);
	}
}
