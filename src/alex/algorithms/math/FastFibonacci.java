package alex.algorithms.math;

public class FastFibonacci {
	public static int[][] multiply(int A[][], int B[][], int n, int m){
		int res[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int sum=0;
				for(int k=0; k < B.length;k++){
					sum+=A[i][k]*B[k][j];
				}
				res[i][j] = sum;
			}
		}
		return res;
	}
	public static int [][] pow (int a[][], int n){
		if(n==1) return a;
		int B[][] = pow(a,n/2);
		if(n%2 ==0){
			return multiply(B, B, a.length, a.length);
		}else{
			return multiply(a,multiply(B, B, a.length, a.length),a.length,a.length);
		}
	}
	public static long fastFib(int nfib){
		int[][] base= {{1,1},{1,0}};
		int f[][] = pow(base,nfib);
		return f[0][1];
	}
	public static void main(String[] args) {
		System.out.println(fastFib(1));
		System.out.println(fastFib(2));
		System.out.println(fastFib(3));
		System.out.println(fastFib(4));
		System.out.println(fastFib(5));
		System.out.println(fastFib(6));
		System.out.println(fastFib(7));
	}

}
