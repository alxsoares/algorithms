package topcoder.alex;

public class ImportsList {
	int N = 52;
	int visited[] = new int[N];
	int order[] = new int[N];
	int cycleDetect;
	int adjmat[][] = new int[N][N];
	int dfsIdx;
	long mask[] = new long[N];
	long procMask[] = new long[N];

	void dfs(int node) {
		if (visited[node] > 0) {
			if (visited[node] == 1)
				cycleDetect = 1;
			return;
		}
		visited[node] = 1;
		for (int i = 0; i < N; i++) {
			if (adjmat[node][i] > 0) {
				dfs(i);
			}
		}
		visited[node] = 2;
		order[dfsIdx] = node;
		dfsIdx--;
	}

	public int[] importsCount(String[] requires) {
		int[] res = new int[requires.length];
		for (int i = 0; i < requires.length; i++) {
			long m = 0;
			for (int j = 0; j < requires.length; j++) {
				if (requires[i].charAt(j) == 'Y') {
					adjmat[i][j] = 1;
					m += (1L << j);
				}
			}
			mask[i] = m;
		}
		dfsIdx = requires.length - 1;
		for (int i = 0; i < requires.length; i++) {
			if (visited[i] <= 0) {
				dfs(i);
			}
		}
		for (int i = requires.length - 1; i >= 0; i--) {
			long m = mask[order[i]];
			long p = m;
			int num = 0;
			for (int j = i + 1; j < requires.length && m != 0; j++) {
				if (adjmat[order[i]][order[j]] > 0
						&& (m & (1L << order[j])) > 0) {
					num++;
					p |= procMask[order[j]];
					m = m & ~(procMask[order[j]]);
				}
			}
			procMask[order[i]] = p;
			res[order[i]] = num;
		}
		return res;
	}

	public static void main(String[] args) {
		String[] g = { "NYYY", "NNYY", "NNNY", "NNNN" };
		ImportsList i = new ImportsList();
		int[] c = i.importsCount(g);
		for (int j = 0; j < c.length; j++) {
			System.out.println(c[j]);
		}
	}

}
