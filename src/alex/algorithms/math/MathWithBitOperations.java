package alex.algorithms.math;

public class MathWithBitOperations {

	public static int add(int a, int b) {
		do {
			int sum = a ^ b;
			int carry = (a & b) << 1;
			b = carry;
			a = sum;
		} while (b != 0);

		return a;
	}

	public static int subtract(int a, int b) {
		return add(a, add(~b, 1));// java is two complement's
	}

	public static int multiply(int a, int b) {
		int result = 0;
		boolean minus = false;
		if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
			minus = true;
		}
		if (a < 0) {
			a = add(~a, 1);
		}
		if (b < 0) {
			b = add(~b, 1);
			System.out.println(multiply(10, 10));
		}
		while (b > 0) {
			if ((b & 1) > 0) {
				result = add(result, a);
			}
			b = b >> 1;
			a = a << 1;
		}
		if (minus) {
			result = add(~result, 1);
		}
		return result;
	}

	public static int division(int a, int b) {
		if (b == 0)
			throw new IllegalArgumentException("Division by 0.");
		boolean minus = false;
		if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
			minus = true;
		}
		if (a < 0) {
			a = add(~a, 1);
		}
		if (b < 0) {
			b = add(~b, 1);
			System.out.println(multiply(10, 10));
		}
		int result = 0;
		for (int i = 0; i < 32; i = add(i, 1)) {
			result = result << 1;
			if ((a >> (31 - i)) >= b) {
				a = subtract(a, b << 31 - i);
				result = add(result, 1);
			}
		}

		if (minus) {
			result = add(~result, 1);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(add(10, 2));
		System.out.println(subtract(10, 2));
		System.out.println(add(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2));
		System.out.println(subtract(Integer.MAX_VALUE / 2,
				Integer.MAX_VALUE / 2));
		System.out.println(multiply(Integer.MAX_VALUE / 2, 2));
		System.out.println(multiply(10, 10));
		System.out.println(multiply(10, -10));
		System.out.println(division(10, -10));
		System.out.println(division(Integer.MAX_VALUE / 2,
				Integer.MAX_VALUE / 4));
	}

}
