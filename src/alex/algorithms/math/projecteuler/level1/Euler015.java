package alex.algorithms.math.projecteuler.level1;

public class Euler015 {

	public static long binomialCoefficientR(int n, int k) {
		if (n == k || k == 0)
			return 1;
		return binomialCoefficientR(n - 1, k - 1)
				+ binomialCoefficientR(n - 1, k);
	}

	public static long binomialCoefficient(int n, int k) {
		long[][] C = new long[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= k && j<=i; j++) {
				// Base
				if (j == 0 || j == i) {
					C[i][j] = 1;
				} else {
					C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
				}
			}
		}
		return C[n][k];
	}
	public static long binomialCoefficientOptimised(int n, int k){
		long C[] = new long[k+1];
		C[0] = 1;
		for(int i=1; i<=n;i++){
			for(int j=Math.min(i, k); j>0;j--){
				C[j] = C[j] + C[j-1];
			}
		}
		return C[k];
	}
	
	public static long binomialCoefficientOptimisedHuge(int n, int k){
		long C[] = new long[k+1];
		C[0] = 1;
		int counter =0;
		for(int i=1; i<=n;i++){
			for(int j=Math.min(i, k); j>0;j--){
				C[j] = C[j] + C[j-1];
				if(C[j]%10==0) counter++;
			}
		}
		return counter;
	}

	public static void main(String[] args) {
		System.out.printf("%d", binomialCoefficientOptimised(40, 20));
	}

}
