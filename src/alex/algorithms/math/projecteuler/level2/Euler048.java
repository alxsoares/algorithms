package alex.algorithms.math.projecteuler.level2;

public class Euler048 {

	public static void main(String[] args) {
		long result = 0;
		long modulo = 10000000000L;
		 
		for (int i = 1; i <= 1000; i++) {
		    long square = i;
		    for (int j = 1; j < i; j++) {
		        square *= i;
		        if (square >= Long.MAX_VALUE / 1000) {
		            square %= modulo;
		        }
		    }
		 
		    result += square;
		    result %= modulo;
		}
		System.out.printf("%d\n",result);
	}

}
