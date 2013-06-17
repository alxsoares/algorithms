package alex.algorithms.math.projecteuler.level5;


public class Euler113 {
	 public static long choose(int n, int k) {
         k = Math.min(k, n - k);

         long res = 1;
         for (int i = 1; i <= k; i++) {
             res *= n - k + i;
             res /= i;
         }

         return res;
     }
	 
	public static void main(String[] args) {
		long res = choose(100 + 10, 10) + choose(100 +9, 9) - 10 * 100 - 2;
		System.out.println(res);
	}

}
