package topcoder.alex;

import java.util.Arrays;

public class WallGameDiv1 {

	int w, h;
	String[] costs;
	int dpRabbit[][][][] = new int[50][50][51][51];
	int dpEel[][][][] = new int[50][50][51][51];

	// Sum of costs between (y,x0) and (y,x1-1):
	int sum(int y, int x0, int x1) {
		int s = 0;
		for (int i = x0; i < x1; i++) {
			s += costs[y].charAt(i) - '0';
		}
		return s;
	}

	int playRabbit(int y, int x, int x0, int x1) {
		// Token is currently at point (x,y). There are walls between each
		// (y, i) and (y+1,i) for each x0 <= i < x1 :
		// What should Rabbit do?

		int res = dpRabbit[y][x][x0][x1]; // O(n^3) states because x = x0-1, x0,
											// x1-1 or x1
		if (res == -1) {
			if (!(x0 <= x && x < x1)) {
				// no wall below the Token. Best to move below:
				// It is convenient to use [x,x) as the empty interval. This way
				// the
				// Eel function can easily extend it to include x:
				res = (costs[y + 1].charAt(x) - '0') + playEel(y + 1, x, x, x);
			} else {

				res = Integer.MAX_VALUE;
				// Rabbit decides to move to x0 - 1
				if (x0 > 0) {
					// sum(y,x0-1,x) = Cost to move from (x,y) to (x0-1,y).
					// playEel(y,x0-1, x0,x1) = Cost after Eel decides to place
					// a wall or not.
					//
					res = sum(y, x0 - 1, x) + playEel(y, x0 - 1, x0, x1);
				}

				// Rabbit decides to move to x1:
				if (x1 < w) {
					// sum(y, x+1, x1+1) = Cost to move from (x,y) to (x1,y).
					// playEel(y,x1, x0,x1 ) = Cost after Eel decides to place
					// a wall or not.
					//
					int tem = sum(y, x + 1, x1 + 1) + playEel(y, x1, x0, x1);

					// Rabbit wants the smaller cost:
					res = Math.min(res, tem);
				}
			}
			dpRabbit[y][x][x0][x1] = res; // memoize the case.
		}
		return res;
	}

	int playEel(int y, int x, int x0, int x1) {
		// Token is currently at point (x,y). There are walls between each
		// (y, i) and (y+1,i) for each x0 <= i < x1 :
		// What should Eel do?

		int res = dpEel[y][x][x0][x1]; // Also O(n^3) because x = x0-1 or x1
		if (res == -1) {
			if (y == h - 1) {
				// The token is in the last row. Eel cannot do much about it.
				res = 0;
			} else {
				// What if Eel decides not to place a wall?
				res = playRabbit(y, x, x0, x1); // let the Rabbit do its move

				// What if Eel decides to place a wall?
				if (x1 - x0 < w - 1) { // Cannot fill the whole row with walls
					int tem = playRabbit(y, x, Math.min(x0, x),
							Math.max(x1, x + 1));
					// extend the interval that contains the wall

					// Eel wants the maximum cost:
					res = Math.max(res, tem);
				}
			}
			dpEel[y][x][x0][x1] = res; // memoize the case.
		}
		return res;
	}
	

	public int play(String[] costs) {
		// Save some variables for use by the functions:

		this.costs = costs;
		h = costs.length;
		w = costs[0].length();
		// Initialize dpRabbit and dpEel with -1s.
		for (int i = 0; i < dpRabbit.length; i++) {
			for (int j = 0; j < dpRabbit[i].length; j++) {
				for (int j2 = 0; j2 < dpRabbit[i][j].length; j2++) {
					Arrays.fill(dpRabbit[i][j][j2], -1);
				}
			}
		}
		for (int i = 0; i < dpEel.length; i++) {
			for (int j = 0; j < dpEel[i].length; j++) {
				for (int j2 = 0; j2 < dpEel[i][j].length; j2++) {
					Arrays.fill(dpEel[i][j][j2], -1);
				}
			}
		}

		// Rabbit picks a starting cell in the top row:
		int best = Integer.MAX_VALUE;
		for (int i = 0; i < w; i++) {
			// Rabbit wants the choice that costs the least:
			best = Math.min(best,
					(costs[0].charAt(i) - '0') + playEel(0, i, i, i));
		}
		return best;

	}

	public static void main(String[] args) {
		System.out.println(new WallGameDiv1().play(new String[] { "99999",
				"99999", "99999" }));
	}

}
