package alex.algorithms.math.projecteuler.level3;

import java.math.BigInteger;

public class Euler055 {

	static void reverse(char [] a){
		for(int i=0; i<a.length/2;i++){
			char aux=a[i];
			a[i] = a[a.length-i-1];
			a[a.length-i-1] = aux;
		}
	}
	static BigInteger reverse(BigInteger number) {
	    char[] k = number.toString().toCharArray();
	    reverse(k);
	    return new BigInteger(new String(k));
	}
	 
	static boolean isPal(BigInteger number) {
	    return number.equals(reverse(number));
	}
	
	static boolean isLychrel(int number) {
		BigInteger n = BigInteger.valueOf(number);
		for(int i=0; i< 50;i++){
			n = n.add(reverse(n));
			if(isPal(n)) return false;
		}
		return true;
		
	}
	public static void main(String[] args) {
		int result = 0;
		for (int i = 10; i < 10000; i++) {
		    if (isLychrel(i)) result++;
		}
		System.out.printf("%d\n",result);
	}

}
