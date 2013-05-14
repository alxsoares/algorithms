package alex.algorithms.math.projecteuler.level1;

import java.math.BigInteger;

public class Euler020 {

	public static void main(String[] args) {
		BigInteger f = new BigInteger("100");
		for(int i=99;i>=2;i--){
			f = f.multiply(BigInteger.valueOf(i));
		}
		String s = f.toString();
		BigInteger sum = BigInteger.valueOf(0);
		for(int i=0; i<s.length();i++){
			sum = sum.add(new BigInteger(s.substring(i, i+1)));
		}
		System.out.printf("%s\n",sum.toString());
	}

}
