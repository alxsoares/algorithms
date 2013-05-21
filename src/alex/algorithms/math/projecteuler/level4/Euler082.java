package alex.algorithms.math.projecteuler.level4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Euler082 {

    public static void main(final String[] args) throws NumberFormatException, IOException {
        String line;
        InputStream fis = new FileInputStream("/home/alex/matrix.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        try {
            int[][] G = new int[80][80];
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] edges = line.split(",");
                for (int j = 0; j < edges.length; j++) {
                    String e = edges[j].trim();
                    G[i][j] = Integer.valueOf(e);
                }
                i++;
            }
            int gridSize = G.length;
            int[] sol = new int[gridSize];
            // initialise solution
            for (i = 0; i < gridSize; i++) {
                sol[i] = G[i][gridSize - 1];
            }
            for (i = gridSize - 2; i >= 0; i--) {
                // Traverse down
                sol[0] += G[0][i];
                for (int j = 1; j < gridSize; j++) {
                    sol[j] = Math.min(sol[j - 1] + G[j][i], sol[j] + G[j][i]);
                }

                // Traverse up
                for (int j = gridSize - 2; j >= 0; j--) {
                    sol[j] = Math.min(sol[j], sol[j + 1] + G[j][i]);
                }
            }
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < sol.length; j++) {
                if (min > sol[j]) {
                    min = sol[j];
                }
            }
            System.out.printf("%d\n", min);
        } finally {
            br.close();
        }
    }
}

