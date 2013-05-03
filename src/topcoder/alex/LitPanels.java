package topcoder.alex;

public class LitPanels {
	int MOD = (int) 1e9 + 7;

	long boundingBox(int x, int y, int sx, int sy) {
		int zones[][] = new int[x][y];
		for (int i = 0; i < x; i++) {
			zones[i][0] |= (1 << 0);
			zones[i][y - 1] |= (1 << 1);
			;
		}
		for (int i = 0; i < y; i++) {
			zones[0][i] |= (1 << 2);
			zones[x - 1][i] |= (1 << 3);
		}
		// bad zones
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (!((i < sx && j < sy) || (i >= x - sx && j >= y - sy))) {
					zones[i][j] |= (1 << 4);
				}
				if (!((i >= x - sx && j < sy) || (i < sx && j >= y - sy))) {
					zones[i][j] |= (1 << 5);
				}
			}
		}
		int maskCount[] = new int[1 << 6];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				maskCount[zones[i][j]]++;
			}
		}
		long pow2[] = new long[x * y + 1];
		pow2[0] = 1;
		for (int i = 1; i <= x * y; i++) {
			pow2[i] = (pow2[i - 1] + pow2[i - 1]) % MOD;
		}

		long dp[][] = new long[1 << 6 + 1][1 << 6];
		dp[1 << 6][1 + 2 + 4 + 8] = 1;
		dp[1 << 6][1 + 2 + 4 + 8 + 16] = 1;
		dp[1 << 6][1 + 2 + 4 + 8 + 32] = 1;
		for (int i = (1 << 6) - 1; i >= 0; i--) {
			for (int m = 0; m < (1 << 6); m++) {
				long res = 0;
				res += (dp[i + 1][m | i] * (pow2[maskCount[i]] + MOD - 1))
						% MOD;
				res += dp[i + 1][m];

				dp[i][m] = (res % MOD);
			}
		}
		return dp[0][0];
	}

	int countPatterns(int X, int Y, int sx, int sy) {
		long res = 0;
		for (int x = 1; x <= X; x++) {
			for (int y = 1; y <= Y; y++) {
				long p = boundingBox(x, y, sx, sy);
				p = (p * (X - x + 1)) % MOD;
				p = (p * (Y - y + 1)) % MOD;
				res += p;
			}
		}
		return (int) ((res + 1) % MOD);
	}

	public static void main(String[] args) {
		LitPanels lp = new LitPanels();
		System.out.println(lp.countPatterns(40, 39, 5, 8));
	}

}
