package alex.acm;

import java.io.IOException;
import java.util.StringTokenizer;

public class PrimeLand {
	int MAX = 50000;
	int primes[] = new int[MAX];
	int numbers[] = new int[MAX + 1];
	int nprimes = 0;

	void calcPrimes() {
		for (int i = 2; i <= MAX; i++) {
			if (numbers[i] == 0) {
				primes[nprimes++] = i;
				for (int j = 2 * i; j <= MAX; j += i) {
					numbers[j]++;
				}
			}
		}
	}

	void printResult(int n) {
		n--;
		String result = "";
		for (int i = 0; i < nprimes && n > 1; i++) {
			int exp = 0;
			while (n % primes[i] == 0) {
				exp++;
				n = n / primes[i];
			}
			if (exp > 0) {
				result = primes[i] + " " + exp + " " + result;
			}
		}
		System.out.println(result.trim());

	}

	static String ReadLn(int maxLg) { // utility function to read from stdin

		byte lin[] = new byte[maxLg];

		int lg = 0, car = -1;

		try {

			while (lg < maxLg) {

				car = System.

				in.read();

				if ((car < 0) || (car == '\n')) {
					break;
				}
				lin[lg++] += car;

			}

		}

		catch (IOException e) {

			return (null);
		}

		if ((car < 0) && (lg == 0)) {
			return (null); // eof
		}

		return (new String(lin, 0, lg));
	}

	public static void main(String[] args) {
		PrimeLand main = new PrimeLand();
		main.calcPrimes();
		String str = "";
		while ((str = ReadLn(10000)) != null) {
			if ("0".equals(str.trim()))
				break;
			StringTokenizer st = new StringTokenizer(str);
			if (st.countTokens() == 0)
				break;
			int n = 1;
			int e, p;
			while (st.hasMoreTokens()) {
				p = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				n = n * (int) Math.pow((double) p, e);
			}
			main.printResult(n);
		}
	}
}
