package alex.algorithms.pattern;

public class KMP {
	/**
	 * Computes Longest Prefix Suffix 
	 */
	public static int[] computeLPS(char[] pattern) {

		int[] lps = new int[pattern.length];
		int previouslength = 0;
		int i = 0;
		lps[0] = 0;
		i = 1;

		while (i < pattern.length) {
			if (pattern[i] == pattern[previouslength]) {
				previouslength++;
				lps[i++] = previouslength;
			} else {
				if (previouslength != 0) {
					previouslength = lps[previouslength-1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
			System.out.printf(" i, previouslength %d %d\n", i, previouslength);
		}
		for (int j = 0; j < lps.length; j++) {
			System.out.printf("%d ", lps[j]);
		}
		System.out.println();
		return lps;
	}

	public static void KMPSearch(String text, String pattern) {
		int[] lps = computeLPS(pattern.toCharArray());
		int i = 0, j = 0;
		while (i < text.length()) {
			if (pattern.charAt(j) == text.charAt(i)) {
				i++;
				j++;
			}
			if (j == pattern.length()) {
				System.out.printf("Pattern found at %d\n", i - j);
				j = lps[j - 1];
			} else if (j < pattern.length() && i < text.length() && pattern.charAt(j) != text.charAt(i)) {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}
	}

	public static void main(String[] args) {
		String text = "ABABCABABABABABCABAABABDABACDABABCABAABABCABABAB";
		String pattern = "ABABCABABAB";
		KMPSearch(text,pattern);

	}

}
