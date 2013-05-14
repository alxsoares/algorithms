package alex.algorithms.math.projecteuler.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Euler024 {

	public static void main(String[] args) {

		// fill the array with the values 9!...0!
		int[] base = new int[10];
		for (int i = 10 - 1; i >= 0; i--) {
			base[10 - 1 - i] = factorial(i);
		}
		System.out.println(Arrays.toString(base));

		// find the digits of the factorial number n=999999
		// to do that, we look how many times a factor i! is in n, starting at
		// 9!
		int[] fac_digits = new int[10];
		for (int i = 10 - 1, n = 999999; i >= 0; i--) {
			int remain = n % base[10 - 1 - i];
			fac_digits[10 - 1 - i] = (n - remain) / base[10 - 1 - i];
			n = remain;
		}
		System.out.println("F(999999): " + Arrays.toString(fac_digits));

		// fill an array with the permutation numbers 0...9
		List<Integer> numbers = new ArrayList<Integer>(10);
		for (int i = 0; i < 10; i++) {
			numbers.add(i);
		}

		StringBuilder permutation = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			permutation.append(numbers.remove(fac_digits[i]));
		}
		System.out.println("The 10^6 permutation is " + permutation);

	}

	public static int factorial(int n) {
		if (n == 0)
			return 1;
		int val = factorial(n - 1) * n;
		return val;
	}

}
