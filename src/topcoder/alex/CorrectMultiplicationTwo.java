package topcoder.alex;

import static java.lang.Math.*;

public class CorrectMultiplicationTwo {

	public int getMinimum(int a, int b, int c) {
		int res = (a + b + c - 3);
		for (int A = 1; A <= c + res; A++) {
			for (int B = 1; B <= (c + res) / A; B++) {
				int C = A * B;
				res = Math.min(res,
						Math.abs(A - a) + Math.abs(B - b) + Math.abs(C - c));
			}
		}
		return res;
	}

	long MAX_A = 1L << 15, MAX_B = 1L << 30;

	long dist(long a, long b, long c, long A, long B) {
		return abs(A - a) + abs(B - b) + abs(A * B - c);
	}

	long search(int a, int b, int c) {
		long best = 1L << 42;
		// search over all possible values of A
		for (long A = 1; A < MAX_A; A++) {
			long B = 0;
			// binary/ternary search to find the best B for a fixed A
			for (long D = MAX_B; D > 0; D >>= 1) {
				long dist0 = dist(a, b, c, A, B + D), dist1 = dist(a, b, c, A,
						B + D + 1);
				if (dist0 >= dist1)
					B += D;
			}
			if (B == 0)
				B++;
			long dist0 = dist(a, b, c, A, B), dist1 = dist(a, b, c, A, B + 1);
			best = min(best, min(dist0, dist1));
		}
		return best;
	}

	public long getMinimum2(int a, int b, int c) {
		return min(search(a, b, c), search(b, a, c));
	}

	public static void main(String[] args) {
		CorrectMultiplicationTwo c = new CorrectMultiplicationTwo();
		System.out.printf("Minimum difference is %d\n",
				c.getMinimum(19, 28, 522));
		System.out.printf("Minimum difference is %d\n",
				c.getMinimum(1000, 100, 10));
		System.out.printf("Minimum difference is %d\n",
				c.getMinimum(399, 522, 199999));
		
		System.out.printf("Minimum difference is %d\n",
				c.getMinimum2(19, 28, 522));
		System.out.printf("Minimum difference is %d\n",
				c.getMinimum2(1000, 100, 10));
		System.out.printf("Minimum difference is %d\n",
				c.getMinimum2(399, 522, 199999));
	}

}
