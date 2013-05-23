package alex.algorithms.math.projecteuler.level4;

import java.util.HashSet;
import java.util.Set;

import alex.algorithms.math.projecteuler.Eratosthenes;

public class Euler087 {
	static Set<Integer> nbrs = new HashSet<Integer>();
	static Set<Integer> primes = Eratosthenes.sieve(7100);
	static final int LIMIT = (int) 50e6;

	public static void main(String[] args) {
		for (int a : primes) {
			int sq = a * a;
			if (sq >= LIMIT)
				break;
			for (int b : primes) {
				int cb = b * b * b;
				if (sq + cb >= LIMIT)
					break;
				for (int c : primes) {
					int fp = c * c * c * c;
					if (sq + cb + fp >= LIMIT)
						break;
					nbrs.add(sq + cb + fp);
				}
			}
		}
		System.out.println(nbrs.size());

	}

}
