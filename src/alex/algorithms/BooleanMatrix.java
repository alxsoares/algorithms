package alex.algorithms;

/**
 * Given a boolean matrix mat[M][N] of size M X N, modify it such that if a
 * matrix cell mat[i][j] is 1 (or true) then make all the cells of ith row and
 * jth column as 1.
 * 
 */
public class BooleanMatrix {

	public static void modifyMatrix(int mat[][]) {
//		int row[] = new int[mat.length];
//		int col[] = new int[mat[0].length];
		boolean firstColumn = false;
		boolean firstRow = false;
		
		for(int i=0; i < mat.length; i++){
			if(mat[i][0]==1){
				firstColumn = true;
			}
		}
		for(int i=0; i < mat[0].length; i++){
			if(mat[0][i]==1){
				firstRow = true;
			}
		}
		
		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat[i].length; j++) {
				if (mat[i][j] == 1) {
					mat[i][0] = 1;
					mat[0][j] = 1;
				}
			}
		}

		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat[0].length; j++) {
				if (mat[i][0] == 1 || mat[0][j] == 1) {
					mat[i][j] = 1;
				}
			}
		}
		for(int i=0; i < mat.length; i++){
			if(firstColumn){
				mat[i][0] = 1;
			}else{
				mat[i][0] = 0;
			}
		}
		for(int i=0; i < mat[0].length; i++){
			if(firstRow){
				mat[0][i] = 1;
			}else{
				mat[0][i] = 0;
			}
		}
	}

	public static void printMatrix(int mat[][]) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
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
