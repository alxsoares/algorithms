package alex.algorithms.math.projecteuler.level4;

import java.math.BigInteger;

public class Euler097 {

	public static void main(String[] args) {
		BigInteger mod = BigInteger.valueOf(10000000000l);
		BigInteger two = BigInteger.valueOf(2);
		BigInteger res = two.modPow(BigInteger.valueOf(7830457),mod).multiply(BigInteger.valueOf(28433)).add(BigInteger.ONE);
		res = res.mod(mod);
		System.out.printf("%d\n", res);
	}

}
