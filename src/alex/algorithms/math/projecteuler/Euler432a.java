package alex.algorithms.math.projecteuler;

public class Euler432a {

	public static boolean IsProbablePrime(long n, int[] ar) {

		for (int i = 0; i < ar.length; i++) {
			if (Witness(ar[i], n))
				return false;
		}
		return true;
	}

	static boolean Witness(int a, long n) {
		int t = 0;
		long u = n - 1;
		while ((u & 1) == 0) {
			t++;
			u >>= 1;
		}

		long xi1 = modpow(a, u, n);
		long xi2;

		for (int i = 0; i < t; i++) {
			xi2 = xi1 * xi1 % n;
			if ((xi2 == 1) && (xi1 != 1) && (xi1 != (n - 1)))
				return true;
			xi1 = xi2;
		}
		if (xi1 != 1)
			return true;
		return false;
	}

	static long modpow(long b, long e, long m) {
		long r = 1;
		while (e > 0) {
			if ((e & 1) != 0)
				r = r * b % m;
			{
				b = b * b % m;
				e >>= 1;
			}
		}
		return r;
	}

	private static final long LimitA = 1373653;
	private static final long LimitB = 9080191;
	private static final long LimitC = 4759123141L;
	private static final long LimitD = 2152302898747L;
	private static final long LimitE = 3474749660383L;

	public static boolean IsProbablePrime(long n) {
		if (n < LimitA)
			return IsProbablePrime(n, new int[] { 2, 3 });
		if (n < LimitB)
			return IsProbablePrime(n, new int[] { 31, 73 });
		if (n < LimitC)
			return IsProbablePrime(n, new int[] { 2, 7, 61 });
		if (n < LimitD)
			return IsProbablePrime(n, new int[] { 2, 3, 5, 7, 11 });
		if (n < LimitE)
			return IsProbablePrime(n, new int[] { 2, 3, 5, 7, 11, 13 });

		return IsProbablePrime(n, new int[] { 2, 3, 5, 7, 11, 13, 17 });

	}

	static Integer[] primes = Eratosthenes.sieve(2, (int) Math.sqrt(10e11));

	public static long phi(long n) {
		if (IsProbablePrime(n)) {
			return (n - 1);
		}
		long phiNom = 1;
		long phiDenom = 1;
		for (int i = 0; i < primes.length; i++) {
			int p = primes[i];
			if (p > n)
				break;
			if (n % p == 0) {
				phiNom *= (p - 1);
				phiDenom *= p;
			}
		}
		return (n * phiNom) / phiDenom;
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

	public static long phi(int m, int n) {
		if (n == 1)
			return 92160;
		if (IsProbablePrime(n)) {
			return 92160 * (n - 1);
		}
		long d = gcd(m, n);
		return 92160 * phi(n) * d / phi(d);
	}

	public static void factor(int n) {
		int ncopy = n;
		for (int i = 2; i * i <= ncopy; i++) {
			if (n % i == 0) {
				int exp = 0;
				while (n % i == 0) {
					exp++;
					n = n / i;
				}
				System.out.printf("%d %d\n", i, exp);
			}
		}
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
	static long cache[] = new long[9999999];

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

	static long fiEO(long m) {
		if (m < cache.length && cache[(int) m] != 0)
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
		long result = phiTot(m);
		if (m < cache.length)
			cache[(int) m] = result;
		return result;
	}

	public static long totient(long n) {
		if (n == 1)
			return 1;
		long tot = n;
		for (int i = 0; i < primes.length; i++) {
			int p = primes[i];
			if (p > n)
				break;
			if (n % p == 0) {
				tot = tot * (p - 1) / p;
			}

		}
		if (tot == n) {
			tot = n - 1;
		}
		return tot;
	}

	public static int mobius(long n) {
		if (n == 1)
			return 1;
		int distinct = 0;
		for (int i = 0; i < primes.length; i++) {
			int p = primes[i];
			if (p > n)
				break;
			int exp = 0;
			long nn = n;
			if (nn % p == 0)
				distinct++;
			while (nn % p == 0) {
				exp++;
				if (exp > 1)
					return 0;
				nn = nn / p;
			}
		}
		if (distinct % 2 == 0)
			return 1;
		return -1;
	}
	public static long phiSum(long n){
		long sum=0;
		for(long i=0;i*i<=n;i++){
			if(n%i==0){
				sum+=totient(i)*(n/i)*(1+(n/i));
				long d = n/i;
				sum+=totient(d)*(n/d)*(1+(n/d));
			}
		}
		return sum;
	}
	public static long phiTot(long n){
		
		double mi=0;
		for(long i=1;i*i<=n;i++){
			if(n%i==0){
				mi +=(double)mobius(i)/i;
				mi+=(double) mobius(n/i)/(n/i);
			}
		}
		return (long) (mi*n);
	}
	public static void main(String[] args) {
		// System.out.println(IsProbablePrime(17));
		// System.out.println(IsProbablePrime(19));
		// System.out.println(IsProbablePrime(16));
		// System.out.println(IsProbablePrime(101));
		// System.out.println(IsProbablePrime(1001));
		// System.out.println(phi(510510));

		System.out.println(phiTot(510510));
		// long sum=0;
		// for(int i=1;i<=1000000;i++){
		// System.out.println(i);
		// sum+=phi(510510, i);
		// }
		// System.out.printf("%d\n", sum);
		long result = 0;
		long mod = 1000000000;
		long start = System.currentTimeMillis();
		// listTotients();
		for (long i = 1; i <= 1000000L; i++) {
			if (i % 100000 == 0)
				System.out.println(i);
			result = ((result % mod) + fi(510510, i) % mod) % mod;
		}
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(result);
	}

}
