package alex.algorithms;

public class SubSetZeroSum {
	public static boolean hasSubSetWithSumZero(int a[]) {
		if (a == null || a.length == 0)
			return false;
		long n = 1 << a.length;
		for (long i = 1; i < n; i++) {
			long set = i;
			int index = 0;
			int sum = 0;
			boolean hasElement = false;
			System.out.println(i);
			while (set > 0) {
				if ((set & 1) > 0) {
					sum += a[index];
					hasElement = true;
				}
				set = set >> 1;
				index++;
			}
			if (sum == 0 && hasElement)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		int a[] = {3,10,2,-3};
		System.out.println(hasSubSetWithSumZero(a));
	}

}
