package alex.algorithms.math.projecteuler.level4;

public class Euler090 {


	static boolean check(int i, int j, int a, int b) {
		return (((a & 1 << i) > 0 && (b & 1 << j) > 0))
				|| (((b & 1 << i) > 0 && (a & 1 << j) > 0));
	}

	public static void main(String[] args) {
		int a, b;
		int count = 0;
		for (a = 0; a < 1 << 10; a++)
			if (Integer.bitCount(a) == 6)
				for (b = a; b < 1 << (10); b++)
					if (Integer.bitCount(b) == 6) {
						if (!check(0, 1, a, b))
							continue;
						if (!check(0, 4, a, b))
							continue;
						if (!check(0, 9, a, b) && !check(0, 6, a, b))
							continue;
						if (!check(1, 6, a, b) && !check(1, 9, a, b))
							continue;
						if (!check(2, 5, a, b))
							continue;
						if (!check(3, 6, a, b) && !check(3, 9, a, b))
							continue;
						if (!check(4, 9, a, b) && !check(4, 6, a, b))
							continue;
						if (!check(6, 4, a, b) && !check(9, 4, a, b))
							continue;
						if (!check(8, 1, a, b))
							continue;
						count++;
					}
		System.out.printf("count: %d\n", count);

	}

}
