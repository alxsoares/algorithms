package alex.algorithms.math.projecteuler;

public class Euler430 {
	private static long N = 10000000000L;
	private static int M = 4000;

	public static void main(String[] args) {

		double exp = 0;
		long L = 15000000;
		for (long i = 1; i <= L; i++) {
			double p = 2. * i / (double) N - 2. * i / (double) N * i
					/ (double) N + (2. * i - 1.) / ((double) N*N );// (double) N;
			exp += prob2(p);
		}
		exp += N / 2 - L;
		System.out.printf("%.2f\n", exp);
	}
	
	private static double prob2(double p) {
		return 1 + Math.pow(1 - 2 * p, M);
	}

}
