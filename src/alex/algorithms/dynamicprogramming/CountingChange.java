package alex.algorithms.dynamicprogramming;

public class CountingChange {
	private static int MAXTOTAL = 10000;
	static long nway[] = new long[MAXTOTAL + 1];
	static int coin[] = { 50, 25, 10, 5,2, 1 };

	public static void main(String[] args) {
		int n=4;
		nway[0] = 1;
		for (int i = 0; i < coin.length; i++) {
			int c = coin[i];
			for (int j = c; j <= n; j++)
				nway[j] += nway[j - c];
		}
		System.out.printf("%d\n", nway[n]);

	}

}
