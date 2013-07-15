package topcoder.alex;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 
 * Problem Statement
 * 
 * 
 * This problem statement contains superscripts and/or subscripts. These may not
 * display properly outside the applet.
 * 
 * Gobble City has a tree topology: There are N intersections, numbered 0
 * through N-1. The intersections are connected by N-1 bidirectional roads in
 * such a way that it is possible to travel between any two intersections. More
 * precisely, for each pair of intersections there is a unique path (a sequence
 * of roads; see Notes for a formal definition) that connects them. You are
 * given a int[] roads that describes the roads: for each i (0 <= i <= N-2), we
 * have 0 <= roads[i] <= i and there is a road between intersections numbered
 * roads[i] and i+1.
 * 
 * On each road there is a single lamp. The lamps are numbered 0 through N-2.
 * For each i, lamp i is on the road that connects roads[i] and i+1. You are
 * given a String initState that describes the initial states of all lamps:
 * initState[i]='1' means that lamp i is initially on, and initState[i]='0'
 * means that it is off.
 * 
 * Now you are at the Control Center for these lamps. The only way in which you
 * can operate the lamps looks as follows: You enter the numbers of two
 * intersections (X and Y) into the Control Center computer, and the computer
 * toggles the state of all lamps on the path between X and Y. (Toggling the
 * state of a lamp means that if the lamp was off it is now on, and vice versa.)
 * You can perform arbitrarily many such operations, one after another.
 * 
 * Some of the lamps are important to you. You are given this information in the
 * String isImportant: isImportant[i]='1' means that lamp i is important, and
 * isImportant[i]='0' means that it is not.
 * 
 * Your goal is to turn on all important lamps at the same time. (We do not care
 * about the state of the remaining lamps.) Return the minimum number of
 * operations needed to achieve the goal.
 * 
 * http://apps.topcoder.com/wiki/display/tc/SRM+583
 */
public class TurnOnLamps {

	int n;
	int state[][] = new int[50][50]; // State of the edge's lamp.
	boolean important[][] = new boolean[50][50]; // If important[u][v], the edge
													// u<->v is important.
	boolean adj[][] = new boolean[50][50]; // Adjacency matrix of the graph.

	int parent[] = new int[50];

	void dfs(int u, int par) {
		parent[u] = par;
		for (int v = 0; v < n; v++) {
			if (adj[u][v] && (!important[u][v] || state[u][v] == 0)
					&& (v != par)) {
				dfs(v, u);
			}
		}
	}

	// Finds the simple path between x and y.
	// If a valid path exists, the first and only element of the result is -1
	// else the first result is the number of important edges between the nodes
	// the remaining contents of the vector are the path itself.
	Integer[] makePath(int x, int y) {
		// We call a DFS, imagining that x is the root, so every vertex
		// reachable from x will have a parent[] vertex in this topology.
		Arrays.fill(parent, 0, n, -1);
		dfs(x, -1);

		if (parent[y] == -1) {
			// There is no valid path (one of the edges in the simple path
			// is important and has state = 0).
			return new Integer[] { -1 };
		} else {
			// Build the path and count the number of important edges:
			LinkedList<Integer> path = new LinkedList<>();
			path.add(0);
			while (parent[y] != -1) {
				if (important[y][parent[y]]) {
					path.set(0, path.get(0) + 1);
				}
				path.add(y);
				y = parent[y];
			}
			path.add(y);
			return path.toArray(new Integer[] {});
		}
	}

	public int minimize(int[] roads, String initState, String isImportant) {
		n = roads.length + 1;
		for (int i = 0; i < adj.length; i++) {
			Arrays.fill(adj[i], false);
		}
		for (int i = 0; i < n - 1; i++) {
			// between roads[i] and i+1
			int u = roads[i], v = i + 1;
			adj[u][v] = adj[v][u] = true;
			state[u][v] = state[v][u] = initState.charAt(i) - '0';
			important[u][v] = important[v][u] = (isImportant.charAt(i) == '1');
		}
		int c = 0;
		while (true) {
			// Try pairs (i,j) of vertices to find the best path available:
			Integer[] bestPath = { -1 };
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					Integer[] newPath = makePath(i, j);
					if (bestPath[0] < newPath[0]) {
						bestPath = newPath;
					}
				}
			}
			// If the best path is 0, the only remaining paths do not have
			// important edges, and it is not needed to change it. If it is -1
			// there are no remaining paths.
			if (bestPath[0] <= 0) {
				break;
			}
			c++;
			// Toggle the states of all the lamps in the path.
			for (int i = 1; i < bestPath.length - 1; i++) {
				int u = bestPath[i], v = bestPath[i + 1];
				state[u][v] = state[v][u] = 1;
				// we know that all the important edges in the path had
				// state[u][v] = 0. The other edges are not important and
				// thus their state does not matter, we can set it as 1.
			}
		}
		return c++;
	}

	public static void main(String[] args) {
		System.out.println(new TurnOnLamps().minimize(new int[] { 0, 0, 1, 2,
				4, 4, 6, 1, 2, 5, 2, 8, 8, 3, 6, 4, 14, 7, 18, 14, 11, 7, 1,
				12, 7, 5, 18, 23, 0, 14, 11, 10, 2, 2, 6, 1, 30, 11, 9, 12, 5,
				35, 25, 11, 23, 17, 14, 45, 15 },
				"0000000000010000000000000010000010100000000000000",
				"1010111111111011011111000110111111111111111110111"));
	}
}
