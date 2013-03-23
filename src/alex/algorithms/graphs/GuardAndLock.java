package alex.algorithms.graphs;

import java.util.Arrays;
import java.util.LinkedList;

public class GuardAndLock {
	static int distance[][] = new int[7][7];

	public static Node BFS(int graph[][]) {

		for (int i = 0; i < distance.length; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}

		boolean isVisited[][] = new boolean[7][7];
		LinkedList<Node> queue = new LinkedList<Node>();

		for (int i = 0; i < graph.length; i++) {

			for (int j = 0; j < graph.length; j++) {
				//Add Guards
				if (graph[i][j] == 3) {
					Node temp = new Node();
					temp.x = i;
					temp.y = j;
					queue.add(temp);
					isVisited[i][j] = true;
					distance[i][j] = 0;
				}

				if (graph[i][j] == 0) {
					isVisited[i][j] = true;
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		Node curr = null;

		while (!queue.isEmpty()) {
			Node root = queue.poll();

			if (root.x + 1 < graph.length) {
				if (!isVisited[root.x + 1][root.y]) {
					Node temp = new Node();
					temp.x = root.x + 1;
					temp.y = root.y;
					queue.add(temp);
					isVisited[root.x + 1][root.y] = true;
					distance[root.x + 1][root.y] = distance[root.x][root.y] + 1;
				}
			}

			if (root.x > 0) {
				if (!isVisited[root.x - 1][root.y]) {
					Node temp = new Node();
					temp.x = root.x - 1;
					temp.y = root.y;
					queue.add(temp);
					isVisited[root.x - 1][root.y] = true;
					distance[root.x - 1][root.y] = distance[root.x][root.y] + 1;
				}
			}
			if (root.y + 1 < graph.length) {
				if (!isVisited[root.x][root.y + 1]) {
					Node temp = new Node();
					temp.x = root.x;
					temp.y = root.y + 1;
					queue.add(temp);
					isVisited[root.x][root.y + 1] = true;
					distance[root.x][root.y + 1] = distance[root.x][root.y] + 1;
				}
			}
			if (root.y > 0) {
				if (!isVisited[root.x][root.y - 1]) {
					Node temp = new Node();
					temp.x = root.x;
					temp.y = root.y - 1;
					queue.add(temp);
					isVisited[root.x][root.y - 1] = true;
					distance[root.x][root.y - 1] = distance[root.x][root.y] + 1;
				}
			}
			curr = root;
		}
		return curr;
	}

	public static void main(String[] args) {
		//@formatter:off
		int matrix[][] = { 
				{1, 1, 1, 1, 1, 1, 1},
				{1, 0, 1, 1, 1, 1, 0},
				{1, 1, 1, 1, 3, 1, 1},
				{1, 1, 1, 0, 1, 1, 1},
				{1, 1, 1, 3, 1, 1, 1},
				{1, 3, 1, 1, 1, 1, 0},
				{1, 1, 1, 1, 1, 1, 3} 
				};
		//@formatter:on

		Node far = BFS(matrix);

		if (far != null) {
			System.out.printf("%d %d %d\n", far.x, far.y,
					distance[far.x][far.y]);
		}
	}
}

class Node {
	int x;
	int y;
}
