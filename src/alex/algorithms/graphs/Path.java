package alex.algorithms.graphs;

import java.util.ArrayList;
import java.util.LinkedList;

public class Path {
    public static boolean BFS(final int source, final int destination, final ArrayList<ArrayList<Integer>> G) {
        if (source == destination)
            return true;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean visited[] = new boolean[G.size()];
        visited[source] = true;
        queue.add(source);
        while (!queue.isEmpty()) {
            int s = queue.poll();
            for (int neigh : G.get(s)) {
                if (neigh == destination)
                    return true;
                if (!visited[neigh]) {
                    visited[neigh] = true;
                    queue.add(neigh);
                }
            }
        }
        return false;
    }

    public static void main(final String[] args) {
        ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>();
        g.add(0, new ArrayList<Integer>());
        g.add(1, new ArrayList<Integer>());
        g.add(2, new ArrayList<Integer>());
        g.add(3, new ArrayList<Integer>());
        g.get(0).add(1);
        g.get(0).add(2);
        g.get(1).add(2);
        g.get(2).add(0);
        g.get(2).add(3);
        g.get(3).add(3);
        System.out.println(BFS(1, 3, g));
    }
    
}
