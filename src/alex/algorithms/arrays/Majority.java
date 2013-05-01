package alex.algorithms.arrays;

public class Majority {

	public static int majority(int[] array) {
		int majIndex = 0;
		int count = 1;
		for (int i = 1; i < array.length; i++) {
			if (array[majIndex] == array[i]) {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				majIndex = i;
				count = 1;
			}
		}
		count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == array[majIndex]) {
				count++;
			}
		}
		if (count >= array.length / 2) {
			return majIndex;
		}
		return -1;
	}

	public static void main(String[] args) {
		int a[] = { 1, 3, 3, 1, 2, 2,1,2,1,1 };
		int index = majority(a);
		if (index > 0) {
			System.out.printf("%d %d\n", index, a[index]);
		}
	}

}
