package alex.algorithms.math.projecteuler.level4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Bellman-Ford solution.
 * 
 */
public class Euler083 {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		String line;
		InputStream fis = new FileInputStream("/root/matrix.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		try {
			int[][] G = new int[80][80];
			int[][] D = new int[80][80];
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] edges = line.split(",");
				for (int j = 0; j < edges.length; j++) {
					String e = edges[j].trim();
					G[i][j] = Integer.valueOf(e);
					D[i][j] = Integer.MAX_VALUE;
				}
				i++;
			}
			int dx[] = { 0, -1, 1, 0 };
			int dy[] = { 1, 0, 0, -1 };
			D[0][0] = G[0][0];
			int n = 80;
			for (int times = 0; times < n; times++) {
				for (i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (D[i][j] == Integer.MAX_VALUE)
							continue;
						for (int d = 0; d < 4; d++) {
							int x = i + dx[d];
							int y = j + dy[d];
							if (isSafe(x, y, n)) {
								int dist = D[i][j] + G[x][y];
								if (D[x][y] > dist) {
									D[x][y] = dist;
								}
							}
						}
					}
				}
			}
			System.out.println(D[n - 1][n - 1]);
		} finally {
			br.close();
		}

	}

	public static boolean isSafe(int a, int b, int n) {
		return ((a >= 0 && a < n) && (b >= 0 && b < n));
	}

}
