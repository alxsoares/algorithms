package alex.algorithms.graphs;

import java.util.Arrays;

public class StableMarriage {

	// This function returns true if woman 'w' prefers man 'm1' over man 'm'
	boolean wPrefersM1OverM(int prefer[][], int w, int m, int m1) {
		int N = prefer.length / 2;
		// Check if w prefers m over her current engagment m1
		for (int i = 0; i < N; i++) {
			// If m1 comes before m in lisr of w, then w prefers her
			// cirrent engagement, don't do anything
			if (prefer[w][i] == m1)
				return true;

			// If m cmes before m1 in w's list, then free her current
			// engagement and engage her with m
			if (prefer[w][i] == m)
				return false;
		}
		return false;
	}

	// Prints stable matching for N boys and N girls. Boys are numbered as 0 to
	// N-1. Girls are numbereed as N to 2N-1.
	void stableMarriage(int prefer[][]) {
		// Stores partner of women. This is our output array that
		// stores paing information. The value of wPartner[i]
		// indicates the partner assigned to woman N+i. Note that
		// the woman numbers between N and 2*N-1. The value -1
		// indicates that (N+i)'th woman is free
		int N = prefer.length/2;
		int wPartner[] = new int[N];

		// An array to store availability of men. If mFree[i] is
		// true, then man 'i' is free
		boolean mFree[] = new boolean[N];

		// Initialize all men and women as free
		Arrays.fill(wPartner, -1);
		Arrays.fill(mFree, false);
		int freeCount = N;

		// While there are free men
		while (freeCount > 0) {
			// Pick the first free man (we could pick any)
			int m;
			for (m = 0; m < N; m++)
				if (mFree[m] == false)
					break;

			// One by one go to all women according to m's preferences.
			// Here m is the picked free man
			for (int i = 0; i < N && mFree[m] == false; i++) {
				int w = prefer[m][i];

				// The woman of preference is free, w and m become
				// partners (Note that the partnership maybe changed
				// later). So we can say they are engaged not married
				if (wPartner[w - N] == -1) {
					wPartner[w - N] = m;
					mFree[m] = true;
					freeCount--;
				}
				// If w is not free
				else {
					// Find current engagement of w
					int m1 = wPartner[w - N];

					// If w prefers m over her current engagement m1,
					// then break the engagement between w and m1 and
					// engage m with w.
					if (!wPrefersM1OverM(prefer, w, m, m1)) {
						wPartner[w - N] = m1;
						mFree[m] = true;
						mFree[m1] = false;
					}
				}
			}
		}

		System.out.println("Woman   Man");
		for (int i = 0; i < N; i++)
			System.out.println(" " + (i + N) + "\t" + wPartner[i]);
		System.out.println();
	}
	public static void main(String[] args) {
		//@formatter:off
		int prefer[][] = { {7, 5, 6, 4},
		        {5, 4, 6, 7},
		        {4, 5, 6, 7},
		        {4, 5, 6, 7},
		        {0, 1, 2, 3},
		        {0, 1, 2, 3},
		        {0, 1, 2, 3},
		        {0, 1, 2, 3},
		    };
		//@formatter:on
		    new StableMarriage().stableMarriage(prefer);
	}

}
