package alex.algorithms.math;

import java.math.BigInteger;
import java.util.Random;

public class KaratsubaFastMultiplication {
	private static final int CUTOFF = 1500;

	//O(n^1.5) time complexity for multiplication.
	public static BigInteger karatsuba(BigInteger x, BigInteger y) {

		int N = Math.max(x.bitLength(), y.bitLength());
		if (N <= CUTOFF)
			return x.multiply(y); // optimize this parameter

		// number of bits divided by 2, rounded up
		N = (N / 2) + (N % 2);

		// x = a + 2^N b, y = c + 2^N d
		BigInteger b = x.shiftRight(N);
		BigInteger a = x.subtract(b.shiftLeft(N));
		BigInteger d = y.shiftRight(N);
		BigInteger c = y.subtract(d.shiftLeft(N));

		// compute sub-expressions
		BigInteger ac = karatsuba(a, c);
		BigInteger bd = karatsuba(b, d);
		BigInteger abcd = karatsuba(a.add(b), c.add(d));

		return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(
				bd.shiftLeft(2 * N));
	}

	public static void main(String[] args) {
		int N = 300000;
		Random random = new Random();
		BigInteger a = new BigInteger(N, random);
		BigInteger b = new BigInteger(N, random);

		long start = System.currentTimeMillis();
		BigInteger c = karatsuba(a, b);
		long stop = System.currentTimeMillis();
		System.out.println(stop - start);

		start = System.currentTimeMillis();
		BigInteger d = a.multiply(b);
		stop = System.currentTimeMillis();
		System.out.println(stop - start);

		System.out.println((c.equals(d)));

	}

}
