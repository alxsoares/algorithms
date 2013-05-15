package alex.algorithms.math.projecteuler.level2;

import java.util.BitSet;
import java.util.Set;
import java.util.TreeSet;

public class Euler041 {
    public static Set<Integer> sieve(final int n) {
        Set<Integer> primes = new TreeSet<>();
        BitSet bprimes = new BitSet(n + 1);
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (!bprimes.get(i)) {
                for (int j = 2 * i; j <= n; j += i) {
                    bprimes.set(j);
                }
            }

        }
        for (int i = 2; i <= n; i++) {
            if (!bprimes.get(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    static boolean isPandigital(long n) {
        int digits = 0;
        int count = 0;
        int tmp;

        while (n > 0) {
            tmp = digits;
            digits = digits | 1 << (int) ((n % 10) - 1);// 0-base index
            if (tmp == digits) {// verify if the same bit was set
                return false;
            }

            count++;
            n /= 10;
        }

        return digits == (1 << count) - 1;
    }

    public static void main(final String[] args) {
        Set<Integer> sieve = sieve(7654321);
        int max = Integer.MIN_VALUE;
        for (Integer prime : sieve) {
            if (isPandigital(prime) && prime > max) {
                max = prime;
            }
        }
        System.out.printf("%d\n", max);
    }
}
