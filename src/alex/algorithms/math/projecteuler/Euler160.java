package alex.algorithms.math.projecteuler;

public class Euler160 {
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

	public static int last5(long f) {
		int mod = 3125;
		long num2s = 0;
		for (long h = f / 5; h > 0; h /= 5)
			num2s += h;

		int t = 1;

		int[] memo = new int[mod];
		for (int i = 0; i < mod; i++) {
			if (i % 5 != 0)
				t = (t * i) % mod;
			memo[i] = t;
		}

		int v = 1;
		while (f > 0) {
			long num = (f / mod);
			v = (v * (int) modpow(t,  num, mod)) % mod;
			v = (v * memo[(int) (f % mod)]) % mod;
			f = f / 5;
		}

		int pow2 = (int) modpow(2,  num2s,mod);

		int x = 0;
		for (int i = 0; i < mod; i++)
			if ((pow2 * i) % mod == v) {
				x = i;
				break;
			}

		for (int i = (int) x;; i += mod)
			if (i % 32 == 0)
				return i;
	}

	public static void main(String[] args) {
		System.out.println(last5(1000000000000L));
	}

}
