package alex.algorithms.arrays;


public class TransposeMatrix {

	static int pred(int k, int L, int M) {
		return (k % M) * L + k / M;
	}

	static void transpose(int m[], int L, int M) {
		int LxM = L * M;
		int i, j, k, stillToMove;
		for (i = 0, stillToMove = LxM; stillToMove > 0; i++) {
			for (j = pred(i, L, M); j > i; j = pred(j, L, M))
				;
			if (j < i)
				continue;
			for (k = i, j = pred(i, L, M); j != i; k = j, j = pred(j, L, M)) {
				exchange(m, k, j);
				--stillToMove;
			}
			--stillToMove;
		}
	}

	private static void exchange(int[] m, int k, int j) {
		int aux = m[k];
		m[k] = m[j];
		m[j] = aux;
	}

	// nxn caso
	public static void rotate(int[][] m) {
		// first transpose
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < i; j++) {
				int aux = m[i][j];
				m[i][j] = m[j][i];
				m[j][i] = aux;
			}
		}
		// reverse lines
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length / 2; j++) {
				int aux = m[i][j];
				m[i][j] = m[i][m.length - j - 1];
				m[i][m.length - j - 1] = aux;
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int m[] = { 1, 2, 3, 4, 5, 6 };

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(m[i * 2 + j]);
			}
			System.out.println();
		}
		transpose(m, 2, 3);
		System.out.println();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(m[i * 3 + j]);
			}
			System.out.println();
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3 / 2; j++) {
				int aux = m[i * 3 + j];
				m[i * 3 + j] = m[i * 3 + j + 3 - 1];
				m[i * 3 + j + 3 - 1] = aux;

			}
		}
		System.out.println();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(m[i * 3 + j]);
			}
			System.out.println();
		}
		int[][] nn = {{1,2,3},{4,5,6},{7,8,9}};
		for (int i = 0; i < nn.length; i++) {
			for (int j = 0; j < nn.length; j++) {
				System.out.printf("%d ",nn[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		rotate(nn);
		for (int i = 0; i < nn.length; i++) {
			for (int j = 0; j < nn.length; j++) {
				System.out.printf("%d ",nn[i][j]);
			}
			System.out.println();
		}
		
	}
}
