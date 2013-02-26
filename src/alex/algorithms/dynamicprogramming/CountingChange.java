package alex.algorithms.dynamicprogramming;

public class CountingChange {
	private static int MAXTOTAL = 10000;
	static long nway[] = new long[MAXTOTAL + 1];
	static int coin[] = { 50, 25, 10, 5, 1 };

	public static void main(String[] args) {
		int v = 5;
		int n=5;
		nway[0] = 1;
		for (int i = 0; i < v; i++) {
			int c = coin[i];
			for (int j = c; j <= n; j++)
				nway[j] += nway[j - c];
		}
		System.out.printf("%d\n", nway[n]);

	}

}
