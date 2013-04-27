package topcoder.alex;
/*
 * SRM197-D1-600
 * SOLVED
 * UGLY
 */

import static java.lang.Math.min;

public class Paths {

	long INF = Long.MAX_VALUE >> 4;

	public int secondBest(String[] graph, int from, int to) {
		long[][] dist = floyd(graph);
		long[][] second = second(graph, dist, from, to);

		if (second[from][to] == INF)
			return -1;

		return (int) second[from][to];
	}

	private long[][] second(String[] graph, long[][] dist, int from, int to) {
		long[][] second = new long[graph.length][graph.length];

		for (int i = 0; i < second.length; i++)
			for (int j = 0; j < second.length; j++)
				if (graph[i].charAt(j) != 'X' && graph[i].charAt(j) - '0' > dist[i][j])
					second[i][j] = graph[i].charAt(j) - '0';
				else
					second[i][j] = INF;

		for (int k = 0; k < graph.length; k++)
			for (int i = 0; i < graph.length; i++)
				for (int j = 0; j < graph.length; j++)
					if (dist[i][k] + dist[k][j] > dist[i][j])
						second[i][j] = min(second[i][j], dist[i][k] + dist[k][j]);

		for (int k = 0; k < graph.length; k++)
			for (int i = 0; i < graph.length; i++)
				for (int j = 0; j < graph.length; j++) {
					second[i][j] = min(second[i][j], dist[i][k] + second[k][j]);
					second[i][j] = min(second[i][j], second[i][k] + dist[k][j]);
				}

		for (int k = 0; k < graph.length; k++)
			for (int i = 0; i < graph.length; i++)
				for (int j = 0; j < graph.length; j++) {
					second[i][j] = min(second[i][j], dist[i][k] + second[k][j]);
					second[i][j] = min(second[i][j], second[i][k] + dist[k][j]);
				}

		return second;
	}

	public long[][] floyd(String[] graph) {
		long[][] dist = new long[graph.length][graph.length];

		for (int i = 0; i < dist.length; i++)
			for (int j = 0; j < dist.length; j++)
				if (i == j)
					dist[i][j] = 0;
				else if (graph[i].charAt(j) == 'X')
					dist[i][j] = INF;
				else
					dist[i][j] = graph[i].charAt(j) - '0';

		for (int k = 0; k < graph.length; ++k)
			for (int i = 0; i < graph.length; i++)
				for (int j = 0; j < graph.length; j++)
					dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);

		return dist;
	}
}
