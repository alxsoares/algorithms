package topcoder.alex;

import java.util.Arrays;

/**
 * 
 * http://wilanw.blogspot.com.br/2009/07/tc-srm360-d1-500.html
 */
public class PrinceOfPersia {

	private static final Integer INF = 999999999;
	// the maximum number of vertices
	private int NN = 256;

	// adjacency matrix (fill this up)
	private int cap[][] = new int[NN][NN];

	// flow network
	private int fnet[][] = new int[NN][NN];

	// BFS
	int q[] = new int[NN];
	int qf, qb;
	int prev[] = new int[NN];

	int dx[] = { -1, 1, 0, 0 };
	int dy[] = { 0, 0, -1, 1 };

	public int minObstacles(String[] maze) {
		boolean gotP = false;
		int source = 0, sink = 0;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length(); j++) {
				// assign the source and sink
				if (maze[i].charAt(j) == 'P' && !gotP) {
					source = (i * maze[0].length() + j) * 2;
					gotP = true;
				} else if (maze[i].charAt(j) == 'P' && gotP) {
					sink = (i * maze[0].length() + j) * 2;
				}
				// build connections
				if (maze[i].charAt(j) == '.') {
					cap[(i * maze[0].length() + j) * 2][(i * maze[0].length() + j) * 2 + 1] = 1;
				} else if (maze[i].charAt(j) == 'P') {
					cap[(i * maze[0].length() + j) * 2][(i * maze[0].length() + j) * 2 + 1] = INF;
				}
				// iterate through adjacent cells
				for (int k = 0; k < 4; k++) {
					int mi = i + dx[k];
					int mj = j + dy[k];
					if (mi < 0 || mj < 0 || mi >= maze.length
							|| mj >= maze[0].length())
						continue;
					// impossible case
					if (maze[mi].charAt(mj) == 'P' && maze[i].charAt(j) == 'P')
						return -1;
					if (maze[mi].charAt(mj) != '#') {
						cap[(i * maze[0].length() + j) * 2 + 1][(mi
								* maze[0].length() + mj) * 2] = INF;
					}
				}
			}
		}
		int flow = fordFulkerson(maze[0].length() * maze.length * 2, source,
				sink);
		return flow;
	}

	private int fordFulkerson(int n, int s, int t) {
		for (int i = 0; i < fnet.length; i++)
			Arrays.fill(fnet[i], 0);
		int flow = 0;
		// find an augmenting path of at least 1
		while (true) {
			Arrays.fill(prev, -1);
			qf = qb = 0;
			prev[q[qb++] = s] = -2;
			while (qb > qf && prev[t] == -1)
				for (int u = q[qf++], v = 0; v < n; v++)
					if (prev[v] == -1 && fnet[u][v] - fnet[v][u] < cap[u][v])
						prev[q[qb++] = v] = u;
			if (prev[t] == -1)
				break;
			// get the bottleneck capacity
			int bot = 0x7FFFFFFF;
			for (int v = t, u = prev[v]; u >= 0; v = u, u = prev[v])
				bot = Math.min(bot, cap[u][v] - fnet[u][v] + fnet[v][u]);
			// update the flow network
			for (int v = t, u = prev[v]; u >= 0; v = u, u = prev[v])
				fnet[u][v] += bot;
			flow += bot;
		}
		return flow;
	}

	public static void main(String[] args) {
		String[] maze = { "P....", "...##", "##...", "....P" };
		System.out.printf("%d\n", new PrinceOfPersia().minObstacles(maze));
	}

}
