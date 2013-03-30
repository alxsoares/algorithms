package alex.algorithms.pattern.search;

public class FiniteAutomata {

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

	public static void computeTransitionsTable(char[] pattern, int M, int[][] TF) {
		for (int state = 0; state <= M; state++) {
			for (int x = 0; x < 256; x++) {
				TF[state][x] = nextState(pattern, M, state, x);
			}
		}
	}
	public static void search(String pattern, String text){
		int M = pattern.length();
		int N = text.length();
		int [][] TF = new int[M+1][256];
		computeTransitionsTable(pattern.toCharArray(), M, TF);
		int state =0;
		for(int i=0; i< N;i++){
			state = TF[state][text.charAt(i)];
			if(state==M){
				System.out.printf("Pattern found at index %d\n", i-M+1);
			}
		}
	}
	public static void main(String[] args) {
		search("Alex", "AlexAlexAlexAAAAAAAAAaaaaAlex");
	}

}
