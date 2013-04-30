package alex.algorithms.dynamicprogramming;


//@formatter:off
/**
 * 
 *Let the input string be str[l……h]. The problem can be broken down into three parts:
 * 1. Find the minimum number of insertions in the substring str[l+1,…….h].
 * 2. Find the minimum number of insertions in the substring str[l…….h-1].
 * 3. Find the minimum number of insertions in the substring str[l+1……h-1].
 * 
 * minInsertions(str[l+1…..h-1]) if str[l] is equal to str[h]
 * min(minInsertions(str[l…..h-1]), minInsertions(str[l+1…..h])) + 1 otherwise
 */
//@formatter:on
public class MinimumInsertionsPalindrome {
	public static int minInsertionsRecursive(char[] str, int low, int high) {
		if (low > high)
			return Integer.MAX_VALUE;
		if (low == high)
			return 0;
		if (low == high - 1) {
			if (str[low] == str[high]) {
				return 0;
			} else {
				return 1;
			}
		}
		if (str[low] == str[high]) {
			return minInsertionsRecursive(str, low + 1, high - 1);
		} else {
			return Math.min(minInsertionsRecursive(str, low + 1, high),
					minInsertionsRecursive(str, low, high - 1)) + 1;
		}
	}

	// Dynamic Programming version
	// Time complexity: O(N^2)
	// Auxiliary Space: O(N^2)
	public static int minInsertionsDP(char[] str) {
		int n = str.length;
		int table[][] = new int[n][n];
		for (int gap = 1; gap < n; gap++) {

			for (int high = gap, low = 0; high < n; high++, low++) {

				if (str[low] == str[high]) {
					table[low][high] = table[low + 1][high - 1];
				} else {
					table[low][high] = Math.min(table[low + 1][high],
							table[low][high - 1]) + 1;
				}
			}
		}
		return table[0][n - 1];
	}

	private static char[] reverse(char[] str) {
		char[] a = new char[str.length];
		for (int i = 0; i < a.length; i++) {
			a[str.length - i - 1] = str[i];
		}
		return a;
	}

	public static int minInsertionsWithLCS(String str) {
		int n = str.length();
		char[] A = str.toCharArray();
		char[] B = reverse(A);
		int[][] L = new int[n+1][n+1];
		for(int i=0; i <= n;i++){
			for(int j=0; j <= n; j++){
				if(i==0 || j==0){
					L[i][j] =0;
				}else if(A[i-1]==B[j-1]){
					L[i][j] = L[i-1][j-1]+1;
				}else{
					L[i][j] = Math.max(L[i][j-1],L[i-1][j]);
				}
			}
		}
		int LCS = L[n][n];
		return n - LCS;
	}

	public static void main(String[] args) {
		String s = "abcde";
		System.out.printf("Min insertions for \"%s\" is  %d \n", s,
				minInsertionsRecursive(s.toCharArray(), 0, s.length() - 1));
		s = "bcd";
		System.out.printf("Min insertions for \"%s\" is  %d \n", s,
				minInsertionsRecursive(s.toCharArray(), 0, s.length() - 1));

		s = "gee";
		System.out.printf("Min insertions for \"%s\" is  %d \n", s,
				minInsertionsRecursive(s.toCharArray(), 0, s.length() - 1));

		
		s = "abcde";
		System.out.printf("Min insertions for \"%s\" is  %d \n", s,
				minInsertionsDP(s.toCharArray()));
		s = "bcd";
		System.out.printf("Min insertions for \"%s\" is  %d \n", s,
				minInsertionsDP(s.toCharArray()));

		s = "gee";
		System.out.printf("Min insertions for \"%s\" is  %d \n", s,
				minInsertionsDP(s.toCharArray()));
		
		s = "abcde";
		System.out.printf("Min insertions for \"%s\" is  %d \n", s,
				minInsertionsWithLCS(s));
		s = "bcd";
		System.out.printf("Min insertions for \"%s\" is  %d \n", s,
				minInsertionsWithLCS(s));

		s = "gee";
		System.out.printf("Min insertions for \"%s\" is  %d \n", s,
				minInsertionsWithLCS(s));
	}

}
