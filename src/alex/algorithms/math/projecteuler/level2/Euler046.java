package alex.algorithms.math.projecteuler.level2;

import java.util.Set;

import alex.algorithms.math.projecteuler.ErastotenesSieve;

public class Euler046 {
	public static boolean isTwiceSquare(long n) {
		double s = Math.sqrt(n / 2);
		return s == ((int) s);
	}

	public static void main(String[] args) {
		Set<Integer> sieve = ErastotenesSieve.sieve(10000);
		for (long odd = 3;; odd += 2) {
			boolean notFound = true;
			for (Integer prime : sieve) {
				if (prime > odd)
					break;
				if (isTwiceSquare(odd - prime)) {
					notFound = false;
					break;
				}

			}
			if(notFound){
				System.out.printf("%d\n", odd);
				break;
			}
		}
	}

}
