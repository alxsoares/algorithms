package topcoder.alex;

import java.util.Arrays;

public class TheProgrammingContestDivTwo {
	public int[] find(int T, int[] requiredTime) {
		int n = requiredTime.length;
		Arrays.sort(requiredTime, 0, n);

		int t = 0;
		int score = 0, penalty = 0;

		int i = 0;
		while ((i < n) && (t + requiredTime[i] <= T)) {
			score++;
			t += requiredTime[i];
			penalty += t;
			i++;
		}
		return new int[] { score, penalty };
	}

	public static void main(String[] args) {
		int[] a = new TheProgrammingContestDivTwo()
				.find(47, new int[] { 8, 5 });
		System.out.printf("%d %d \n", a[0], a[1]);
	}

}
