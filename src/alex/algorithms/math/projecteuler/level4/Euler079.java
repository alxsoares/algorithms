package alex.algorithms.math.projecteuler.level4;

public class Euler079 {

	static boolean precedes[][] = new boolean[10][10];
	static boolean used[] = new boolean[10];
	static int numPreceding[] = new int[10];
	static char solution[] = new char[20];

	public static void main(String[] args) {
		
		int numbers[] = { 319, 680, 180, 690, 129, 620, 762, 689, 762, 318,
				368, 710, 720, 710, 629, 168, 160, 689, 716, 731, 736, 729,
				316, 729, 729, 710, 769, 290, 719, 680, 318, 389, 162, 289,
				162, 718, 729, 319, 790, 680, 890, 362, 319, 760, 316, 729,
				380, 319, 728, 716 };
		// read input and build precedence graph
		for (int i = 0; i < numbers.length; i++) {
			int n = numbers[i];
			int c = n % 10;
			n /= 10;
			int b = n % 10;
			n /= 10;
			int a = n % 10;
			precedes[a][b] = true;
			precedes[a][c] = true;
			precedes[b][c] = true;
			used[a] = true;
			used[b] = true;
			used[c] = true;
		}
		// calculate transitive closure of precedence graph
		for (int k = 0; k < 10; k++) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (precedes[i][k] && precedes[k][j])
						precedes[i][j] = true;
				}
			}
		}
		// calculate number of preceding digits for every digit
		for (int i = 0; i < 10; i++) {
			if (!used[i])
				continue;
			for (int j = 0; j < 10; j++) {
				numPreceding[i] += precedes[j][i] ? 1 : 0;
			}
			solution[numPreceding[i]] = (char) ('0' + i);
		}
		System.out.println(solution);
	}

}
