package alex.algorithms.math.projecteuler;

public class Euler023 {

	static boolean isabundant(int num) {
		int sum = 0;
		double UPPER_BOUND = Math.sqrt(num);
		for (int i = 1; i <= UPPER_BOUND; i++) {
			if (num % i == 0) {
				if (i == UPPER_BOUND || i == 1) {
					sum += i;
				} else {
					sum += i;
					sum += num / i;
				}
			}
		}
		return sum > num;
	}

	public static void main(String[] args) {
		int sum = 0, t;
		for (int i = 1; i < 28123; i++) {
			t = 0;
			for (int j = 12; j * 2 <= i; j++)
				if (isabundant(j) && isabundant(i - j)) {
					t = 1;
					break;
				}
			if (t == 0)
				sum += i;

		}
		System.out.printf("%s\n", sum);
	}

}
