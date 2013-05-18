package alex.algorithms.math.projecteuler.level3;

import java.math.BigInteger;

public class Euler056 {
	static int digitalSum(BigInteger n) {
		String s = n.toString();
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			sum += Integer.valueOf(s.substring(i, i + 1));
		}
		return sum;
	}

	static double log10BigInteger(BigInteger val, int prec) {
		assert (prec > 0 && prec < 16 && val.compareTo(BigInteger.ZERO) > 0);
		String dec = val.toString();
		int n = dec.length();
		if (dec.length() > prec)
			dec = dec.substring(0, prec + 1);
		return n + Math.log10(Double.parseDouble("0." + dec));
	}

	public static void main(String[] args) {
		int max = 0;
		for (int a = 1; a < 100; a++) {
			for (int b = 1; b < 100; b++) {
				BigInteger p = BigInteger.valueOf(a).pow(b);
				int sum = digitalSum(p);
				if (sum > max)
					max = sum;
			}
		}
		System.out.printf("%d\n",max);
	}

}
