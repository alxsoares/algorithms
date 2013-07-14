package alex.algorithms.math.projecteuler;

import java.math.BigInteger;

public class Euler432a {
	

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
	public static BigInteger bitwiseGcd(BigInteger u, BigInteger v) {
		if (u.equals(BigInteger.ZERO))
			return v;
		if (v.equals(BigInteger.ZERO))
			return u;

		int uBit = u.getLowestSetBit();
		int vBit = v.getLowestSetBit();
		int k = (uBit <= vBit ? uBit : vBit);

		while (u.signum() > 0) {
			u = u.shiftRight(u.getLowestSetBit());
			v = v.shiftRight(v.getLowestSetBit());
			if (u.subtract(v).signum() >= 0) {
				u = (u.subtract(v)).shiftRight(1);
			} else {
				v = (v.subtract(u)).shiftRight(1);
			}
		}

		return v.shiftLeft(k);
	}
	public static long gcdAlex(long u, long  v) {
		if (u == v)
			return u;
		if (u == 0 || v == 0)
			return java.lang.Math.max(u, v);
		if (u % 2 == 0) {
			if (v % 2 == 0) {
				return gcdAlex(u >> 1, v >> 1) << 1;
			}
			return gcdAlex(u >> 1, v);
		}
		if (v % 2 == 0) {
			return gcdAlex(u, v >> 1);
		}
		if (u > v)
			return gcdAlex((u - v) >> 1, v);
		return gcdAlex(u, (v - u) >> 1);
	}
	public static long lcm(final long a, final long b) {
		return a * b / gcdAlex(a, b);
	}
	
	public static BigInteger lcm(BigInteger a, BigInteger b) {
		return a .multiply(b).divide(bitwiseGcd(a, b));
	}
	static int cache[] = new int[9999];

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
	
	public static long SN(long n){
		if(n < cache.length){
			return cache[(int) n];
		}
		long SG = (n*(n+3))/2;
		long sum=0;
		for(int i=2;i<=n;i++){
			sum+=SN((long) Math.floor(((double)n)/i));
		}
		return SG - sum;
	}
	public static void main(String[] args) {
		listTotients();
		long sum=0;
		System.out.println(SN(510510));
//		for(long i=1;i<=510510;i++){
////			if(i%1000==0)
//			{
//				System.out.println(i+"->"+sum);
//			}
//			sum+=SN(i);
//		}
		System.out.println(sum);
	}
}
