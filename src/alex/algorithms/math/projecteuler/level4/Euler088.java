package alex.algorithms.math.projecteuler.level4;

public class Euler088 {

	static int best[];
	static int flag[];

	public static void main(String[] args) {
		int i, sum = 0;
		best = new int[15001];
		flag = new int[20000];

		for (i = 2; i <= 12000; i++)
			best[i] = 2 * i;
		for (i = 0; i < 20000; i++)
			flag[i] = 0;
		solve(0, 1, 0);
		for (i = 2; i <= 12000; i++) {
			if (flag[best[i]] == 0)
				sum += best[i];
			flag[best[i]] = 1;
		}
		System.out.printf("Result = %d\n" , sum);
	}

	static public void solve(int level, int sum1, int sum2) {
		int i, p;
		for (i = 2;; i++) {
			if (sum1 * i > 30000)
				break;
			p = sum1 - sum2;
			if (sum1 < best[p + level])
				best[p + level] = sum1;
			solve(level + 1, sum1 * i, sum2 + i);
		}
	}

}
