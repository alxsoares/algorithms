package alex.algorithms.greedy;

import java.util.Arrays;

import alex.datastructures.Edge;
import alex.datastructures.Graph;
import alex.datastructures.UnionFind;

public class KruskalMST {

	public static void kruskalMST(Graph graph) {
		int V = graph.V;
		Edge result[] = new Edge[V];
		int e = 0;
		int i = 0;

		Arrays.sort(graph.edge);
		UnionFind unionFind = new UnionFind(V);
		while (e < V - 1) {
			Edge nextEdge = graph.edge[i++];

			int x = unionFind.find(nextEdge.src);
			int y = unionFind.find(nextEdge.dest);

			// Test for cycles
			if (x != y) {
				result[e++] = nextEdge;
				unionFind.union(x, y);
			}
		}

		System.out.printf("MST\n");
		for (i = 0; i < e; ++i)
			System.out.printf("%d -- %d == %d\n", result[i].src,
					result[i].dest, result[i].weight);
		return;
	}

	public static void main(String[] args) {
		int V = 4;
		int E = 5;
		Graph graph = Graph.createGraph(V, E);

		graph.edge[0].src = 0;
		graph.edge[0].dest = 1;
		graph.edge[0].weight = 10;

		graph.edge[1].src = 0;
		graph.edge[1].dest = 2;
		graph.edge[1].weight = 6;

		graph.edge[2].src = 0;
		graph.edge[2].dest = 3;
		graph.edge[2].weight = 5;

		graph.edge[3].src = 1;
		graph.edge[3].dest = 3;
		graph.edge[3].weight = 15;

		graph.edge[4].src = 2;
		graph.edge[4].dest = 3;
		graph.edge[4].weight = 4;

		kruskalMST(graph);

	}

}
