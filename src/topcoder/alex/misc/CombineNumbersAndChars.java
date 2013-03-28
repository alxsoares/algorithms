package topcoder.alex.misc;

public class CombineNumbersAndChars {
	public static void change(int begin, int end, char[] a) {
		int n = end - begin + 1;
		int mid = (begin + end) / 2;
		if (n > 3) {
			if (n % 4 == 0) {
				for (int i = 0; i < n / 4; i++) {
					swap(a, i + begin + n / 4, i + begin + n / 2);
				}
				change(begin, mid, a);
				change(mid + 1, end, a);
			} else if (n % 4 == 2) {
				char c = a[mid];
				for (int i = mid; i < end - 1; i++) {
					a[i] = a[i + 1];
				}
				a[end - 1] = c;
				change(begin, end - 2, a);// n%4==0 now
			}
		}
	}

	public static void swap(char a[], int x, int y) {
		char aux = a[x];
		a[x] = a[y];
		a[y] = aux;
	}

	public static void main(String[] args) {
		char[] array = "1234567890abcdefghij".toCharArray();
		change(0, array.length - 1, array);
		System.out.println(new String(array));
	}

}
