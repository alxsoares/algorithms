package alex.algorithms.math.projecteuler.level1;

import java.math.BigInteger;

public class Euler025 {
	public static BigInteger fastFibIterative(int n) {
		BigInteger i = BigInteger.ONE;
		BigInteger h = BigInteger.ONE;
		BigInteger j = BigInteger.ZERO;
		BigInteger k = BigInteger.ZERO;
		BigInteger t;
		while (n > 0) {
			if (n % 2 == 1) { // if n is odd
				t = j.multiply(h);
				j = i.multiply(h).add(j.multiply(k)).add(t);
				i = i.multiply(k).add(t);
			}
			t = h.multiply(h);
			h = BigInteger.valueOf(2).multiply(k).multiply(h).add(t);
			k = k.multiply(k).add(t);
			n = n>>1;
		}
		return j;
	}

	public static BigInteger b1 = new BigInteger("1");
	public static BigInteger b2 = new BigInteger("1");
	public static BigInteger b3 = new BigInteger("0");

	public static void main(String[] args) {
		for (int i = 12;; i++) {
			BigInteger fib = fastFibIterative(i);
			if (fib.toString().length()>= 1000) {
				System.out.println(fib.toString().length());
				System.out.println(i);
				break;
			}
		}
	}

}
