package alex.algorithms;

public class Calc51 {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 4, 4, 4,
				4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };
		System.out.println(calc51(arr));
		int[] arr2 = { 1, 1, 2, 2, 2, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1 };
		System.out.println(calc51(arr2));
		int[] arr3 = { 8, 1, 8, 1, 8, 3, 8, 3, 8, 3, 8, 2, 8, 2, 8, 2, 8, 1, 8,
				1, 8, 3, 8, 1, 8, 1, 8, 2, 8, 1, 8, 1, 8, 1, 8, 1, 1, 8, 2, 8,
				2, 8 };
		System.out.println(calc51(arr3));
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

}
