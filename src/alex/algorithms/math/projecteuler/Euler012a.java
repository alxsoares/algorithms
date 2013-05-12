package alex.algorithms.math.projecteuler;

import java.util.ArrayList;

public class Euler012a {

	public static void main(String[] args) {
		PrimeDivisors();
		// CoPrimes();
	}

	public static void PrimeDivisors() {
		int number = 1;
		int i = 2;
		int[] primelist = ESieve(75000);

		while (PrimeFactorisationNoD(number, primelist) < 500) {
			number += i;
			i++;
		}

		System.out.println(number);

	}

	private static int PrimeFactorisationNoD(int number, int[] primelist) {
		int nod = 1;
		int exponent;
		int remain = number;

		for (int i = 0; i < primelist.length; i++) {

			// In case there is a remainder this is a prime factor as well
			// The exponent of that factor is 1
			if (primelist[i] * primelist[i] > number) {
				return nod * 2;
			}

			exponent = 1;
			while (remain % primelist[i] == 0) {
				exponent++;
				remain = remain / primelist[i];
			}
			nod *= exponent;

			// If there is no remainder, return the count
			if (remain == 1) {
				return nod;
			}
		}
		return nod;
	}

	// Returns the list of prime numbers up to the input
	public static int[] ESieve(int upperLimit) {

		int sieveBound = (int) (upperLimit - 1) / 2;
		int upperSqrt = ((int) Math.sqrt(upperLimit) - 1) / 2;

		int[] PrimeBits = new int[sieveBound + 1];

		for (int i = 1; i <= upperSqrt; i++) {
			if (PrimeBits[i] == 1) {
				for (int j = i * 2 * (i + 1); j <= sieveBound; j += 2 * i + 1) {
					PrimeBits[j] = 0;
				}
			}
		}

		ArrayList<Integer> numbers = new ArrayList<Integer>(
				(int) (upperLimit / (Math.log(upperLimit) - 1.08366)));
		numbers.add(2);
		for (int i = 1; i <= sieveBound; i++) {
			if (PrimeBits[i] == 1) {
				numbers.add(2 * i + 1);
			}
		}
		int[] ret = new int[numbers.size()];
		for (int c = 0; c < ret.length; c++) {
			ret[c] = numbers.get(c).intValue();
		}

		return ret;
	}

	public static void CoPrimes() {
		int number = 1;
		int i = 2;
		int cnt = 0;
		int Dn1 = 2;
		int Dn = 2;
		int[] primelist = ESieve(1000);

		while (cnt < 500) {
			if (i % 2 == 0) {
				Dn = PrimeFactorisationNoD(i + 1, primelist);
				cnt = Dn * Dn1;
			} else {
				Dn1 = PrimeFactorisationNoD((i + 1) / 2, primelist);
				cnt = Dn * Dn1;
			}
			i++;
		}
		number = i * (i - 1) / 2;

		System.out.println(number);

	}
}
