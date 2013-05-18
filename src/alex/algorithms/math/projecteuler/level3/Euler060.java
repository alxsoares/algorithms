package alex.algorithms.math.projecteuler.level3;

import java.util.HashSet;

import alex.algorithms.math.projecteuler.ErastotenesSieve;

public class Euler060 {
	static Integer[] primes = ErastotenesSieve.sieve(30000).toArray(
			new Integer[0]);
	static int concat(int a, int b) {
        int c = b;
        while (c > 0) {
            a *= 10;
            c /= 10;
        }

        return a + b;
    }

	static HashSet<Integer> MakePairs(int a) {
        HashSet<Integer> pairs = new HashSet<Integer>();
        for (int b = a + 1; b < primes.length; b++) {
            if (isPrime(concat(primes[a], primes[b])) &&
                isPrime(concat(primes[b], primes[a])))
                pairs.add(primes[b]);
        }
        return pairs;
    }


	static boolean isPrime(long n) {
		long upperBound = (long) Math.sqrt(n);
		for (int i = 2; i <= upperBound; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int result = Integer.MAX_VALUE;
		 HashSet<Integer>[] pairs = new HashSet[primes.length];

         for (int a = 1; a < primes.length; a++) {
             if (primes[a] * 5 >= result) break;
             if (pairs[a] == null) pairs[a] = MakePairs(a);
             for (int b = a + 1; b < primes.length; b++) {
                 if (primes[a] + primes[b] * 4 >= result) break;
                 if (!pairs[a].contains(primes[b])) continue;
                 if (pairs[b] == null) pairs[b] = MakePairs(b);

                 for (int c = b + 1; c < primes.length; c++) {
                     if (primes[a] + primes[b] + primes[c] * 3 >= result) break;
                     if (!pairs[a].contains(primes[c]) ||
                         !pairs[b].contains(primes[c])) continue;
                     if (pairs[c] == null) pairs[c] = MakePairs(c);

                     for (int d = c + 1; d < primes.length; d++) {
                         if (primes[a] + primes[b] + primes[c] + primes[d] * 2 >= result) break;
                         if (!pairs[a].contains(primes[d]) ||
                             !pairs[b].contains(primes[d]) ||
                             !pairs[c].contains(primes[d])) continue;
                         if (pairs[d] == null) pairs[d] = MakePairs(d);

                         for (int e = d + 1; e < primes.length; e++) {
                             if (primes[a] + primes[b] + primes[c] + primes[d] + primes[e] >= result) break;
                             if (!pairs[a].contains(primes[e]) ||
                                 !pairs[b].contains(primes[e]) ||
                                 !pairs[c].contains(primes[e]) ||
                                 !pairs[d].contains(primes[e])) continue;

                             if (result > primes[a] + primes[b] + primes[c] + primes[d] + primes[e])
                                 result = primes[a] + primes[b] + primes[c] + primes[d] + primes[e];

                             break;
                         }
                     }
                 }
             }
         }
         System.out.printf("%d\n", result);
     }
     


}
