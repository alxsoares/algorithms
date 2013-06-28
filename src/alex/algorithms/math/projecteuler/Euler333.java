package alex.algorithms.math.projecteuler;

import java.util.Set;

public class Euler333 {
	private static final int LIMIT = 1000000;
	static int powers[][] = new int[21][14];
	static int count[] = new int[LIMIT];
	static Integer[] sieve = Eratosthenes.sieve(2,LIMIT);

	public static void main(String[] args) {
		int p2 = 1;
		for (int e2 = 0; e2 < 21; e2++,p2*=2) {
			int p3 = 1;
			for (int e3 = 0; e3 < 14; e3++,p3*=3) {
				powers[e2][e3] = p2 * p3;

			}
		}
		for (int e3 = 0; e3 < 14; e3++) {
			count(0, e3, powers[0][e3]);
		}
		int sum=0;
		for (int i = 0; i < sieve.length; i++) {
			Integer p = sieve[i];
			if(count[p]==1){
				sum+=p;
			}
		}
		System.out.println(sum);
	
	}

	private static void count(int e2, int e3, int value) {
		if (value <= 0 || value > LIMIT)
			return;
		count[value]++;
		for (int f3 = 0; f3 < e3; f3++) {
			for (int f2 = e2 + 1; value + powers[f2][f3] < LIMIT; f2++) {
				count(f2, f3, value + powers[f2][f3]);
			}

		}
	}
}
