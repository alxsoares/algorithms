package topcoder.alex;

public class DengklekMessage {
	int matches[][] = new int[501][50];
	int transition[][] = new int[501][50];
	double dp[][] = new double[502][502];

	int[] initKMP(String s) {
		int n = s.length();
		int[] next = new int[n + 1];
		next[0] = -1;
		for (int i = 0, j = -1; i < n; next[++i] = ++j) {
			while ((j >= 0) && (s.charAt(i) != s.charAt(j))) {
				j = next[j];
			}
		}
		return next;
	}

	public double theExpected(String[] pieces, String[] goodSubstring, long K) {
		String good = "";
		for (int i = 0; i < goodSubstring.length; i++) {
			good += goodSubstring[i];
		}
		int L = good.length();

		int kmp[] = initKMP(good);
		for (int x = 0; x <= L; x++) {
			String prev = good.substring(0, x);
			for (int p = 0; p < pieces.length; p++) {
				String news = prev + pieces[p];
				matches[x][p] = 0;
				int m = 0;
				int i = 0;
				while (m + i < news.length()) {
					if (good.charAt(i) == news.charAt(m + i)) {
						if (i == good.length() - 1) {
							if (!(x == L && m == 0)) {
								matches[x][p]++;
							}
							m = m + i - kmp[i];
							if (kmp[i] > -1) {
								i = kmp[i];
							} else {
								i = 0;
							}
						} else {
							i++;
						}
					} else {
						m = m + i - kmp[i];
						if (kmp[i] > -1) {
							i = kmp[i];
						} else {
							i = 0;
						}
					}
				}
				transition[x][p] = i;
			}

		}
		for (int x = 0; x <= L; x++) {
			dp[x][0] = 0;
		}
		int MAX = L + 1;
		for (int k = 1; k <= MAX; k++) {
			for (int x = 0; x <= L; x++) {
				dp[x][k] = 0;
				for (int p = 0; p < pieces.length; p++) {
					dp[x][k] += matches[x][p] + dp[transition[x][p]][k - 1];
				}
				dp[x][k] /= pieces.length;
			}
		}
		if (K <= L) {
			return dp[0][(int) K];
		} else {
			return dp[0][L] + (dp[0][L + 1] - dp[0][L]) * (K - L);
		}
	}

	public static void main(String[] args) {
		DengklekMessage d = new DengklekMessage();
		System.out.println(d.theExpected(new String[] { "0" },
				new String[] { "00" }, 10));
	}

}
