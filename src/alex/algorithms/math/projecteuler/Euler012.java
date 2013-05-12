package alex.algorithms.math.projecteuler;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Euler012 {

	public static boolean isPrime(int n) {
		if (n == 2)
			return true;
		for (int i = 2; i * i < n; i++) {
			if (n % i == 0)
				return false;
		}

		return true;
	}

	public static long nthTriangle(long n) {
		long sum = ((1 + n) * n) / 2;
		return sum;
	}

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

	public static Integer[] sieve(int n) {
		BitSet e = new BitSet(n);
		for (int i = 2; i * i < n; i++) {
			if (!e.get(i)) {
				for (int j = 2 * i; j < n; j += i) {
					e.set(j);
					if (j + i < 0)
						break;
				}
			}
			if ((i + 1) * (i + 1) <= 0)
				break;
		}
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 2; i < e.length(); i++) {
			if (!e.get(i)) {
				a.add(i);
			}
		}

		return a.toArray(new Integer[0]);
	}

	public static int factors(long n, Integer[] sieve, Set<Integer> set) {
		int counter = 0;
		ArrayList<Integer> primeFactors = new ArrayList<Integer>();
		for (int i = 0; sieve[i] * sieve[i] <= n; i++) {
			long ncopy = n;
			int p = sieve[i];
			while (ncopy > 1 && ncopy % sieve[i] == 0) {
				primeFactors.add(p);
				ncopy=ncopy/sieve[i];
				p=p*sieve[i];
			}
			// int k = 1;
			// for (int j = sieve[i]; n % sieve[i] == 0 && j < n; j += sieve[i],
			// k++) {
			// if ((j == sieve[i] || !set.contains(k)) && (n % j == 0)) {
			// counter++;
			// }
			// }
			if (sieve[i + 1] * sieve[i + 1] < 0)
				break;
		}
		HashSet<Long> divs = new HashSet<Long>();
		int times = (1 << primeFactors.size());
		for (int i = 0; i < times; i++) {
			ArrayList<Integer> s = new ArrayList<>();
			int k = i;
			int index = 0;
			while (k > 0) {
				if ((k & 1) > 0) {
					s.add(primeFactors.get(index));
				}
				k = k >> 1;
				index++;
			}
			long prod = 1;
			for (Iterator<Integer> iterator = s.iterator(); iterator.hasNext();) {
				prod *= iterator.next();

			}
			if (prod <= n && prod > 1&& n%prod==0 && !divs.contains(prod)) {
				counter++;
				divs.add(prod);
			}
		}
		if (!divs.contains(n))
			counter++;
		return counter + 1;
	}

	public static void main(String[] args) {
		Integer[] sieve = sieve(99999999);
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < sieve.length; i++) {
			set.add(sieve[i]);
		}
		System.out.println(factors(nthTriangle(1728), sieve, set));
		// System.out.println(factors(nthTriangle(41040)));// 842161320
		for (long i = 7;; i++) {
			long nthTriangle = nthTriangle(i);
			int factors = factors(nthTriangle, sieve, set);
			System.out.printf("Factors %d\n", factors);
			if (factors >= 500) {
				System.out.printf("%d %d\n", nthTriangle, i);
				return;
			}
		}
//		int n = 1728;// 1493856 1728
//		long triang = 1493856;
//		int c = 0;
//		for (long i = 1; i <= triang; i++) {
//			if (triang % i == 0) {
//				c++;
//			}
//		}
//		System.out.printf("%d\n", c);
	}

}
