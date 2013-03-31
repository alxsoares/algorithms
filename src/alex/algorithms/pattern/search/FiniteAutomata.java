package alex.algorithms.pattern.search;

public class FiniteAutomata {

	private static final int NUMBER_OF_CHARS = 256;

	public static int nextState(char[] pattern, int M, int state, int x) {
		if (state < M && pattern[state] == x) {
			return state + 1;
		}
		for (int nextState = state; nextState > 0; nextState--) {
			if (pattern[nextState - 1] == x) {
				int i;
				for (i = 0; i < nextState - 1; i++) {
					if (pattern[i] != pattern[state - nextState + i + 1]) {
						break;
					}
				}
				if (i == nextState - 1)
					return nextState;
			}

		}
		return 0;
	}

	public static void computeTransitionsFunctionsNaive(char[] pattern, int M,
			int[][] TF) {
		for (int state = 0; state <= M; state++) {
			for (int x = 0; x < NUMBER_OF_CHARS; x++) {
				TF[state][x] = nextState(pattern, M, state, x);
			}
		}
	}

	/**
	 * Time Complexity O((m^3)*NUMBER_OF_CHARS)-> to be improved
	 */
	public static void search(String pattern, String text) {
		int M = pattern.length();
		int N = text.length();
		int[][] TF = new int[M + 1][NUMBER_OF_CHARS];
		computeTransitionsFunctionsNaive(pattern.toCharArray(), M, TF);
		int state = 0;
		for (int i = 0; i < N; i++) {
			state = TF[state][text.charAt(i)];
			if (state == M) {
				System.out.printf("Pattern found at index %d\n", i - M + 1);
			}
		}
	}

	public static int[][] computeTransitionsFunctions(char[] pattern, int M) {
		int[][] TF = new int[M + 1][NUMBER_OF_CHARS];

		for (int x = 0; x < NUMBER_OF_CHARS; x++) {
			TF[0][x] = 0;
		}
		TF[0][pattern[0]] = 1;
		int lps = 0;// Longest prefix suffix.
		for (int i = 1; i <= M; i++) {
			for (int x = 0; x < NUMBER_OF_CHARS; x++) {
				TF[i][x] = TF[lps][x];
			}
			if (i < M) {
				TF[i][pattern[i]] = i + 1;
				lps = TF[lps][pattern[i]];
			}
		}
		return TF;
	}

	public static void searchOptimised(String pattern, String text) {
		int M = pattern.length();
		int N = text.length();
		int[][] TF = computeTransitionsFunctions(pattern.toCharArray(), M);
		int state = 0;
		for (int i = 0; i < N; i++) {
			state = TF[state][text.charAt(i)];
			if (state == M) {
				System.out.printf("Pattern found at index %d\n", i - M + 1);
			}
		}
	}

	public static void main(String[] args) {
		search("Alex", "AlexAlexAlexAAAAAAAAAaaaaAlex");
		searchOptimised("Alex", "AlexAlexAlexAAAAAAAAAaaaaAlex");
	}

}
