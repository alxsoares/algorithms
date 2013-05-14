package alex.algorithms.math.projecteuler.level2;

import java.util.Set;
import java.util.TreeSet;

public class Euler035 {
    public static Set<Integer> sieve(final int n) {
        Set<Integer> primes = new TreeSet<>();
        boolean[] bprimes = new boolean[n + 1];
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (!bprimes[i]) {
                for (int j = 2 * i; j <= n; j += i) {
                    bprimes[j] = true;
                }
            }

        }
        for (int i = 2; i <= n; i++) {
            if (!bprimes[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static int checkCircularPrimes(final int prime, final Set<Integer> primes) {
        int multiplier = 1;
        int number = prime;
        int count = 0;
        int d;

        while (number > 0) {
            d = number % 10;
            if (d % 2 == 0 || d == 5) {
                primes.remove(prime);
                return 0;
            }
            number /= 10;
            multiplier *= 10;
            count++;
        }
        multiplier /= 10;

        number = prime;
        Set<Integer> foundCircularPrimes = new TreeSet<Integer>();

        for (int i = 0; i < count; i++) {
            if (primes.contains(number)) {
                foundCircularPrimes.add(number);
                primes.remove(number);
            } else if (!foundCircularPrimes.contains(number)) {
                return 0;
            }

            d = number % 10;
            number = d * multiplier + number / 10;
        }

        return foundCircularPrimes.size();
    }

    public static void main(final String[] args) {
        Set<Integer> primes = sieve(1000000);
        int totalPrimes = primes.size();
        primes.remove(2);
        primes.remove(5);
        int numberOfCircularPrimes = 2;
        while (primes.size() > 0) {
            numberOfCircularPrimes += checkCircularPrimes(primes.iterator().next(), primes);
        }
        System.out.printf("%d %d\n", numberOfCircularPrimes, totalPrimes - numberOfCircularPrimes);

    }
}
