package topcoder.alex;

public class DropCoins {

	public int getMinimum(String[] board, int K) {
		int w = board.length;
		int h = board[0].length();
		final int INF = 1000000000; // A large number
		int res = INF;

		// For each subrectangle of the board:
		for (int x0 = 0; x0 < w; x0++) {
			for (int x1 = x0 + 1; x1 <= w; x1++) {
				for (int y0 = 0; y0 < h; y0++) {
					for (int y1 = y0 + 1; y1 <= h; y1++) {
						// Count the coins inside the subrectangle:
						int coins = 0;
						for (int i = x0; i < x1; i++) {
							for (int j = y0; j < y1; j++) {
								if (board[i].charAt(j) == 'o') {
									coins++;
								}
							}
						}
						// If there are K coins, find the minimum number of
						// moves to
						// end with that subrectangle. Remember the minimum
						// overall.
						if (coins == K) {
							int a = x0; // number of left columns to remove.
							int b = w - x1; // number of right columns to remove
							int c = y0; // number of top rows to remove
							int d = h - y1; // number of bottom rows to remove.
							res = Math.min(res, a + b + c + d + Math.min(a, b)
									+ Math.min(c, d));
						}
					}
				}
			}
		}
		return ((res == INF) ? -1 : res);
	}

	public static void main(String[] args) {
		DropCoins d = new DropCoins();
		System.out.println(d.getMinimum(
				new String[]  { ".o..", "oooo", "..o." } , 3));
	}

}
