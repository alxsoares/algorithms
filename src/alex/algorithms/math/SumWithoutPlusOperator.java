package alex.algorithms.math;

public class SumWithoutPlusOperator {

	public static int sum(int a, int b) {
		do {
			int sum = a ^ b;
			int carry = (a & b) << 1;
			b = carry;
			a = sum;
		} while (b != 0);

		return a;
	}

	public static void main(String[] args) {
		System.out.println(sum(10,2));
		System.out.println(sum(Integer.MAX_VALUE/2,Integer.MAX_VALUE/2));
	}

}
