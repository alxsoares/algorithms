package alex.algorithms.math.projecteuler.level5;

public class Euler121 {
	static  long calc(int n) {
		int numVals = (n - 1) / 2;
		long[] values = new long[numVals];
		for (int i = 0; i < numVals; i++) {
			values[i] = 1;
		}
		for (; n > 0; n--) {
			for (int i = 0; i < numVals - 1; i++) {
				values[i] += n * values[i + 1];
			}
			values[numVals - 1] += n;
		}
		return values[0];
	}
	

	public static void main(String[] args) {
		long fact = 1;
		for(int i=2;i<=16;i++){
			fact*=i;
		}
		System.out.println(Math.floor((double)fact/calc(15)));
	}

}
