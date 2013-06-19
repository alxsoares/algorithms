package alex.algorithms.math.projecteuler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

	public static long lcm(final long a, final long b) {
		return a * b / gcd(a, b);
	}

	static long fi(long m, long n) {
		long d = 1;
		if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0 || n % 11 == 0
				|| n % 13 == 0 || n % 17 == 0) {
			d = gcd(m, n);
			return 92160 * fiEO(n) * d / fiEO(d);
		}
		return 92160 * fiEO(n);

	}

	// 510510=2*3*5*7*11*13*17
	static long cache[] = new long[99999999];

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

	static Set<Integer> sieve = Eratosthenes.sieve((int) Math.sqrt(10e11));
	
	static long fiEO(long m) {
		if (m < cache.length && cache[(int) m] != -1)
			return cache[(int) m];
		if (m % 4 == 0) {
			long result = 2 * fiEO(m / 2);
			if (m < cache.length)
				cache[(int) m] = result;
			return result;
		}
		if (m % 2 == 0 && m % 4 != 0) {

			long result = fiEO(m / 2);
			if (m < cache.length)
				cache[(int) m] = result;
			return result;
		}		
		long result = fi(m);
		if (m < cache.length)
			cache[(int) m] = result;
		return result;
	}

	public static long sqrt(long x) {
		if (x < 0)
			throw new IllegalArgumentException("Square root of negative number");
		long y = 0;
		for (long i = 1L << 31; i != 0; i >>>= 1) {
			y |= i;
			if (y > 3037000499L || y * y > x)
				y ^= i;
		}
		return y;
	}

	public static long fi(long n) {
		if (n <= 0)
			throw new IllegalArgumentException(
					"Totient of non-positive integer");
		int p = 1;
		for (int j = 0, end = (int)sqrt(n);j<primes.length&& primes[j] <= end; j++) { // Trial
															// division
			int i = primes[j];
			if (n % i == 0) { // Found a factor
				p *= i - 1;
				n /= i;
				while (n % i == 0) {
					p *= i;
					n /= i;
				}
				end = (int)sqrt(n);
				if(cache[end]!=-1){
					return p*cache[end];
				}
			}
		}
		if (n != 1)
			p *= n - 1;
		return p;
	}

	public static void main(String[] args) {
		Arrays.fill(cache, -1);
		 listTotients();
		// System.out.println(fi(1000000000));400000000
		long result = 0;
		long mod = 1000000000;
		long start = System.currentTimeMillis();
//		HashSet<Long> set = new HashSet<>();
		for (long i = 1; i <= 100000000000L; i++) {
			if (i % 1000000 == 0)
				System.out.println(i);
			long fi = fi(510510, i);
//			if (set.contains(fi)) {
//				// if (i % 2 == 0 || i % 5 == 0)
//				System.out.println(fi + "->" + i);
//			} else {
//				set.add(fi);
//			}
			result = ((result % mod) + fi % mod) % mod;
		}
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(result);
		// long sum = sumPhi(1000000);
		// sys
	}

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

	static long phi(long n) {
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
