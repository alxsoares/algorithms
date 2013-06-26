package alex.algorithms.math.projecteuler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Euler432 {

    public static long gcd(final long u, final long v) {
        if (u == v)
            return u;
        if (u == 0)
            return v;
        if (v == 0)
            return u;
        if ((~u & 1) > 0) {// u é par
            if ((v & 1) > 0) {// v impar
                return gcd(u >> 1, v);// v impar e u par
            }
            return gcd(u >> 1, v >> 1) << 1;// ambos são pares
        }
        if ((~v & 1) > 0)
            return gcd(u, v >> 1);// v par e u impar
        if (u > v) {
            return gcd((u - v) >> 1, v);
        } else {
            return gcd(u, (v - u) >> 1);
        }
    }

    static long fi2(long m, long n) {

        long d = 1;
        boolean two = false, three = false, five = false, seven = false, eleven = false, thirteen = false, seventeen = false;
        while (n % 2 == 0) {
            n = n / 2;
            d *= 2;
            two = true;
        }
        while (n % 3 == 0) {
            n = n / 3;
            d *= 3;
            three = true;
        }
        while (n % 5 == 0) {
            n = n / 5;
            d *= 5;
            five = true;
        }
        while (n % 7 == 0) {
            n = n / 7;
            d *= 7;
            seven = true;
        }
        while (n % 11 == 0) {
            n = n / 11;
            d *= 11;
            eleven = true;
        }
        while (n % 13 == 0) {
            n = n / 13;
            d *= 13;
            thirteen = true;
        }
        while (n % 17 == 0) {
            n = n / 17;
            d *= 17;
            seventeen = true;
        }

        long num = 1;
        long den = 1;
        if (two) {
            m = m / 2;
            d = d * 2;
            num *= 1;
            den *= 2;
        }
        if (three) {
            m = m / 3;
            d = d * 3;
            num *= 2;
            den *= 3;
        }
        if (five) {
            m = m / 5;
            d = d * 5;
            num *= 4;
            den *= 5;
        }
        if (seven) {
            m = m / 7;
            d = d * 7;
            num *= 6;
            den *= 7;
        }
        if (eleven) {
            m = m / 11;
            d = d * 11;
            num *= 10;
            den *= 11;
        }
        if (thirteen) {
            m = m / 13;
            d = d * 13;
            num *= 12;
            den *= 13;
        }
        if (seventeen) {
            m = m / 17;
            d = d * 17;
            num *= 16;
            den *= 17;
        }
        return (fi(m) * fi(n) * d * num) / den;
    }

    // 821125120
    // 510510=2*3*5*7*11*13*17
    static int cache[] = new int[999999999];

    public static void listTotients() {
        for (int i = 0; i < cache.length; i++)
            cache[i] = i;

        for (int i = 2; i < cache.length; i++) {
            if (cache[i] == i) { // i is prime
                for (int j = i; j < cache.length; j += i)
                    cache[j] = cache[j] / i * (i - 1);
            }
        }
    }

    static Set<Integer> sieve = sieve((int) Math.sqrt(10e11));

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

    static long fiEO(final long m) {
        if (m < cache.length)
            return cache[(int) m];
        if (m % 4 == 0) {
            long result = 2 * fiEO(m / 2);
            return result;
        }
        if (m % 2 == 0 && m % 4 != 0) {

            long result = fiEO(m / 2);
            return result;
        }
        long result = fi(m);
        return result;
    }

    public static long fi(long n) {

        if (n < cache.length) {
            return cache[(int) n];
        }

        if (n <= 0)
            throw new IllegalArgumentException("Totient of non-positive integer");
        int p = 1;
        for (int j = 0, end = (int) Math.sqrt(n); j < primes.length && primes[j] <= end; j++) { // Trial
            // division
            int i = primes[j];
            if (n % i == 0) { // Found a factor
                p *= i - 1;
                n /= i;
                while (n % i == 0) {
                    p *= i;
                    n /= i;
                }
                end = (int) Math.sqrt(n);
                if (n < cache.length) {
                    return p * cache[(int) n];
                }
            }
        }
        if (n != 1)
            p *= n - 1;
        return p;
    }

    public static void main(final String[] args) {
        // System.out.println(primes.length);
        Arrays.fill(cache, -1);
        listTotients();
        // // System.out.println(fi(1000000000));400000000
        // long result = 0;
        int mod = 1000000000;
        // long start = System.currentTimeMillis();
        // for (long i = 1; i <= 100000000000L; i++) {
        // if (i % 1000000 == 0)
        // System.out.println(i);
        // long fi = fi2(510510, i);
        //
        // result = ((result % mod) + fi % mod) % mod;
        // }
        // System.out.println(System.currentTimeMillis() - start);
        // System.out.println(result);
        // // sys
        // // MOdificacao
        List<Future<Integer>> list = new ArrayList<Future<Integer>>();
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(100);
        int i = 0;
        long len = 100000000000L;
        long blockSize = (len / 100);
        while (true) {
            try {
                long s = (i * blockSize) + 1;
                long f = Math.min((i + 1) * blockSize, len);
                list.add(executor.submit(new Task(s, f)));
                if (f >= len) {
                    break;
                }
                i++;
            } catch (Throwable t) {
                System.out.println(t.getMessage());
            }
        }
        int r = 0;
        // Wait until all threads are finish
        // while (!executor.isTerminated()) {
        // }
        for (Future<Integer> future : list) {
            try {
                r = (r % mod + future.get() % mod) % mod;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        System.out.println("Result==>" + r);

    }

    private static final int NTHREDS = 50;

    static boolean isPrime(final long n) {
        if (n == 2 || n == 3)
            return true;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    static Integer[] primes = Eratosthenes.sieve(2, (int) Math.sqrt(10e11));

    static long phi(final long n) {
        if (n < cache.length) {
            return cache[(int) n];
        }
        // Base case
        if (n < 2)
            return fi(n);

        // Lehmer's conjecture
        if (isPrime(n))
            return n - 1;

        // Even number?
        if ((n & 1) == 0) {
            long m = n >> 1;
            return ((m & 1) != 1) ? phi(m) << 1 : phi(m);
        }

        // For all primes ...
        for (int i = 0; i < primes.length; i++) {
            int m = primes[i];
            if (n % m != 0)
                continue;

            // phi is multiplicative
            long o = n / m;
            long d = gcd(m, o);
            return d == 1 ? phi(m) * phi(o) : phi(m) * phi(o) * d / phi(d);
        }
        return fi(n);
    }

}

class Task implements Callable<Integer> {
    final int mod = 1000000000;
    final private long start;
    final private long end;

    Task(final long start, final long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        Integer result = 0;
        for (long i = start; i <= end; i++) {
            if (i % 1000000 == 0) {
                System.out.println(i);
            }
            long fi = Euler432.fi2(510510, i);
            result = (int) (((result % mod) + fi % mod) % mod);
        }
        return result;
    }

}

