package alex.algorithms.math.projecteuler;

public class Euler432 {

	static long M = 100000000000L;
	static int K = 510510;
	static int MOD = 1000000000;
	static int N = 50000000;
	static int p510510[] = { 2, 3, 5, 7, 11, 13, 17 };
	static int a[] = new int[N];
	static int phi[] = new int[N];
	static int sphi[] = new int[N];
	static long f[] = new long[(int) (M / N + 1)];

	static long F(long k) {
		if (k <= M / N)
			return f[(int) k];
		return sphi[(int) (M / k)];
	}

	static long result = 0;

	static void recursion(long prime, int i) {
		if (i == 7) {
			result = (result + F(prime)) % MOD;
			return;
		}
		for (; prime <= M; prime *= p510510[i])
			recursion(prime, i + 1);
	}

	public static void main(String[] args) {
		for (int i = 1; i < N; i++)
			phi[i] = i;
		for (int i = 2; i < N; i++)
			if (a[i] == 0)
				for (int j = i; j < N; j += i) {
					a[j] = 1;
					phi[j] = phi[j] / i * (i - 1);
				}
		for (int i = 1; i < N; i++) {
			sphi[i] = (sphi[i - 1] + phi[i]) % MOD;
		}
		for (long k = (M / N); k >= 1; k--) {
			long n = M / k;
			long a[] = { n, n + 1 };
			if (a[0] % 2 > 0)
				a[1] /= 2;
			else
				a[0] /= 2;
			a[0] %= MOD;
			a[1] %= MOD;
			long res = (a[0] * a[1]) % MOD;
			int v = (int) Math.sqrt(n);
			for (long j = 2; j <= n / (v + 1); j++) {
				res -= F(j * k);
				if (res < 0)
					res += MOD;
			}
			for (long x = 1; x <= v; x++) {
				res -= (n / x - n / (x + 1)) % MOD * sphi[(int) x] % MOD;
				if (res < 0)
					res += MOD;
			}
			f[(int) k] = res;
		}
		result = 0;
		recursion(1, 0);
		for (int i = 0; i < 7; i++)
			result = result * (p510510[i] - 1) % MOD;
		System.out.println(result);
	}

}
