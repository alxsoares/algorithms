package alex.algorithms.arrays;

public class PeakElement {

	public static int findPeak(int array[], int low, int high) {
		int mid = (low + high) / 2;
		
		if (mid == 0 && array[mid] >= array[mid + 1])
			return mid;
		
		if (mid == array.length - 1 && array[mid] >= array[mid - 1])
			return mid;
		
		if (mid > 0
				&& (array[mid - 1] <= array[mid] && array[mid] >= array[mid + 1]))
			return mid;
		
		if (mid > 0 && array[mid - 1] > array[mid])
			return findPeak(array, low, mid - 1);
		
		return findPeak(array, mid + 1, high);
	}

	public static void main(String[] args) {
		int array[] = { 1, 3, 20, 4, 1, 0 };
		int index = findPeak(array, 0, array.length);
		System.out.printf("%d\n", array[index]);
	}

}
