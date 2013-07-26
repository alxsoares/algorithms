package alex.acm;

import java.io.PrintWriter;
import java.util.Scanner;

public class UVA294 {
	public static int divisorCount1(int n) {
		// a counter for the number of divisors
		int count = 0;

		// loop through 1..n
		for (int i = 1; i <= n; i++)
			if (n % i == 0) // if the remainder of n divided by i is 0
				count++;    // then i is a divisor of n

		// return the number of divisors
		return count;
	}

	public static int divisorCount2(int n) {
		// a counter for the number of divisors
		// intially 1 because n is a divisor of n
		int count = 1;

		// loop through 1..n/2
		for (int i = 1; i <= n / 2; i++)
			if (n % i == 0) // if the remainder of n divided by i is 0
				count++;    // then i is a divisor of n

		// return the number of divisors
		return count;
	}

	public static int divisorCount3(int n) {
		if (n == 1)    // 1 is a tricky number,
			return 1;  // so we'll handle it separately

		// a counter for the number of divisors
		int count = 0;

		// save the square root to avoid re-computation
		int sqrt = (int)Math.sqrt(n);

		// loop through 1..sqrt(n)
		for (int i = 1; i <= sqrt; i++)
			if (n % i == 0) // if the remainder of n divided by i is 0
				count += 2; // then i and n/i are divisors of n

		// if n is a square number, then
		// we counted sqrt(n) twice
		if (sqrt * sqrt == n)
			count--; // so we fix the count

		// return the number of divisors
		return count;
	}

	public static int divisorCount(int n) {
		// a counter for the number of divisors
		// intially 1 (the multiplication identity)
		int count = 1;

		// save the square root to avoid re-computation
		int sqrt = (int)Math.sqrt(n);

		// loop through 2 and the odd numbers up to sqrt(n)
		for (int i = 2; i <= sqrt; i = (i == 2 ? 3 : i + 2)) {

			// a counter for the power of the
			// current number in the prime factorization
			int pow = 0;

			// while i is in n's prime factorization
			while (n % i == 0) {
				pow++;  // increment the power count
				n /= i; // remove one i from the prime factorization of n
			}

			// if there were any i's in n's prime factorization
			if (pow != 0) {
				// change the divisor count according to our formula
				count *= pow + 1;

				// recompute the square root, since we've changed n
				// (a little optimization)
				sqrt = (int)Math.sqrt(n);
			}
		}

		// if we've still not removed all factors from n,
		// then there is one prime factor left
		if (n != 1)
			// change the divisor count according to our formula
			// (the power of the last prime is 1)
			count *= 1 + 1;

		// return the number of divisors
		return count;
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);

		int tests = in.nextInt(); // read the number of tests

		// for each test
		for (int test = 0; test < tests; test++) {


			int L = in.nextInt(),    // read L
				U = in.nextInt(),    // read U
				maxDivisorCount = 0, // the maximum divisor count found
				maxNumber = 0;       // the number with the maximum divisor count

			// loop through the numbers
			for (int i = L; i <= U; i++) {

				// the divisor count of the current number
				// the divisorCount function calculates it for us
				int currentDivisorCount = divisorCount(i);

				// if the current divisor count is larger than
				// the largest divisor count (a contradiction),
				// then the current divisor count is the
				// largest divisor count
				if (currentDivisorCount > maxDivisorCount) {

					// update appropriate variables
					maxDivisorCount = currentDivisorCount;
					maxNumber = i;
				}
			}

			// output the result in the correct format
			out.printf("Between %d and %d, %d has a maximum of %d divisors.\n", L, U, maxNumber, maxDivisorCount);
		}
	}
}