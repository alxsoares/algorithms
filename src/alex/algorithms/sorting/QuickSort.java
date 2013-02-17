package alex.algorithms.sorting;

public class QuickSort {

	public static void swap(int[] arr, int i, int j) {
		int aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}

	public static void quickSort(int[] dados, int start, int end) {
		if (start < end) {
			int middle = (start + end) / 2;
			int first = start;
			int lower = start + 1;
			int upper = end;
			swap(dados, first, middle);
			int pivot = dados[first];
			while (lower < upper) {
				while (dados[lower] < pivot && lower < end)
					lower++;
				while (dados[upper] > pivot && upper > start)
					upper--;
				// change need to be done.
				if (lower < upper) {
					swap(dados, lower++, upper--);
				} else {
					lower++;// avoid infinite loop
				}
			}
			// puts pivot in the right position
			swap(dados, first, upper);
			quickSort(dados, start, upper - 1);
			quickSort(dados, upper + 1, end);
		}
	}

	public static void quicksortOptimized(int[] data, int left, int right) {
		int pivotValue = data[(left + right) / 2];
		int i = left;
		int j = right;
		while (i <= j) {
			// Find leftmost value greater than or equal to the pivot.
			while (data[i] < pivotValue)
				i++;
			// Find rightmost value less than or equal to the pivot.
			while (data[j] > pivotValue)
				j--;
			// If the values are in the wrong order, swap them.
			if (i <= j) {
				swap(data, i, j);
				i++;
				j--;
			}
		}
		// Apply the algorithm to the partitions we made, if any.
		if (left < j) {
			quicksortOptimized(data, left, j);
		}
		if (i < right) {
			quicksortOptimized(data, i, right);
		}
	}

	public static void main(String[] args) {
		int arr[] = { 70, 1, 2, 3, 4, 5, 6, 80, 8, 9, 1, -1, -10 , -1000};
		quicksortOptimized(arr, 0, arr.length - 1);
		String comma = "";
		for (int i = 0; i < arr.length; i++) {
			int j = arr[i];
			System.out.print(comma + j);
			comma = ",";
		}
	}

}
