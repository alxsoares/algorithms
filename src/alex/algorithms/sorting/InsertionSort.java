package alex.algorithms.sorting;

public class InsertionSort {
	public static void sort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int val = array[i];
			for (int j = 0; j < i; j++) {
				if (val < array[j]) {
					System.arraycopy(array, j, array, j + 1, i - j);
					array[j] = val;
					break;
				}
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}

	public static void quickSort(int[] d, int left, int right) {
		int pivot = d[(left+right)/2];
		int i=left;
		int j= right;
		while(i<=j){
			while(pivot > d[i]){
				i++;
			}
			while(pivot < d[j]){
				j--;
			}
			if(i<=j){
				swap(d, i++, j--);
			}
		}
		if(left < j){
			quickSort(d, left, j);
		}
		if(i < right){
			quickSort(d, i, right);
		}
	}

	public static void main(String[] args) {
		int arr[] = { 70, 1, 2, 3, 4, 5, 6, 80, 8, 9, 1, -1, -10,-1,-1000 };
		quickSort(arr, 0, arr.length - 1);
		String comma = "";
		for (int i = 0; i < arr.length; i++) {
			int j = arr[i];
			System.out.print(comma + j);
			comma = ",";
		}
	}

}
