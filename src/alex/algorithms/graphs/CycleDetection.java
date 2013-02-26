package alex.algorithms.graphs;

import java.util.Arrays;

public class CycleDetection {
	private static final int MAX = 1000;
	static int G[][] = new int[MAX + 1][MAX + 1];
	static int num[] = new int[MAX + 1];
	static int pred[] = new int[MAX + 1];

	public static boolean dfs(int v) {
		num[v]++;
		for (int i = 1; i < G[v].length; i++) {
			int u = G[v][i];
			if (u == 0 || i == v)
				continue;
			if (num[i] == -1) {
				num[i] = 0;
				pred[i] = v;
				dfs(i);
			} else if (num[i] != -1) {
				pred[i] = v;
				System.out.printf("Ciclo encontrado entre %d e %d\n", v, i);
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		G[1][2] = 1;
		G[2][1] = 1;

		G[2][3] = 1;
		G[3][2] = 0;

		G[3][1] = 1;
		G[1][3] = 1;
		Arrays.fill(num, -1);
		Arrays.fill(pred, -1);
		dfs(1);
	}

}
