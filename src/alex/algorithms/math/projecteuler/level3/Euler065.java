package alex.algorithms.math.projecteuler.level3;

import java.math.BigInteger;

public class Euler065 {

	public static void main(String[] args) {
		long start_time = System.currentTimeMillis();
		// Used formula n2 = a0*n1+n2
		int iterate = 2;
		BigInteger denom = new BigInteger("1");
		BigInteger numerator = new BigInteger("2");

		for (; iterate <= 100; ++iterate) {
			int convergent = (iterate % 3 == 0) ? 2 * (iterate / 3) : 1;
			BigInteger temp = denom;
			denom = numerator;
			numerator = denom.multiply(new BigInteger(convergent + "")).add(
					temp);
		}

		int summ = 0;
		char[] tempdata = numerator.toString().toCharArray();
		for (int index = 0; index <= tempdata.length - 1; ++index) {
			summ += Integer.parseInt(tempdata[index] + "");
		}
		System.out
				.println("sum of digits in the numerator of the 100th convergent of the continued fraction for e:"
						+ summ);
		System.out.println("Total time taken:"
				+ ((System.currentTimeMillis() - start_time) / 1000) + "sec");
	}

}
