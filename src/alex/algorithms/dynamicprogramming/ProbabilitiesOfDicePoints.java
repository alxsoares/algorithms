package alex.algorithms.dynamicprogramming;

public class ProbabilitiesOfDicePoints {

	public static void printProbability(int number) {
		if (number < 1)
			return;
		int maxValue = 6;
		int prob[][] = new int[2][maxValue * number + 1];
		int flag = 0;
		for (int i = 1; i <= maxValue; i++) {
			prob[flag][i] = 1;
		}
		for (int k = 2; k <= number; k++) {
			for (int i = 0; i < k; i++) {
				prob[1 - flag][i] = 0;
			}
			for (int i = k; i <= maxValue * k; i++) {
				prob[1 - flag][i] = 0;
				for (int j = 1; j <= i && j <= maxValue; j++)
					prob[1 - flag][i] += prob[flag][i - j];
			}
			flag = (flag + 1) % 2;
		}
		double total = Math.pow((double) maxValue, number);
		for (int i = number; i <= maxValue * number; ++i) {
			double ratio = (double) prob[flag][i] / total;
			System.out.printf("%d: %e\n", i, ratio);
		}
		
	}

	public static void main(String[] args) {
		printProbability(2);
	}

}
