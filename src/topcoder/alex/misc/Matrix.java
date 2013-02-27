package topcoder.alex.misc;

public class Matrix {

	public static int[][] multiply(int a[][], int b[][]) {

		int aRows = a.length, aColumns = a[0].length, bRows = b.length, bColumns = b[0].length;

		if (aColumns != bRows) {
			throw new IllegalArgumentException("A:Rows: " + aColumns
					+ " did not match B:Columns " + bRows + ".");
		}

		int[][] resultant = new int[aRows][bColumns];

		for (int i = 0; i < aRows; i++) { // aRow
			for (int j = 0; j < bColumns; j++) { // bColumn
				for (int k = 0; k < aColumns; k++) { // aColumn
					resultant[i][j] += a[i][k] * b[k][j];
				}
			}
		}

		return resultant;
	}

	public static void print(int[][] mat) {
		// 1 2 3
		// 4 5 6
		// 7 8 9
		// 10 11 12
		int n = mat.length;
		int m = mat[0].length;
		boolean row = true;
		boolean incr = true;
		int i = 0, j = 0;
		while (true) {
			if (row) {
				if (incr) {
					while (j < m) {
						System.out.printf("%d ", mat[i][j++]);
					}
				} else {
					while (j >= mat[0].length - m) {
						System.out.printf("%d ", mat[i][j--]);
					}
				}
			} else {
				if (incr) {
					while (i < n) {
						System.out.printf("%d ", mat[i++][j]);
					}
				} else {
					while (i > mat.length - n) {
						System.out.printf("%d ", mat[i--][j]);
					}
				}
			}
			if (row && incr) {
				row = false;
				j--;
				i++;
			} else if (!row && incr) {
				row = true;
				incr = false;
				i--;j--;
			} else if (row && !incr) {
				row = false;
				j++;i--;
			} else {
				row = true;
				incr = true;
				m--;
				n--;
				i = mat.length - n;
				j = mat[0].length - m;
				if (i > mat.length / 2 || j > mat[0].length/2)
					break;
			}

		}

	}

	public static void main(String[] args) {
		int mat[][] = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
		print(mat);
	}

}
