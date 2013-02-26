package topcoder.alex.misc;

public class CountTwos {

	public static int count2R(int n) {
		if (n < 2)
			return 0;
		int power = 1;
		while (power * 10 < n)
			power *= 10;
		int q = n / power;
		int r = n % power;
		int p2 = 0;
		if (q > 2)
			p2 += power;
		else if (q == 2)
			p2 += r + 1;

		int np2 = q * count2R(power - 1) + count2R(r);

		return np2 + p2;

	}

	public static void main(String[] args) {
		System.out.printf("Number of 2s from 0 to %d => %d \n", 1000,
				count2R(888));
	}

}
