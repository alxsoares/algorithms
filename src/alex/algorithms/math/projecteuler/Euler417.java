package alex.algorithms.math.projecteuler;

public class Euler417 {

	static int S = 100000000;
	static boolean b[] = new boolean[S];
	static int[] tot = new int[S];
	static int[] pf = new int[S];

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

	static long lenCycle(long n) {
		while (n % 2 == 0)
			n /= 2;
		while (n % 5 == 0)
			n /= 5;
		if (n == 1)
			return 0;
		long len = tot[(int) n];
		long tmp = len;
		while (tmp > 1) {
			long p = pf[(int) tmp];
			if (modpow(10, len / p, n) == 1)
				len /= p;
			tmp /= p;
		}
		return len;
	}

	public static void main(String[] args) {
		for (int i = 0; i < S; i++)
			tot[i] = i;
		for (int i = 2, ten = 1; i < S; i++)
			if (!b[i]) {
				if (i > ten) {
					ten *= 10;
				}
				for (int j = i; j < S; j += i) {
					tot[j] = tot[j] / i * (i - 1);
					b[(int) j] = true;
					pf[j] = i;
				}
			}
		long sum = 0;
		for (int i = S - 1; i >= 3; i--) {
			if (i % 1000000 == 0)
				System.out.println(i / 1000000);
			sum += lenCycle(i);
		}
		System.out.println(sum);
	}

}
