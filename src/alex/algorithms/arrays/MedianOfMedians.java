package alex.algorithms.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedianOfMedians {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] a = { 88, 30, 11, 17, 22, 16, 39, 8, 31, 55, 29, 63, 77, 69,
				99, 90, 81, 2, 20, 53, 62, 5, 88, 33, 44, 6 };

		int n = 22;
		int res = select(a, n);

		System.out.println(res);

		Arrays.sort(a);
		System.out.println(a[n - 1]);

	}

	private static int select(Integer[] a, int k)

	{
		if (a.length <= 10)

		{
			Arrays.sort(a);

			return a[k - 1];

		}
		int n = a.length;

		// partition L into subsets S[i] of five elements each
		// (there will be n/5 subsets total).

		List<Integer[]> list = new ArrayList<Integer[]>();

		int cnt = 0;

		int m = n / 5;
		for (int i = 0; i < m; i++) {

			Integer[] arr = new Integer[5];

			for (int j = 0; j < 5; j++) {

				if (cnt == n)
					break;
				arr[j] = a[cnt++];

			}
			Arrays.sort(arr);

			list.add(arr);
		}

		Integer[] x = new Integer[m];

		for (int i = 0; i < m; i++) {

			x[i] = list.get(i)[2];

		}

		int v = x[0];

		if (x.length > 2) {

			v = (x.length % 2 == 0) ? x[x.length % 2 - 1] : x[x.length / 2];

		}

		// partition L into L1<M, L2=M, L3>M

		Integer[] l = partition_l(a, v);

		Integer[] r = partition_r(a, v);

		if (k == l.length + 1) {

			return v;
		} else if (k <= l.length) {

			return select(l, k);

		} else {
			return select(r, k - l.length - 1);

		}

	}

	private static Integer[] partition_l(Integer[] a, int pivot) {

		if (a.length == 0)

			return a;
		int j = 0;

		Integer[] b = new Integer[a.length];

		for (int i = 0; i < a.length; i++) {

			if (a[i] < pivot) {

				b[j++] = a[i];

			}
		}
		Integer[] l = new Integer[j];

		System.arraycopy(b, 0, l, 0, j);

		return l;
	}

	private static Integer[] partition_r(Integer[] a, int pivot) {

		if (a.length == 0)

			return a;
		int j = 0;

		Integer[] b = new Integer[a.length];

		for (int i = 0; i < a.length; i++) {

			if (a[i] > pivot) {

				b[j++] = a[i];

			}
		}
		Integer[] r = new Integer[j];

		System.arraycopy(b, 0, r, 0, j);

		return r;
	}

}
