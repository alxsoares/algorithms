package alex.algorithms.dynamicprogramming;

public class BinomialCoefficient {

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
//		System.out.printf("%d\n", binomialCoefficient(100, 11));
//		System.out.printf("%d\n", binomialCoefficientOptimised(100, 11));
//		System.out.printf("%d\n", binomialCoefficientOptimisedHuge(1000000000, 10000000-10));
		long sum = 0;
		for(int i=12;i<=25;i++){
			sum+=binomialCoefficient(25, i);
		}
		sum+=4*binomialCoefficient(15, 11);
		sum+=4*binomialCoefficient(20, 11);
		System.out.println(sum);
	}

}
