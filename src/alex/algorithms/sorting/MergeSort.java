package alex.algorithms.sorting;

public class MergeSort {
	public static int[] sort(int[] a) {
		if (a.length < 2) {
			return a;
		}
		int mid = (a.length) / 2;
		int[] left = new int[mid];
		int[] right = new int[a.length - mid];
		System.arraycopy(a, 0, left, 0, left.length);
		System.arraycopy(a, mid, right, 0, right.length);
		left = sort(left);
		right = sort(right);
		return merge(left, right);
	}


	public static void swap(int[] arr, int i, int j) {
		int aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}

	private static int[] merge(int[] left, int[] right) {
		int[] d = new int[left.length + right.length];
		int l = 0;
		int r = 0;
		int dindex = 0;
		while (l < left.length && r < right.length) {
			if (left[l] <= right[r]) {
				d[dindex++] = left[l++];
			} else {
				d[dindex++] = right[r++];
			}
		}
		while (l < left.length) {
			d[dindex++] = left[l++];
		}
		while (r < right.length) {
			d[dindex++] = right[r++];
		}
		return d;
	}

	public static void main(String[] args) {
		int arr[] = { 70, 1, 2, 3, 4, 5, 6, 80, 8, 9, 1, -1, -10, -1, -1000 };
		arr = sort(arr);
		String comma = "";
		for (int i = 0; i < arr.length; i++) {
			int j = arr[i];
			System.out.print(comma + j);
			comma = ",";
		}
	}

}
