package alex.algorithms.math.projecteuler.level2;

public class Euler040 {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < 1000000; i++) {
			sb.append(i);
		}
		int power = 1;
		int prod = 1;
		while (power <= 1000000) {
			prod *= Integer.valueOf(sb.substring(power - 1, power));
			power*=10;
		}
		System.out.printf("%d\n", prod);
	}

}
