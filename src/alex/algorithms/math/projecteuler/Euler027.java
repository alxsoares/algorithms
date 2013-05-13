package alex.algorithms.math.projecteuler;

public class Euler027 {
    public static int[] primes() {
        boolean[] sieve = new boolean[2000001];
        int[] primes = new int[1000];
        sieve[2] = false;
        int k = 0;
        for (int i = 2; k < 1000; i++) {
            if (!sieve[i]) {// isprime
                primes[k++] = i;
            }
            for (int j = 2 * i; j <= 2000000; j += i) {
                sieve[j] = true;
            }
        }
        return primes;
    }

    public static boolean isPrime(final long n) {
        long root = (long) Math.sqrt(n);
        for (long i = 2; i <= root; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(final String[] args) {
        int max = Integer.MIN_VALUE;
        long aa = 0;
        long bb = 0;
        for (long a = -999; a <= 999; a++) {
            for (long b = -999; b <= 999; b++) {
                int n = 0;
                long prime = n * n + a * n + b;
                while (prime > 1 && isPrime(prime)) {
                    n++;
                    if (n > max) {
                        max = n;
                        aa = a;
                        bb = b;
                    }
                    prime = n * n + a * n + b;
                }
            }
        }
        System.out.printf("%d %d %d %d\n", max, aa, bb, aa * bb);

    }
}
