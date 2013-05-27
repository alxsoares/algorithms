package alex.algorithms.math.projecteuler;

public class Euler149 {

	static int M = 1000000;
	static int K2 = 2000;
	static int s[] = new int[4000001];

	static long kadane(int start, int step, int end, long ans) {
		long sum = 0;
		for (int j = start; j < end; j += step) {
			sum = sum + s[j];
			if (sum < 0)
				sum = 0;
			else if (sum > ans)
				ans = sum;
		}
		return ans;
	}

	static int cube(int x) {
		return x * x * x;
	}

	public static void main(String[] args) {
		long ans = 0;
		for (int i = 0; i < 55; ++i)
			s[i] = (int) (((100003 - 200003L * (i + 1) + 300007L * cube(i + 1)) % M) - 500000);
		for (int i = 55; i < 4000000; ++i)
			s[i] = ((s[i - 24] + s[i - 55] + M) % M) - 500000;
		for (int i = 0; i < 2000; ++i) {
			ans = kadane(K2 * i, 1, K2 * (i + 1), ans); // ROWS
			ans = kadane(i, K2, M << 2, ans); // COLUMNS
			ans = kadane(K2 * i, 2001, M << 2, ans); // diagonal
			ans = kadane(i, 2001, M << 2, ans); // diagonal
			ans = kadane(i, 1999, M << 2, ans); // anti-diagonal
			ans = kadane(K2 * (i + 1) - 1, 1999, M << 2, ans); // anti-diagonal
		}
		System.out.printf("%d\n", ans);
	}

}
