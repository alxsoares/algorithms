package topcoder.alex.misc;

public class Silly {

	public static int[] sumOthers(int[] array) {
		int memo[] = new int[array.length];
		int sum = 0;
		for (int i = array.length - 1; i >= 0; i--) {
			memo[i] = sum + array[i];
			sum += array[i];
		}
		sum = 0;
		for (int i = 0; i < array.length - 1; i++) {
			memo[i] = sum + memo[i + 1];
			sum += array[i];
		}
		memo[array.length - 1] = sum;
		return memo;
	}

	public static void main(String[] args) {
		int[] others = { 1, 2, 3, 4 };
		int[] array = sumOthers(others);
		for (int i = 0; i < array.length; i++) {
			System.out.printf("%d ", array[i]);
		}
		System.out.println();
	}

}
