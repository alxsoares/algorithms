package alex.algorithms.math.projecteuler.level3;

import java.math.BigInteger;

public class Euler057 {
	/**
	 * n_{k 1} = n_k 2d_k
	 * 
	 * d_{k 1} = n_k d_k
	 * 
	 * d_{k 1} = n_{k 1} - d_k
	 */
	public static void main(String[] args) {
		BigInteger d = BigInteger.valueOf(2);
		BigInteger n = BigInteger.valueOf(3);
		int count = 0;
		for (int i = 1; i < 1000; i++) {
			n = n.add(BigInteger.valueOf(2).multiply(d));
			d = n.subtract(d);
			if (n.toString().length() > d.toString().length())
				count++;
		}
		System.out.printf("%d\n", count);
	}

}
