package alex.algorithms.math.projecteuler.level2;

public class Euler030 {

	public static void main(String[] args) {
		int sum = 0;

		for (int i = 2; i < 354294; i++) {
			String number = Integer.toString(i);
			int charSum = 0;
			for (char c : number.toCharArray()) {
				charSum += Math.pow(Integer.parseInt(Character.toString(c)), 5);
			}
			if (charSum == i)
				sum += i;
		}

		System.out.println(sum);
	}

}
