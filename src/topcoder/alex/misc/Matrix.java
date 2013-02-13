package topcoder.alex.misc;

public class Matrix {

	public static int[][] multiply(int a[][], int b[][]) {
		   
		  int aRows = a.length,
		      aColumns = a[0].length,
		      bRows = b.length,
		      bColumns = b[0].length;
		   
		  if ( aColumns != bRows ) {
		    throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
		  }
		   
		  int[][] resultant = new int[aRows][bColumns];
		   
		  for(int i = 0; i < aRows; i++) { // aRow
		    for(int j = 0; j < bColumns; j++) { // bColumn
		      for(int k = 0; k < aColumns; k++) { // aColumn
		        resultant[i][j] += a[i][k] * b[k][j];
		      }
		    } 
		  }
		   
		  return resultant;
		}
	
	public static void main(String[] args) {

	}

}
