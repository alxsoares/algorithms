package alex.algorithms.math.projecteuler.level4;

public class Euler094 {
	public static void main(String[] args) {
		long x = 2;
		long y = 1;
		long limit = 1000000000;
		long result = 0;

		while (true) {
			// b = a+1
			long aTimes3 = 2 * x - 1;
			long areaTimes3 = y * (x - 2);
			if (aTimes3 > limit)
				break;

			if (aTimes3 > 0 && areaTimes3 > 0 && aTimes3 % 3 == 0
					&& areaTimes3 % 3 == 0) {

				long a = aTimes3 / 3;
				long area = areaTimes3 / 3;

				result += 3 * a + 1;
			}

			// b = a-1
			aTimes3 = 2 * x + 1;
			areaTimes3 = y * (x + 2);

			if (aTimes3 > 0 && areaTimes3 > 0 && aTimes3 % 3 == 0
					&& areaTimes3 % 3 == 0) {

				long a = aTimes3 / 3;
				long area = areaTimes3 / 3;

				result += 3 * a - 1;
			}

			long nextx = 2 * x + y * 3;
			long nexty = y * 2 + x;

			x = nextx;
			y = nexty;
		}
		System.out.printf("%d\n", result);
	}

}
