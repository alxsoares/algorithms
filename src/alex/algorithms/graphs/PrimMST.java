package alex.algorithms.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class PrimMST {

    public void primMST(final List<ArrayList<Integer>> G, final int W[][]) {

        int V = G.size();
        int parent[] = new int[V];
        final Integer key[] = new Integer[V];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(G.size(), new Comparator<Integer>() {
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

    void printArr(final int arr[]) {
        for (int i = 1; i < arr.length; ++i)
            System.out.printf("%d - %d\n", arr[i], i);
    }

    public void primMST(final int G[][]) {

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

    private void printMST(final int[] parent, final int v, final int[][] G) {
        int V = G.length;
        for (int i = 1; i < V; i++)
            System.out.printf("%d - %d    %d \n", parent[i], i, G[i][parent[i]]);

    }

    private int minKey(final int[] key, final boolean[] mstSet) {
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

    public static void main(final String[] args) {
        //@formatter:off
        int G[][] = {{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
               };
      //@formatter:on
        PrimMST p = new PrimMST();
        p.primMST(G);

        Random rand = new Random(System.currentTimeMillis());

        ArrayList<ArrayList<Integer>> GL = new ArrayList<ArrayList<Integer>>();
        int[][] W = new int[100][100];
        for (int i = 0; i < 100; i++) {
            GL.add(new ArrayList<Integer>());
            for (int j = 0; j < 100; j++) {
                if (i == 0 && j == 99) {
                    GL.get(i).add(j, 1);
                } else if (i == j) {
                    GL.get(i).add(j, 0);
                } else if (j < i) {
                    GL.get(i).add(j, GL.get(j).get(i));
                } else {
                    int w = (Math.abs(rand.nextInt()) % 50) + 10;
                    GL.get(i).add(j, w);
                    W[i][j] = w;
                }
            }
        }
        p.primMST(GL, W);
    }
}
