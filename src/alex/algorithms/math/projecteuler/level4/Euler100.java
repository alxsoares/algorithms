package alex.algorithms.math.projecteuler.level4;

/**
 * 
 * http://www.alpertron.com.ar/QUAD.HTM
 * 
 */
public class Euler100 {
	/**
	 * 
	 * b_{k+1} = 3b_k + 2n_k -2
	 * 
	 * n_{k+1} = 4b_k + 3n_k -3
	 */
	public static void main(String[] args) {
		long b = 15;
		long n = 21;
		long target = 1000000000000L;
		 
		while(n < target){
		    long btemp = 3 * b + 2 * n - 2;
		    long ntemp = 4 * b + 3 * n - 3;
		 
		    b = btemp;
		    n = ntemp;
		}
		System.out.printf("%d\n",b);
	}

}
