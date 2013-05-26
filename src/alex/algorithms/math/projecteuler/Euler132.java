package alex.algorithms.math.projecteuler;

import java.math.BigInteger;

public class Euler132 {

	 public static boolean isPrime(int n) {
	      if (n<2) return false;
	      if (n==2) return true;
	      for (int i=2; i*i<=n; i++)
	         if (n%i==0) return false;
	      return true;
	   }

	   public static void main(String[] args) {
	      BigInteger ten = BigInteger.valueOf(10);
	      int total=0;
	      int count=0;
	      for (int i=5; count<40; i++) {
	         if (!isPrime(i)) continue;
	         int r = ten.modPow(BigInteger.valueOf(1000000000), BigInteger.valueOf(i)).intValue();
	         if (r==1) {
	            total+=i;
	            count++;
	         }
	      }
	      System.out.println("total: " + total);
	   }
}
