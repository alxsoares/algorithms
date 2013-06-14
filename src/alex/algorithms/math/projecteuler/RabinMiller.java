package alex.algorithms.math.projecteuler;

import java.math.BigInteger;

public class RabinMiller {

	public static boolean IsProbablePrime(BigInteger n, int[] ar) {
		if (n.compareTo(BigInteger.valueOf(1)) <= 0)
			return false;
		if (n.compareTo(BigInteger.valueOf(2)) == 0)
			return true;
		if (n.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(0)) == 0)
			return false;
		if (n.compareTo(BigInteger.valueOf(9)) < 0)
			return true;
		if (n.mod(BigInteger.valueOf(3)).compareTo(BigInteger.valueOf(0)) == 0)
			return false;
		if (n.mod(BigInteger.valueOf(5)).compareTo(BigInteger.valueOf(0)) == 0)
			return false;

		for (int i = 0; i < ar.length; i++) {
			if (Witness(ar[i], n))
				return false;
		}
		return true;
	}

	private static boolean Witness(int a, BigInteger n) {
		int t = 0;
		BigInteger u = n.subtract(BigInteger.ONE);
		while ((u.and(BigInteger.ONE).compareTo(BigInteger.ZERO)) == 0) {
			t++;
			u = u.shiftRight(1);
		}

		BigInteger xi1 = BigInteger.valueOf(a).modPow(u, n);
		BigInteger xi2;

		for (int i = 0; i < t; i++) {
			xi2 = xi1.modPow(BigInteger.valueOf(2),n);
			if ((xi2.compareTo(BigInteger.ONE) == 0)
					&& (xi1.compareTo(BigInteger.ONE) != 0)
					&& (xi1.compareTo(n.subtract(BigInteger.ONE)) != 0))
				return true;
			xi1 = xi2;
		}
		if (xi1.compareTo(n.subtract(BigInteger.ONE)) != 0)
			return true;
		return false;
	}

	public static void main(String[] args) {
		System.out.println(IsProbablePrime(BigInteger.valueOf(53), new int[] { 2, 3, 5, 7, 11 }));
		System.out.println(BigInteger.valueOf(53).isProbablePrime(9));
	}

}
