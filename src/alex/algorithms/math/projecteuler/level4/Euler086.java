package alex.algorithms.math.projecteuler.level4;

public class Euler086 {

	static boolean isSquare(int x) {
		int s = (int) Math.sqrt(x + 0.5);
		return x == s * s;
	}

	public static void main(String[] args) {
		int count = 0;
		for (int A = 1;; A++) {
			for (int S = 2; S <= 2 * A; S++)
				if (isSquare(A * A + S * S))
					count += (S > A + 1) ? A + 1 - (S + 1) / 2 : S / 2;
			if (count >= 1000000) {
				System.out.printf("%d\n", A);
				break;
			}
		}

	}

}
