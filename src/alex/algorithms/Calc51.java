package alex.algorithms;

import java.util.Arrays;

public class Calc51 {
	// int[] arr = { 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 4, 4, 4,
	// 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };
	// System.out.println(calc51(arr));
	// int[] arr2 = { 1, 1, 2, 2, 2, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1 };
	// System.out.println(calc51(arr2));
	public static void printMajor(int a[]) {
		int majIndex = 0;
		int count = 1;
		for (int i = 1; i < a.length; i++) {
			if (a[majIndex] == a[i]) {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				majIndex = i;
			}
		}
		count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[majIndex] == a[i])
				count++;
		}
		System.out.printf("%d %.4f\n", a[majIndex], (double) count / a.length);
	}

	public static void main(String[] args) {
		int[] arr3 = {1,1,1,1,1,1,1,1,1,1,1,1,2,3,4,3,3,3,3,3,3};
		printMajor(arr3);
	}

	public static void partition(int dados[], int start, int end) {
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

	}

	public static int mostCommon(int array[]) {
		int best = 0;
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (count == 0) {
				best = array[i];
				count = 1;
			} else {
				count += (array[i] == best ? 1 : -1);
			}
		}
		return best;
	}

	static void swap(int arr[], int a, int b) {
		int aux = arr[a];
		arr[a] = arr[b];
		arr[b] = aux;
	}

	static int calc51(int[] conjunto) {
		int num[] = new int[3];
		int rep[] = new int[3];
		int primeiro = 0, segundo = 1, aux = 2;
		num[primeiro] = num[segundo] = num[aux] = conjunto[0];
		rep[primeiro] = rep[segundo] = rep[aux] = 1;
		for (int i = 1; i < conjunto.length; i++) {
			if (conjunto[i] == num[primeiro]) {
				rep[primeiro]++;
			} else if (conjunto[i] == num[segundo]) {
				rep[segundo]++;
			} else if (conjunto[i] == num[aux]) {
				rep[aux]++;
			} else {
				num[aux] = conjunto[i];
				rep[aux] = 1;
			}
			if (rep[primeiro] < rep[segundo]) {
				swap(rep, primeiro, segundo);
				swap(num, primeiro, segundo);
			}
			if (rep[segundo] < rep[aux]) {
				swap(rep, segundo, aux);
				swap(num, segundo, aux);
			}
		}
		int repetitions = 0;
		for (int i = 0; i < conjunto.length; i++) {
			if (conjunto[i] == num[primeiro])
				repetitions++;
		}
		float r = (float) repetitions / conjunto.length;
		if (r >= 0.51)
			return num[primeiro];
		repetitions = 0;
		for (int i = 0; i < conjunto.length; i++) {
			if (conjunto[i] == num[segundo])
				repetitions++;
		}
		r = (float) repetitions / conjunto.length;
		if (r >= 0.51)
			return num[segundo];

		return -1;
	}
	/**
	 * @param args
	 */

}
