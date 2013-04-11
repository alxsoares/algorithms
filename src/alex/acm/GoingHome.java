package alex.acm;

import java.util.Arrays;

/**
 * 
 * http://wilanw.blogspot.com.br/search/label/Network%20Flow
 * 
 */
public class GoingHome {

	int cost[][] = new int[111][111]; // cost of assigning house i to person j
	int curMatchings[] = new int[111]; // house i matched to person
										// curMatchings[i]
	int delta[][] = new int[111][111]; // the delta/cost graph
	int parent[][] = new int[111][111]; // used for backtracking the cycle
	int cycle[] = new int[111]; // path of the cycle
	int totalLen; // length of the cycle

	boolean augmentingCycle(int N) {
		for (int i = 0; i < delta.length; i++) {
			Arrays.fill(delta[i], 0);
		}
		for (int i = 0; i < parent.length; i++) {
			Arrays.fill(parent[i], 0);
		}
		Arrays.fill(cycle, -1);
		// derive the delta graph
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				delta[i][j] = cost[i][curMatchings[j]]
						- cost[i][curMatchings[i]];
				parent[i][j] = j;
			}
		}
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (delta[i][k] + delta[k][j] < delta[i][j]) {
						// minimum cost matching
						delta[i][j] = delta[i][k] + delta[k][j];
						// book-keep the optimal path so far for i->j
						parent[i][j] = parent[i][k];
						// detect for cycle (if vertex i and j are the same
						// vertex)
						if (i == j) {
							totalLen = 0;
							// backtrack and construct the cycle path
							do {
								cycle[totalLen++] = i;
								i = parent[i][j];
							} while (i != j);
							return true;
						}
					}
				}
			}
		}
		// did not find an augmenting path
		return false;
	}

	int cycleCancel(int N) {
		int res = 0;
		// pseudo max-flow for assignment problem
		for (int i = 0; i < N; i++)
			curMatchings[i] = i;
		while (augmentingCycle(N)) {
			// augment the negative cycle
			int r = curMatchings[cycle[0]];
			for (int i = 0; i < totalLen - 1; i++) {
				curMatchings[cycle[i]] = curMatchings[cycle[i + 1]];
			}
			curMatchings[cycle[totalLen - 1]] = r;
		}
		// compute the final cost
		for (int i = 0; i < N; i++) {
			res += cost[i][curMatchings[i]];
		}
		return res;
	}

	public static void main(String[] args) {

	}

}
