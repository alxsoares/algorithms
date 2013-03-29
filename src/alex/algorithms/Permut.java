package alex.algorithms;

public class Permut {
	public void permut(char src[], char dest[], int pos, int size) {
		if (pos < size) {
			for (int i = 0; i < src.length; i++) {
				dest[pos] = src[i];
				permut(src, dest, pos + 1, size);
			}
		} else {
			System.out.println(dest);
		}
	}

	public void permutString(char str[], int n) {
		final int len = str.length;
		if (n == len - 2) {
			System.out.println(str);
			char aux = str[len - 1];
			str[len - 1] = str[len - 2];
			str[len - 2] = aux;
			System.out.println(str);
			str[len - 2] = str[len - 1];
			str[len - 1] = aux;
		} else {
			for (int i = n; i < str.length; i++) {
				char aux = str[i];
				str[i] = str[n];
				str[n] = aux;
				permutString(str, n + 1);
				str[n] = str[i];
				str[i] = aux;
			}
		}
	}

	void print(char[] s, int[] perm, int n) {
		for (int i = 0; i < n; i++)
			System.out.printf("%c", s[perm[i]]);
		System.out.printf("\n");
	}

	void perm(char[] s, int[] stack, int n, int level) {
		if (level == n) {
			print(s, stack, n);
			return;
		}
		for (int i = 0; i < n; i++) {
			stack[level] = i;
			boolean discard = false;
			for (int j = 0; j < level; j++) {
				if (stack[j] == i) {
					discard = true;
					break;
				}
			}
			if (!discard)
				perm(s, stack, n, level + 1);
		}
	}

	public static void main(String[] args) {
		Permut permut = new Permut();
		String src = "0123456";
		// permut.permut(src.toCharArray(),new char[7],0,7);
		// permut.permutString(src.toCharArray(),0);
		permut.perm(src.toCharArray(), new int[src.length()], src.length(), 0);
	}
}