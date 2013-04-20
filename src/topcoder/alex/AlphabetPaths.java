package topcoder.alex;

public class AlphabetPaths {

	final String LATIN = "ABCDEFZHIKLMNOPQRSTVX";

	// maximum number of sets of 11 elements out of 21
	// simply Combinations(21,11).
	final int MAX_SETS = 352716;

	final int HALF = 11;
	final int ALL = 21;
	int[] setCount = new int[1 << ALL];
	// stores the number of paths that contain a given set of letters.
	int[] foundSets = new int[MAX_SETS];
	// stores the sets of letters found during the backtrack.
	int total; // stores the total of those sets ^
	final int[] dx = new int[] { 0, 0, 1, -1 };
	final int[] dy = new int[] { 1, -1, 0, 0 };
	int w, h;
	int[][] maze;

	void backtrack(int x, int y, int mask, int n) {
		int c = maze[x][y];
		if (c != -1) {
			if ((mask & (1 << c)) == 0) { // If the cell contains a new valid
											// letter, add it to the set
				int nmask = mask | (1 << c);
				if (n + 1 == HALF) { // already found 11 letters, process the
										// path:
					// if the count was zero, this is the first time we
					// find a path of this kind, add its set to the list.
					if (setCount[nmask] == 0) {
						foundSets[total++] = nmask;
					}
					setCount[nmask]++;
				} else {
					for (int i = 0; i < 4; i++) {
						int x2 = x + dx[i];
						int y2 = y + dy[i];
						// Recursively try adjacent cells (x2, y2):
						if (x2 >= 0 && y2 >= 0 && x2 < w && y2 < h) {
							backtrack(x2, y2, nmask, n + 1);
						}
					}
				}
			}
		}
	}

	public long count(String[] letterMaze) {
		w = letterMaze.length;
		h = letterMaze[0].length();
		maze = new int[w][h];
		// Fill an array with ordered ids of the letters in letterMaze
		// or -1 if empty.
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				maze[i][j] = -1;
				for (int k = 0; k < LATIN.length(); k++) {
					if (LATIN.charAt(k) == letterMaze[i].charAt(j)) {
						maze[i][j] = k;
					}
				}
			}
		}

		long res = 0;

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int c = maze[x][y];
				if (c == -1) {
					continue;
				}
				int alwaysInSet = (1 << c);
				total = 0;
				// Find and process all paths of 11 letters that begin
				// at cell (x,y ) :
				backtrack(x, y, 0, 0);

				// Merge the paths to count the paths of length 21:
				for (int i = 0; i < total; i++) {
					int set = foundSets[i];
					// Find the complement:
					int cmp = ((((1 << ALL) - 1) & ~set) | alwaysInSet);
					res += setCount[set] * (long) setCount[cmp];
				}
				// reset to 0:
				for (int i = 0; i < total; i++) {
					int nmask = foundSets[i];
					setCount[nmask] = 0;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		//@formatter:off
		System.out.printf(
				"%d\n",
				new AlphabetPaths().count(new String[] { 
						"ABCDEF.", 
						"V....Z.",
						"T....H.", 
						"S....I.", 
						"R....X.", 
						"KLMNOPQ" }));
		//@formatter:on
	}

}
