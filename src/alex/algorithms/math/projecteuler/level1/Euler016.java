package alex.algorithms.math.projecteuler.level1;

import java.math.BigInteger;

public class Euler016 {
	/**
	 *  public static double powIter(final double n, int e) {
        double factor = n;
        double res = 1;
        while (e > 0) {
            if ((e & 1) > 0) {
                res = res * factor;
            }
            e = e >> 1;
            factor *= factor;
        }
        return res;
    }
	 * @param base
	 * @param exp
	 * @return
	 */
	public static BigInteger fastExponentiation(BigInteger base, int exp){
		BigInteger factor = base;
		BigInteger res = new BigInteger("1");
		while(exp > 0){
			if((exp & 1)>0){
				res = res.multiply(factor);
			}
			exp = exp>>1;
			factor = factor.multiply(factor);
		}
		return res;
	}
	
	public static void main(String[] args) {
		BigInteger p = fastExponentiation(new BigInteger("2"), 1000);
		String s = p.toString();
		BigInteger sum =new BigInteger("0");
		for (int i = 0; i < s.length(); i++) {
			sum = sum.add(new BigInteger(s.substring(i, i+1)));
		}
		System.out.printf("%s\n", sum.toString());
	}

}
