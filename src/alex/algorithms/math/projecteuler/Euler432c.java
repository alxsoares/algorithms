package alex.algorithms.math.projecteuler;

public class Euler432c {
	public static void main(String[] args) {

		int n = 100000000;

		int[] phi = new int[n / 2 + 1];

		for (int i = 1; i <= n / 2; ++i)
			phi[i] = 1;

		for (int i = 2; i <= n / 2; ++i) {
			if (1 == phi[i]) {
				for (int j = i; j <= n / 2; j += i) {
					phi[j] *= i - 1;
				}
				long li = i;
				for (long p = li * li; p <= n / 2; p *= i) {
					for (int j = (int) p; j <= n / 2; j += p) {
						phi[j] *= i;
					}
				}
			}
		}

		long count = 0;
		for (int i = 1; i <= n / 2; ++i)
			count += (n / i - 1) * phi[i];
		System.out.println(phi[17]);

		System.out.println(count);
	}
}