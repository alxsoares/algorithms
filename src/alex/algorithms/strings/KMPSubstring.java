package alex.algorithms.strings;

public class KMPSubstring {
	private int[][] buildDFA(String pat) {
		int M = pat.length();
		int R = 256;
		int[][] dfa = new int[R][M];
		dfa[pat.charAt(0)][0] = 1;
		for (int X = 0, j = 1; j < M; j++) {
			for (int c = 0; c < R; c++) {
				dfa[c][j] = dfa[c][X];// copy restart states
			}
			dfa[pat.charAt(j)][j] = j + 1;
			X = dfa[pat.charAt(j)][X];// current restart state
		}
		return dfa;
	}

	public int search(String txt, String pat) {
		int[][] dfa = buildDFA(pat);
		int N = txt.length();
		int M = pat.length();
		int j = 0, i = 0;
		for (; i < N && j < M; i++) {
			j = dfa[txt.charAt(i)][j];
		}
		if (j == M) {
			return i - M;
		} else {
			return -1;
		}
	}

	public static void main(String[] args) {
		KMPSubstring search = new KMPSubstring();
		System.out.println(search.search("AAAAAAAalex", "alex"));
	}

}
