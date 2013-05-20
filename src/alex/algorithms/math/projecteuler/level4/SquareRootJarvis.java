package alex.algorithms.math.projecteuler.level4;

import java.math.BigInteger;

public class SquareRootJarvis {
	static final BigInteger TEN = BigInteger.valueOf(10);
	static final BigInteger FIVE = BigInteger.valueOf(5);
	static final BigInteger HUNDRED = BigInteger.valueOf(100);

	static BigInteger squareRoot(int n, int digits) {
		BigInteger limit = BigInteger.valueOf(10).pow(digits+1);
	    BigInteger a = BigInteger.valueOf(5 * n);
	    BigInteger b = BigInteger.valueOf(5);

	    while (b.compareTo(limit)<0) {
	        if (a.compareTo(b)>=0) {
	            a=a.subtract(b);
	            b = b.add(TEN);
	        } else {
	            a =a.multiply(HUNDRED);
	            b = b.divide(TEN).multiply(HUNDRED).add(FIVE) ;
	        }
	    }
	 
	    return b.divide(HUNDRED);
	}
	static int digitalSum(BigInteger n) {
		String s = n.toString();
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			sum += Integer.valueOf(s.substring(i, i + 1));
		}
		return sum;
	}
	public static void main(String[] args) {
		System.out.println(squareRoot(3, 20).toString());
		System.out.println(Math.sqrt(3));
		int result = 0;
         int j = 1;

         for (int i = 1; i <= 100; i++) {
             if (j * j == i) {
                 j++;
                 continue;
             }                               
             result += digitalSum(squareRoot(i, 100));
         }    
         System.out.println(result);
	}

}
