package alex.algorithms.strings;

public class StringPermutation {

	public static void swap(char a[], int s, int d) {
		char aux = a[d];
		a[d] = a[s];
		a[s] = aux;
	}

	public static void permute(char a[], int pos) {
		if (a == null || a.length <= 1)
			return;
		if (pos == a.length) {
			System.out.printf("%s\n", new String(a));
			return;
		}
		for (int j = pos; j < a.length; j++) {
			swap(a, pos, j);
			permute(a, pos + 1);
			swap(a, pos, j);
		}
	}

	public static boolean getNext(char value[]) {
		int i = value.length - 1;
		while (i > 0 && value[i - 1] >= value[i])
			i--;
		if (i <= 0)
			return false;
		int j = value.length;
		while (j > 0 && value[j - 1] <= value[i - 1])
			j--;
		if (j <= 0)
			return false;
		swap(value, i - 1, j - 1);
		i++;
		j = value.length;
		while (i < j) {
			swap(value, i - 1, j - 1);
			i++;
			j--;
		}
		return true;
	}

	public static void heapPermute(char values[], int n) {
		if (n == 1) {
			System.out.println(new String(values));
		} else {
			for (int i = 0; i < n; i++) {
				heapPermute(values, n - 1);
				if (n % 2 == 1) {
					swap(values, 0, n - 1);
				} else {
					swap(values, i, n - 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		// permute("1234".toCharArray(), 0);

		char[] values = "1234".toCharArray();
		// for(int i=0; getNext(values);i++){
		// System.out.println(new String(values));
		// }
		heapPermute(values, values.length);
	}

}
