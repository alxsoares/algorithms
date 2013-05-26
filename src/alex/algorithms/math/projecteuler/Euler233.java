package alex.algorithms.math.projecteuler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.LimitExceededException;
import javax.print.attribute.SetOfIntegerSyntax;

public class Euler233 {
	static long pointsInside(long R, ArrayList<Long> d1, ArrayList<Long> d3) {
		int sum = 0;
		long D = R * R;
		for (int x = 0; x <= R; x++) {
			for (int y = 0; y <= R; y++) {
				long distQuadratic = x * x + y * y;
				if (distQuadratic == D) {
					sum++;
				}
			}
		}
		return 4 * sum - 4;
	}

	static long schinzel(long r) {
		int k = 0;
		int d1 = 0;
		int d3 = 0;
		long n = r * r;
		while (4 * k + 1 <= n || 4 * k + 3 <= n) {
			if (n % (4 * k + 1) == 0) {
				d1++;
			}
			if (n % (4 * k + 3) == 0) {
				d3++;
			}
			k++;
		}
		return 4 * (d1 - d3);
	}

	static long schinzel2(long r) {
		int k = 0;
		int d1 = 0;
		int d3 = 0;
		long n = r * r;
		Integer[] sieve = Eratosthenes.sieve(2, 1000000000);
		for (int i = 0; i < sieve.length; i++) {
			Integer p = sieve[i];
			if (p % 4 == 1 && n % p == 0) {
				d1++;
				for (long pp = 2 * p; pp <= n; pp += p) {
					if (pp % 4 == 1 && n % pp == 0)
						d1++;
				}
			}
			if (p % 4 == 3 && n % p == 0) {
				d3++;
				for (long pp = 2 * p; pp <= n; pp += p) {
					if (pp % 4 == 3 && n % pp == 0)
						d3++;
				}
			}
		}
		return 4 * (d1 - d3);

	}

	public static int points(long r) {
		int sum = 0;
		Set<Long> X = new TreeSet<>();
		long limitY = (long) Math.ceil((r / Math.sqrt(2)));
		for (int y = 0; y <= limitY; y++) {
			long xx = r * r - y * y;
			long x = (long) Math.sqrt(xx);
			if (!X.contains(xx) && x * x == xx) {
				sum++;
				X.add(xx);
			}
		}
		return 8 * sum - 4;
	}

	public static int test(int num, Integer[] sieve) {
		TreeSet<Integer> div = new TreeSet<Integer>();
		long lmit = (long) Math.sqrt(1e11);
		for (int i = 0; i < sieve.length && sieve[i] <= num; i++) {
			int p = sieve[i];
			while (p <= num) {
				if (num % p == 0) {
					div.add(p);
					div.add(num / p);
				}
				p += sieve[i];
			}
		}
		div.add(1);
		div.add(num);
		int d1 = 0;
		int d3 = 0;
		for (Iterator<Integer> iterator = div.iterator(); iterator.hasNext();) {
			Integer d = iterator.next();
			System.out.printf("%d ", d);
			if (d % 4 == 1) {
				d1++;
			}
			if (d % 4 == 3) {
				d3++;
			}
		}
		return 4 * (d1 - d3);
	}

	public static int Test(long num, Integer[] sieve) {
		int prod = 1;
		for (int i = 0; i < sieve.length; i++) {
			int p = sieve[i];
			if (num % p == 0 && p % 4 == 3) {
				int div = 0;
				while (num % p == 0) {
					div++;
					p *= sieve[i];
				}
				if (div % 2 == 1)
					return 0;
			}
			if (num % p == 0 && p % 4 == 1) {
				int div = 0;
				while (num % p == 0) {
					div++;
					p *= sieve[i];
				}
				prod *= (div + 1);
			}
		}
		return 4 * prod;
	}

	public static boolean isPrime(long n) {
		if (n < 2)
			return false;
		long sqrt = (long) Math.sqrt(n);
		for (long i = 2; i <= sqrt; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static List<Long> primes(long upperBound) {
		List<Long> primes = new ArrayList<Long>();
		long k = 0;
		while (4 * k + 1 <= upperBound || 4 * k + 3 <= upperBound) {
			long d1 = 4 * k + 1;
			long d3 = 4 * k + 3;
			if (isPrime(d1)) {
				primes.add(d1);
			}
			if (isPrime(d3)) {
				primes.add(d3);
			}
		}
		return primes;
	}

	public static long Test2(long num) {
		long prod = 1;
		long sqrt = (long) Math.sqrt(num);
		for (long i = 0; i <= sqrt; i++) {
			long p1 = 4*i+1;
			long p3 = 4*i+3;
			long p = p3;
			if (num % p == 0 && p % 4 == 3 && isPrime(p)) {
				int div = 0;
				while (num % p == 0) {
					div++;
					p *= p3;
				}
				if (div % 2 == 1)
					return 0;
			}
			p=p1;
			if (num % p == 0 && p % 4 == 1 && isPrime(p)) {
				int div = 0;
				while (num % p == 0) {
					div++;
					p *= p1;
				}
				prod *= (div + 1);
			}
			if(p1 > sqrt && p3> sqrt) break;
		}
		return 4 * prod;
	}

	public static void main(String[] args) {
		long LIMIT = (long) Math.sqrt(1e22);
//		System.out.println(LIMIT);
//		System.out.println((long) 1e22);
//		Integer[] sieve = sieve(2, LIMIT);
		// System.out.println(test(10000, sieve));
//		System.out.println(Test(100000000, sieve));
		for (int i = 10; i <= LIMIT; i++) {
			long test = Test2(i * i);
			if (test ==420) {
				System.out.printf("%d => %d\n", i, test);
			}
		}
		System.out.println(Test2(10000*10000));
//		System.out.println(primes.size());
	}

	public static Integer[] sieve(int lowerLimit, int upperLimit) {

		List<Integer> numbers = new ArrayList<Integer>(
				(int) (upperLimit / (Math.log(upperLimit) - 1.08366)));

		BitSet bprimes = new BitSet(upperLimit + 1);
		int sqrt = upperLimit;
		for (int i = 2; i <= sqrt; i++) {
			if (!bprimes.get(i)) {
				for (int j = 2 * i; j <= upperLimit; j += i) {
					bprimes.set(j);
				}
			}

		}
		for (int i = lowerLimit; i <= upperLimit; i++) {
			if (!bprimes.get(i) && (i % 4 == 1 || i % 4 == 3)) {
				numbers.add(i);
			}
		}
		return numbers.toArray(new Integer[0]);
	}

}
