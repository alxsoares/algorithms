package alex.algorithms.arrays;

public class RotateKTimes {
	private static void rotateLeftKTimes(int[] array, int k) {
		reverse(array, 0, array.length - 1);
		printArray(array);
		reverse(array, 0, array.length - k - 1);
		printArray(array);
		reverse(array, array.length - k, array.length - 1);
	}

	private static void reverse(int[] array, int i, int j) {
		while (i < j) {
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}
	}

	private static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + ",");
		System.out.println();

	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		printArray(array);
		rotateLeftKTimes(array, 5);
		printArray(array);

	}

}
