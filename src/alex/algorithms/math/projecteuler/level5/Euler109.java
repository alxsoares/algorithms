package alex.algorithms.math.projecteuler.level5;

import java.util.ArrayList;
import java.util.List;

public class Euler109 {

	public static void main(String[] args) {
		int limit = 100;
		int result = 0;

		List<Integer> scores = new ArrayList<Integer>();

		// build all possible single dart scores
		for (int i = 1; i <= 20; i++) {
			scores.add(i);
			scores.add(2 * i);
			scores.add(3 * i);
		}
		scores.add(25);
		scores.add(50);

		// make all the possible doubles
		List<Integer> doubles = new ArrayList<Integer>();
		for (int i = 1; i <= 20; i++) {
			doubles.add(2 * i);
		}
		doubles.add(25 * 2);

		// Count all miss, miss, double
		for (int third : doubles) {
			if (third < limit)
				result++;
		}

		// count all miss, hit, double
		for (int i = 0; i < scores.size(); i++) {
			for (int third : doubles) {

				if (scores.get(i) + third < limit)
					result++;
			}
		}

		// count all hit, hit, double
		for (int i = 0; i < scores.size(); i++) {
			for (int j = i; j < scores.size(); j++) {
				for (int third : doubles) {
					if (scores.get(i) + scores.get(j) + third < limit)
						result++;
				}
			}
		}
		System.out.println(result);

	}

}
