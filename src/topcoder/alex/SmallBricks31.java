package topcoder.alex;

import java.util.Arrays;

/**
 * 
 * http://community.topcoder.com/stat?c=problem_statement&pm=11549&rd=14548
 * 
 */
public class SmallBricks31 {
	int w;
	int mem[][] = new int[11][1 << 10];

	int backtrack(int h, int previousRow, int x, int currentRow) {
		if (x == w) {
			// For each generated current row, continue the recursion
			// of F to find the count.
			return F(h - 1, currentRow);
		}
		long res = 0;
		// no brick
		res = backtrack(h, previousRow, x + 1, currentRow);

		// 1x1x1 : (Requires a brick in the bellow row).
		boolean b1 = ((previousRow & (1 << x)) != 0);
		if (b1) {
			int nRow = currentRow | (1 << x); // add brick to the current row
			res += backtrack(h, previousRow, x + 1, nRow);
		}
		// 1x1x2 : (Requires two bricks in the bellow row).
		if (b1 && ((previousRow & (1 << (x + 1))) != 0)) {
			// add brick to the current row
			int nRow = currentRow | (1 << x) | (1 << (x + 1));
			res += backtrack(h, previousRow, x + 2, nRow);
		}
		// 1x1x3 : (Requires two bricks directly bellow the extremes of the
		// bricks).
		if (b1 && ((previousRow & (1 << (x + 2))) != 0)) {
			// add brick to the current row
			int nRow = currentRow | (1 << x) | (1 << (x + 1)) | (1 << (x + 2));
			res += backtrack(h, previousRow, x + 3, nRow);
		}
		return (int) (res % 1000000007);
	}

	int F(int h, int previousRow) {
		// Memoization (Remember
		int res = mem[h][previousRow];
		if (res == -1) {
			if (h == 0) {
				res = 1; // base case
			} else {
				// Enumerate the ways to do the current row,
				// add F(h-1, currentRow) to the result for each one
				res = backtrack(h, previousRow, 0, 0);
			}
		}
		return res;
	}

	public int countWays(int w, int h) {
		this.w = w;
		for (int i = 0; i < mem.length; i++) {
			Arrays.fill(mem[i], -1);
		}
		// The base is a sequence of w 1 bits. (1<<w)-1.
		return F(h, (1 << w) - 1);
	}

	public static void main(String[] args) {
		SmallBricks31 s = new SmallBricks31();
		System.out.println(s.countWays(3, 2));
		System.out.println(s.countWays(5, 5));
	}

}
