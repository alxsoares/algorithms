package alex.algorithms.graphs;

public class PrimMST {

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
    }
}
