package alex.algorithms.math.projecteuler.level5;

public class Euler115 {

	static long[] memo ;
	
	private static long fillCount(int m, int n) {

		long solutions = 1;

		if (n > m)
			return solutions;

		if (memo[m] != 0)
			return memo[m];

		for (int startpos = 0; startpos <= m - n; startpos++) {
			for (int blocklength = n; blocklength + startpos<= m; blocklength++) {
				solutions += fillCount(m - startpos - blocklength - 1, n);
			}
		}

		memo[m] = solutions;
		return solutions;
	}
	public static void main(String[] args) {
		long solutions = 0;            
        int n = 50;
        int m = n-1;
        
        while (solutions < 1000000) {
            m++;
            memo = new long[m + 1];
            solutions = fillCount(m, n);     
        }      
        System.out.printf("%d %d\n", solutions,m);
	}

}
