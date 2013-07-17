package alex.algorithms.graphs;

import java.util.Arrays;

public class MaximalBipartiteMatching {
	// A DFS based recursive function that returns true if a
	// matching for vertex u is possible
	boolean bpm(boolean bpGraph[][], int u, boolean seen[], int matcher[]) {
		if (bpGraph == null || bpGraph[0] == null)
			return false;
		int N = bpGraph[0].length;
		// Try every job one by one
		for (int v = 0; v < N; v++) {
			// If applicant u is interested in job v and v is
			// not visited
			if (bpGraph[u][v] && !seen[v]) {
				seen[v] = true; // Mark v as visited

				// If job 'v' is not assigned to an applicant OR
				// previously assigned applicant for job v (which is matcher[v])
				// has an alternate job available.
				// Since v is marked as visited in the above line, matcher[v]
				// in the following recursive call will not get job 'v' again
				if (matcher[v] < 0 || bpm(bpGraph, matcher[v], seen, matcher)) {
					matcher[v] = u;
					return true;
				}
			}
		}
		return false;
	}

	// Returns maximum number of matching from M to N
	int maxBPM(boolean bpGraph[][]) {
		if (bpGraph == null || bpGraph[0] == null)
			return Integer.MIN_VALUE;
		int M = bpGraph.length;
		int N = bpGraph[0].length;
		// An array to keep track of the applicants assigned to
		// jobs. The value of matchR[i] is the applicant number
		// assigned to job i, the value -1 indicates nobody is
		// assigned.
		int matcher[] = new int[N];

		// Initially all jobs are available
		Arrays.fill(matcher, -1);
		int result = 0; // Count of jobs assigned to applicants
		for (int u = 0; u < M; u++) {
			// Mark all jobs as not seen for next applicant.
			boolean seen[] = new boolean[N];
			// Find if the applicant 'u' can get a job
			if (bpm(bpGraph, u, seen, matcher))
				result++;
		}
		return result;
	}

	public static void main(String[] args) {
		// @formatter:off
		boolean bpGraph[][] = { { false, true, true, false, false, false },
				{ true, false, false, true, false, false },
				{ false, false, true, false, false, false },
				{ false, false, true, true, false, false },
				{ false, false, false, false, false, false },
				{ false, false, false, false, false, true } };
		// @formatter:on
		System.out.println("Maximal Bipartite Matching is "
				+ new MaximalBipartiteMatching().maxBPM(bpGraph));
	}

}
