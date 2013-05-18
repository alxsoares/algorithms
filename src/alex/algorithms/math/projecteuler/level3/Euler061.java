package alex.algorithms.math.projecteuler.level3;

import java.util.ArrayList;
import java.util.List;

public class Euler061 {
	static int[] solution = new int[6];
	static Integer[][] numbers = new Integer[6][];

	public static void main(String[] args) {
		int result = 0;

		for (int i = 0; i < 6; i++) {
			numbers[i] = generateNumbers(i);
		}

		for (int i = 0; i < numbers[5].length; i++) {
			solution[5] = numbers[5][i];
			if (next(5, 1))
				break;
		}
		for (int i = 0; i < solution.length; i++) {
			result += solution[i];
		}
		System.out.printf("%d\n", result);
	}

	static boolean next(int last, int length) {
		for (int i = 0; i < solution.length; i++) {
			if (solution[i] != 0)
				continue;
			for (int j = 0; j < numbers[i].length; j++) {

				boolean unique = true;
				for (int k = 0; k < solution.length; k++) {
					if (numbers[i][j] == solution[k]) {
						unique = false;
						break;
					}
				}

				if (unique && ((numbers[i][j] / 100) == (solution[last] % 100))) {
					solution[i] = numbers[i][j];
					if (length == 5) {
						if (solution[5] / 100 == solution[i] % 100)
							return true;
					}
					if (next(i, length + 1))
						return true;
				}
			}
			solution[i] = 0;
		}
		return false;
	}
	static Integer[] generateNumbers(int type) {

        List<Integer> numbers = new ArrayList<Integer>();

        int n = 0;
        int number = 0;

        while (number < 10000) {
            n++;
            switch (type) {

                case 0:
                    //Triangle numbers
                    number = n * (n + 1) / 2;
                    break;
                case 1:
                    // Square numbers
                    number = n * n;
                    break;
                case 2:
                    // Pentagonal numbers
                    number = n * (3 * n - 1) / 2;
                    break;
                case 3:
                    //Hexagonal numbers
                    number = n * (2*n - 1);
                    break;
                case 4:
                    //Heptagonal numbers
                    number = n * (5 * n - 3) / 2;
                    break;
                case 5:
                    //Octagonal numbers
                    number = n * (3 * n - 2);
                    break;
            }
            if (number > 999)
                numbers.add(number);
        }

        return numbers.toArray(new Integer[0]);
    }


}
