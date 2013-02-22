package alex.algorithms.math;

public class GCD {

	public static int gcd(int a, int b) {
		while (b > 0) {
			a = a % b;
			a ^= b;
			b ^= a;
			a ^= b;
		}
		return a;
	}

	public static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	public static void main(String[] args) {
		System.out.println(gcd(6, 3));
		System.out.println(lcm(3, 6));

	}

}
