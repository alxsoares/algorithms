package alex.algorithms.math.projecteuler.level2;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Euler032 {

	public static void main(String[] args) {
		long total = 0;
		for (int i = 1; i <= 10000; i++) {
			int r = (int) Math.sqrt(i);
			for (int p = 2; p <= r; p++) {
				if (i % p == 0) {
					int m = p;
					int n = i / p;
					String s = String.valueOf(m) + String.valueOf(n)
							+ String.valueOf(i);
					if (s.length() == 9) {
						char[] a = s.toCharArray();
						Arrays.sort(a);
						if ("123456789".equals(new String(a))) {
							total += i;
							break;

						}
					}
				}
			}
		}
		System.out.printf("%d\n", total);
	}

}
