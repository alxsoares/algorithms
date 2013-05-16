package alex.algorithms.math.projecteuler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Euler107 {

	public static void primMST(final List<ArrayList<Integer>> G,
			final int W[][]) {

		int V = G.size();
		int parent[] = new int[V];
		final Integer key[] = new Integer[V];

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(G.size(),
				new Comparator<Integer>() {
					@Override
					public int compare(final Integer o1, final Integer o2) {
						return key[o1].compareTo(key[o2]);
					}
				});

		for (int v = 1; v < V; ++v) {
			parent[v] = -1;
			key[v] = Integer.MAX_VALUE;
		}

		key[0] = 0;
		minHeap.add(0);

		while (!minHeap.isEmpty()) {
			int u = minHeap.remove();
			List<Integer> adj = G.get(u);
			for (Integer v : adj) {
				if (minHeap.contains(v) && W[u][v] < key[v]) {
					key[v] = W[u][v];
					parent[v] = u;
					minHeap.remove(v);
					minHeap.add(v);
				}
			}
		}
		printArr(parent);
	}

	static void printArr(final int arr[]) {
		for (int i = 1; i < arr.length; ++i)
			System.out.printf("%d - %d\n", arr[i], i);
	}

	public static void primMST(final int G[][]) {

		int V = G.length;
		int parent[] = new int[V];
		int key[] = new int[V];
		boolean mstSet[] = new boolean[V];

		for (int i = 0; i < V; i++) {
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}

		key[0] = 0;
		parent[0] = -1;

		for (int count = 0; count < V - 1; count++) {

			int u = minKey(key, mstSet);
			mstSet[u] = true;

			for (int v = 0; v < V; v++)

				if (G[u][v] > 0 && !mstSet[v] && G[u][v] < key[v]) {
					parent[v] = u;
					key[v] = G[u][v];
				}
		}

		printMST(parent, V, G);
	}

	private static void printMST(final int[] parent, final int v,
			final int[][] G) {
		int V = G.length;
		int sum =0;
		for (int i = 1; i < V; i++) {
			System.out
					.printf("%d - %d    %d \n", parent[i], i, G[i][parent[i]]);
			sum+=G[i][parent[i]];
		}
		System.out.println(sum);
	}

	private static int minKey(final int[] key, final boolean[] mstSet) {
		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		int V = key.length;

		for (int v = 0; v < V; v++)
			if ((!mstSet[v]) && key[v] < min) {
				min = key[v];
				minIndex = v;
			}

		return minIndex;
	}

	public static void main(String[] args) throws IOException {
		String line;
		InputStream fis = new FileInputStream("/root/network.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		int[][] G = new int[40][40];
		int i = 0;
		int totalSum=0;
		while ((line = br.readLine()) != null) {
			String[] edges = line.split(",");
			for (int j = 0; j < edges.length; j++) {
				String e = edges[j].trim();
				if (!"-".equals(e)) {
					G[i][j] = Integer.valueOf(e);
					totalSum+=G[i][j];
				}
			}
			i++;
		}
		
		primMST(G);
		System.out.println(totalSum/2);
		System.out.println(261832 - 2153);
	}

}
