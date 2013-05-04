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

	public static int getMajorityWithPartition(int array[]) {
		int start = 0;
		int end = array.length - 1;
		final int middle = (start + end) / 2;
		int index = partition(array, start, end);
		while (index != middle) {
			if (index > middle) {
				end = index - 1;
				index = partition(array, start, end);
			} else {
				start = index + 1;
				index = partition(array, start, end);
			}
		}
		int res = array[index];
		if (!checkMajority(array, res)) {
			throw new IllegalArgumentException("Majority doesn't exist.");
		}
		return res;
	}

	private static boolean checkMajority(int[] array, int res) {
		int number = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == res) {
				number++;
			}
		}
		System.out.println("Array size is "+ array.length);
		System.out.println("Number is "+number);
		return (array.length < 2 * number);
	}

	private static int partition(int[] array, int start, int end) {
		int pivot = (start + end) / 2;
		int first = start;
		int lower = start + 1;
		int upper = end;
		swap(array, pivot, first);
		while (lower < upper) {
			while (array[lower] < array[pivot] && lower < upper) {
				lower++;
			}
			while (array[upper] > array[pivot] && lower < upper) {
				upper--;
			}
			if (lower < upper) {
				swap(array, lower++, upper--);
			} else {
				lower++;
			}
		}
		swap(array, pivot, upper);
		return lower;
	}

	public static void swap(int array[], int source, int dest) {
		int aux = array[source];
		array[source] = array[dest];
		array[dest] = aux;
	}

	public static void main(String[] args) {
		int a[] = { 1, 3, 3, 1, 2, 2, 1, 2, 1, 1 };
		int index = majority(a);
		if (index > 0) {
			System.out.printf("%d %d\n", index, a[index]);
		}
		index = getMajorityWithPartition(new int[]{2,2,2,2,2,2,2,1,3,3,3,3,3,3,3,3,3,3,3,3});
		if (index > 0) {
			System.out.printf("%d\n", index);
		}
	}

}
