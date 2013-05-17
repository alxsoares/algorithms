package alex.algorithms.math.projecteuler.level2;

public class Euler045 {
	// x = n*(3n^2-1)/2 =>3n^2 -n -2x = 0=> n =(1+sqrt(1+24*x))/6 =>n in Integer
	static boolean isPentagonal(long number) {
		double penTest = (Math.sqrt(1 + 24 * number) + 1.0) / 6.0;
		return penTest == ((int) penTest);
	}

	// T(2m-1) =(2m-1)*(2m-1+1)/2 = m(2m-1)=Hm
	public static void main(String[] args) {
		for (int j = 144;; j++) {
			long pent = j * (2 * j - 1);
			if (isPentagonal(pent)) {
				System.out.printf("%d\n", pent);
				break;
			}
		}
	}

}
