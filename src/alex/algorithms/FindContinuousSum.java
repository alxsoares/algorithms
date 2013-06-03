package alex.algorithms;

public class FindContinuousSum {
	public static void findContinuosSum(int sum) {
		if (sum < 3)
			return;
		int small = 1;
		int mid = (1 + sum) / 2;
		int big = 2;
		int currentSum = small + big;
		while (small < mid) {
			if (currentSum == sum) {
				printContinuousSum(small, big);
				currentSum-=small;
				small++;
			}
			if (currentSum > sum) {
				currentSum -= small;
				small++;
			} else if (currentSum < sum) {
				big++;
				currentSum += big;
			}
		}
	}

	private static void printContinuousSum(int small, int big) {
		for (int i = small; i <= big; i++) {
			System.out.printf("%d ", i);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		findContinuosSum(9);
		System.out.println();
		findContinuosSum(15);
		System.out.println();
		findContinuosSum(111);
	}

}
