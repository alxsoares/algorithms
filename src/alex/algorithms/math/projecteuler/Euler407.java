package alex.algorithms.math.projecteuler;

import java.util.ArrayList;
import java.util.List;

public class Euler407 {
    private static final int LIMIT = (int) Math.pow(10, 7);

    public static void main(final String[] args) {
        int[] smallestPrimeFactor = new int[LIMIT + 1];
        for (int i = 2; i < smallestPrimeFactor.length; i++) {
            if (smallestPrimeFactor[i] == 0) {
                smallestPrimeFactor[i] = i;
                if ((long) i * i <= LIMIT) {
                    for (int j = i * i; j <= LIMIT; j += i) {
                        if (smallestPrimeFactor[j] == 0)
                            smallestPrimeFactor[j] = i;
                    }
                }
            }
        }

        // Maximum size of set of prime factors where the product of the set <=
        // LIMIT.
        // This is important because the number of solutions for n is 2^N,
        // where N is the number of distinct prime factors of n.
        int maxNumPrimeFactors = 0;
        for (int i = 2, prod = 1; i < smallestPrimeFactor.length; i++) {
            if (smallestPrimeFactor[i] == i) { // i is prime
                if (LIMIT / prod < i)
                    break;
                prod *= i;
                maxNumPrimeFactors++;
            }
        }

        long sum = 0;
        // Temporary arrays
        int[] solns = new int[1 << maxNumPrimeFactors];
        int[] newsolns = new int[1 << maxNumPrimeFactors];
        for (int i = 1; i <= LIMIT; i++) {
            // Compute factorization as coprime prime powers. e.g. 360 = {2^3,
            // 3^2, 5^1}
            List<Integer> factorization = new ArrayList<Integer>();
            for (int j = i; j != 1;) {
                int p = smallestPrimeFactor[j];
                int q = 1;
                do {
                    j /= p;
                    q *= p;
                } while (j % p == 0);
                factorization.add(q);
            }

            solns[0] = 0;
            int solnslen = 1;
            int modulus = 1;
            for (int q : factorization) {
                // Use Chinese remainder theorem; cache parts of it
                int recip = reciprocalMod(q % modulus, modulus);
                int newmod = q * modulus;

                int newsolnslen = 0;
                for (int j = 0; j < solnslen; j++) {
                    newsolns[newsolnslen++] = ((0 + (int) ((long) (solns[j] - 0 + modulus) * recip % modulus) * q) % newmod);
                    newsolns[newsolnslen++] = ((1 + (int) ((long) (solns[j] - 1 + modulus) * recip % modulus) * q) % newmod);
                }

                solnslen = newsolnslen;
                modulus = newmod;

                // Flip buffers
                int[] temp = solns;
                solns = newsolns;
                newsolns = temp;
            }

            int max = 0;
            for (int j = 0; j < solnslen; j++)
                max = Math.max(solns[j], max);
            sum += max;
        }
        System.out.println(sum);
    }

    public static int reciprocalMod(int x, final int m) {
        if (m < 0 || x < 0 || x >= m)
            throw new IllegalArgumentException();
        int y = x;
        x = m;
        int a = 0;
        int b = 1;
        while (y != 0) {
            int z = x % y;
            int c = a - x / y * b;
            x = y;
            y = z;
            a = b;
            b = c;
        }
        if (x == 1)
            return (a + m) % m;
        else
            throw new IllegalArgumentException("Reciprocal does not exist");
    }
}
