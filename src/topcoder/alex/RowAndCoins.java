package topcoder.alex;

import java.util.Arrays;

public class RowAndCoins {
	String cells;
	int t;

	boolean mem[][] = new boolean[1 << 14][2];

	boolean rec(int mask, int player) {
		boolean res = mem[mask][player];
		if (!res) {
			// will this player will?
			int ec = 0;
			char fc = '?';
			for (int i = 0; i < t; i++) {
				if ((mask & (1 << i)) == 0) {
					ec++;
					fc = cells.charAt(i);
				}
			}
			if (ec == 1) {
				res = ((player == 0) == (fc == 'A'));
			} else {
				res = false;
				for (int i = 0; i < t; i++) {
					int taken = 0;
					for (int j = i; j < t; j++) {
						if ((mask & (1 << j)) == 0) {
							taken++;
							if (ec - taken >= 1) {
								int nmask = (mask | (((1 << taken) - 1)) << i);
								res |= (!(rec(nmask, (player + 1) % 2)));
							}
						} else {
							break;
						}
					}
				}

			}
		}
		return res;
	}

	public String getWinner(String cells) {
		t = cells.length();
		this.cells = cells;
		Arrays.fill(mem, false);
		return rec(0, 0) ? "Alice" : "Bob";
	}

}
