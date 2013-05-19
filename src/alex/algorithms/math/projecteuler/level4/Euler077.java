package alex.algorithms.math.projecteuler.level4;

import alex.algorithms.math.projecteuler.Eratosthenes;

public class Euler077 {

	public static void main(String[] args) {
		Integer[] numbers = Eratosthenes.sieve(2, 1000);
		for (int n = 2;; n++) {
			int[] nway = new int[n+1];
			nway[0] = 1;
			for (int i = 0; i < numbers.length; i++) {
				int c = numbers[i];
				for (int j = c; j <= n; j++)
					nway[j] += nway[j - c];
			}
			if(nway[n] > 5000){
				System.out.printf("%d %d\n",n, nway[n]);
				break;
			}
		}
	}

}
