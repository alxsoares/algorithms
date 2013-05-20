package alex.algorithms.math.projecteuler.level4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Euler081 {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		String line;
		InputStream fis = new FileInputStream("/root/matrix.txt");
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
			int size = G.length;
			//base solution
			for (i = size - 2; i >= 0; i--) {
				G[size - 1][i] += G[size - 1][i + 1];
				G[i][size - 1] += G[i + 1][size - 1];
			}
			
			for (i = size - 2; i >= 0; i--) {
				for (int j = size - 2; j >= 0; j--) {
					G[i][j] += Math.min(G[i + 1][j], G[i][j + 1]);
				}
			}
			
			System.out.printf("%d\n", G[0][0]);
		} finally {
			br.close();
		}
	}

}
