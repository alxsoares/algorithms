package alex.algorithms;

/**
 * Given a boolean matrix mat[M][N] of size M X N, modify it such that if a
 * matrix cell mat[i][j] is 1 (or true) then make all the cells of ith row and
 * jth column as 1.
 * 
 */
public class BooleanMatrix {

	public static void modifyMatrix(int mat[][]) {
		int row[] = new int[mat.length];
		int col[] = new int[mat[0].length];

		int i, j;

		for (i = 0; i < mat.length; i++) {
			for (j = 0; j < mat[i].length; j++) {
				if (mat[i][j] == 1) {
					row[i] = 1;
					col[j] = 1;
				}
			}
		}

		for (i = 0; i < mat.length; i++) {
			for (j = 0; j < mat[0].length; j++) {
				if (row[i] == 1 || col[j] == 1) {
					mat[i][j] = 1;
				}
			}
		}
	}

	public static void printMatrix(int mat[][]) {
		int i, j;
		for (i = 0; i < mat.length; i++) {
			for (j = 0; j < mat[i].length; j++) {
				System.out.printf("%d ", mat[i][j]);
			}
			System.out.printf("\n");
		}
	}

	public static void main(String[] args) {
		int mat[][] = { { 1, 0, 0, 1 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, };

		System.out.printf("Input Matrix \n");
		printMatrix(mat);

		modifyMatrix(mat);

		System.out.printf("Matrix after modification \n");
		printMatrix(mat);
	}

}
