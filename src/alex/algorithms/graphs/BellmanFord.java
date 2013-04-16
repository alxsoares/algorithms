package alex.algorithms.graphs;

import alex.datastructures.Graph;

public class BellmanFord {

	public static void bellmanFord(Graph graph, int src) {
		int V = graph.V;
		int E = graph.E;
		int dist[] = new int[V];
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		dist[src] = 0;

		for (int i = 1; i <= V - 1; i++) {
			for (int j = 0; j < E; j++) {
				int u = graph.edge[j].src;
				int v = graph.edge[j].dest;
				if (dist[u] == Integer.MAX_VALUE)
					continue;
				int weight = graph.edge[j].weight;
				if (dist[u] + weight < dist[v]) {
					dist[v] = dist[u] + weight;
				}
			}
		}

		for (int i = 0; i < E; i++) {
			int u = graph.edge[i].src;
			int v = graph.edge[i].dest;
			int weight = graph.edge[i].weight;
			if (dist[u] + weight < dist[v])
				System.out.printf("Graph contains negative weight cycle");
		}
		printArr(dist);
	}

	public static void printArr(int dist[]) {
		System.out.printf("Vertex   Distance from Source\n");
		for (int i = 0; i < dist.length; ++i)
			System.out.printf("%d \t\t %d\n", i, dist[i]);
	}

	public static void main(String[] args) {
	    int V = 5; 
	    int E = 8; 
	    Graph graph = Graph.createGraph(V, E);
	 
	    graph.edge[0].src = 0;
	    graph.edge[0].dest = 1;
	    graph.edge[0].weight = -1;
	 
	    graph.edge[1].src = 0;
	    graph.edge[1].dest = 2;
	    graph.edge[1].weight = 4;
	 
	    graph.edge[2].src = 1;
	    graph.edge[2].dest = 2;
	    graph.edge[2].weight = 3;
	 
	    graph.edge[3].src = 1;
	    graph.edge[3].dest = 3;
	    graph.edge[3].weight = 2;
	 
	    graph.edge[4].src = 1;
	    graph.edge[4].dest = 4;
	    graph.edge[4].weight = 2;
	 
	    graph.edge[5].src = 3;
	    graph.edge[5].dest = 2;
	    graph.edge[5].weight = 5;
	 
	    graph.edge[6].src = 3;
	    graph.edge[6].dest = 1;
	    graph.edge[6].weight = 1;
	 
	    graph.edge[7].src = 4;
	    graph.edge[7].dest = 3;
	    graph.edge[7].weight = -3;
	    
	    bellmanFord(graph, 0);
	 
	}

}
