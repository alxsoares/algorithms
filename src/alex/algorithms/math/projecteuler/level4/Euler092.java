package alex.algorithms.math.projecteuler.level4;

public class Euler092 {

	static int next(int n) {
		int result = 0;
		while (n > 0) {
			result += (n % 10) * (n % 10);
			n /= 10;
		}
		return result;
	}

	public static void main(String[] args) {
		int result = 0;
		for (int start = 1; start < 10000000; ++start) {
			int curr = start;
			while (curr != 1 && curr != 89)
				curr = next(curr);
			result += (curr == 89) ? 1 : 0;
		}
		System.out.printf("%d\n", result);
	}

}
