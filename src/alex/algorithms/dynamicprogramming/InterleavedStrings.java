package alex.algorithms.dynamicprogramming;

public class InterleavedStrings {
	static boolean isInterleaved(String A, String B, String C) {
		// Find lengths of the two strings
		int M = A.length(), N = B.length();

		// Let us create a 2D table to store solutions of
		// subproblems. C[i][j] will be true if C[0..i+j-1]
		// is an interleaving of A[0..i-1] and B[0..j-1].
		boolean IL[][] = new boolean[M + 1][N + 1];

		// C can be an interleaving of A and B only if sum
		// of lengths of A & B is equal to length of C.
		if ((M + N) != C.length())
			return false;
		
		IL[0][0] = true;
		
		for (int j = 1; j <= N; j++) {
			if (B.charAt(j - 1) == C.charAt(j - 1))
				IL[0][j] = IL[0][j - 1];
		}
		for (int i = 1; i <= M; i++) {
			if (A.charAt(i - 1) == C.charAt(i - 1))
				IL[i][0] = IL[i - 1][0];
		}
		// Process all characters of A and B
		for (int i = 1; i <= M; ++i) {
			for (int j = 1; j <= N; ++j) {
				if (i >= 1 && j >= 1 && A.charAt(i - 1) == C.charAt(i + j - 1)
						&& B.charAt(j - 1) != C.charAt(i + j - 1))
					IL[i][j] = IL[i - 1][j];

				// Current character of C matches with current character of B,
				// but doesn't match with current character of A
				else if (i >= 1 && A.charAt(i - 1) != C.charAt(i + j - 1)
						&& j >= 1 && B.charAt(j - 1) == C.charAt(i + j - 1))
					IL[i][j] = IL[i][j - 1];

				// Current character of C matches with that of both A and B
				else if (i >= 1 && j >= 1
						&& A.charAt(i - 1) == C.charAt(i + j - 1)
						&& B.charAt(j - 1) == C.charAt(i + j - 1))
					IL[i][j] = (IL[i - 1][j] || IL[i][j - 1]);
			}
		}

		return IL[M][N];
	}

	// A function to run test cases
	static void test(String A, String B, String C) {
		if (isInterleaved(A, B, C))
			System.out.printf("%s is interleaved of %s and %s \n", C, A, B);
		else
			System.out.printf("%s is not interleaved of %s and %s \n", C, A, B);
	}

	public static void main(String[] args) {
		test("XXY", "XXZ", "XXZXXXY");
		test("XY", "WZ", "WZXY");
		test("XY", "X", "XXY");
		test("YX", "X", "XXY");
		test("XXY", "XXZ", "XXXXZY");
	}

}
