package alex.algorithms.dynamicprogramming;

public class FactorAndFactorials {

	int dp[][] = new int[101][101];

	void calcFactors() {
		dp[2][2] = 1;
		for (int i = 3; i < 101; i++) {
			for (int j = 2; j < i; j++) {
				dp[i][j] = dp[i - 1][j];
			}
			int aux = i;
			for (int j = 2; j <= aux; j++) {
				while (aux % j == 0) {
					dp[i][j]++;
					aux = aux / j;
				}
			}

		}
	}

	void imprime(int n) {
		String number = String.valueOf(n);
		System.out.print(rightJustify(number) + "! =");
		for (int i = 2; i <= n; i++) {
			if (dp[n][i] != 0) {
				String value = String.valueOf(dp[n][i]);
				value = rightJustify(value);
				System.out.print(value);
			}
		}
		System.out.println("");
	}

	private String rightJustify(String value) {
		while (value.length() != 3) {
			value = " " + value;
		}
		return value;
	}

	public static void main(String[] args) {
		FactorAndFactorials factorandfactorials = new FactorAndFactorials();
		factorandfactorials.calcFactors();

		for (int i = 2; i <= 100; i++)
			factorandfactorials.imprime(i);
	}

}
