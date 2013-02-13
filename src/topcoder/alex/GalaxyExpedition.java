package topcoder.alex;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class GalaxyExpedition {
    /**
     * Grafo conexo
     * 
     * @param G
     * @param n
     * @return
     */
    public Integer[][] BFS(final Integer G[][], final int n) {
        Integer T[][] = new Integer[n][n];
        int W[] = new int[n];// whites
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(G[0][0]);
        W[0] = 1;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int i = 0; i < n; i++) {
                Integer v = G[u][i];
                if (v != null && W[v] == 0) {
                    W[v] = 1;
                    q.add(v);
                    T[u][v] = 1;
                }
            }
            W[u] = 2;
        }

        return T;
    }

    public int[] possibleValues(final String[] dependencies) {
        final int n = dependencies.length;
        Integer G[][] = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            String s = dependencies[i];
            String ds[] = s.split(" ");
            for (int j = 0; j < ds.length; j++) {
                String t = ds[j];
                if (t != null && !"".equals(t.trim())) {
                    G[i][Integer.valueOf(t)] = 1;
                }
            }
        }
        // COnstruir arvore geradora
        TreeSet<Integer> visited = new TreeSet<Integer>();

        return null;
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        String a = "a b c";
        String x[] = a.split(" ");
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }

    }

}
