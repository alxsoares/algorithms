package alex.questions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class ReverseandAdd {
	public static int reverse(int num) {
		int r = 0;
		while (num > 0) {
			r = r * 10 + (num % 10);
			num = num / 10;
		}
		return r;
	}

	static void reverse(char[] a) {
		for (int i = 0; i < a.length / 2; i++) {
			char aux = a[i];
			a[i] = a[a.length - i - 1];
			a[a.length - i - 1] = aux;
		}
	}

	static BigInteger reverse(BigInteger number) {
		char[] k = number.toString().toCharArray();
		reverse(k);
		return new BigInteger(new String(k));
	}

	static boolean isPal(BigInteger number) {
		return number.equals(reverse(number));
	}

	public static void printfNumberOfIterations(long number) {
		BigInteger n = BigInteger.valueOf(number);
		int iterations = 0;
		// not cheking number of iterations < 1000
		while (!isPal(n)) {
			n = n.add(reverse(n));
			iterations++;
		}
		System.out.printf("%d %s\n", iterations, n.toString());
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println("Expected file name as argument.");
			return;
		}
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				printfNumberOfIterations(Long.valueOf(line.trim()));
			}
		} finally {
			reader.close();
		}
	}

}
