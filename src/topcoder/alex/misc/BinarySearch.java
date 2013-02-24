package topcoder.alex.misc;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
	public static int binarySearch(int array[], int target, int begin, int end) {
		if (begin > end)
			throw new RuntimeException("Target no present in the array.");
		if (begin == end) {
			if (array[begin] == target)
				return begin;
			throw new RuntimeException("Target no present in the array.");
		}
		int center = (begin + end) / 2;
		if (array[center] == target)
			return center;
		if (target < array[center])
			return binarySearch(array, target, begin, center - 1);
		return binarySearch(array, target, center + 1, end);
	}

	// 15 16 19 20 25 1 3 ->4 5 7 10 14
	public static int binarySearchShiftedArray(int array[], int target,
			int begin, int end) {
		if (begin > end)
			throw new RuntimeException("Target no present in the array.");
		if (begin == end) {
			if (array[begin] == target)
				return begin;
			throw new RuntimeException("Target no present in the array.");
		}
		int center = (begin + end) / 2;
		if (array[center] == target)
			return center;
		if (array[begin] <= array[center]) {
			if (target > array[center]) {
				begin = center + 1;
			} else if (target > array[begin]) {
				end = center - 1;
			} else {
				begin = center + 1;
			}
		} else if (target < array[center]) {
			end = center - 1;
		} else if (target < array[end]) {
			begin = center + 1;
		} else {
			end = center - 1;
		}
		return binarySearchShiftedArray(array, target, begin, end);
	}

	public static int iterBinarySearch(int array[], int target) {
		int begin = 0;
		int end = array.length - 1;
		if (begin > end)
			throw new RuntimeException("Target not present.");
		while (true) {
			if (begin > end)
				throw new RuntimeException("Target not present.");
			if (begin == end && array[begin] == target)
				return begin;
			int center = (begin + end) / 2;
			if (array[center] == target)
				return center;
			else if (array[center] > target)
				end = center - 1;
			else
				begin = center + 1;
		}
	}

	public static int searchString(String[] words, String target, int begin,
			int end) {
		while (begin <= end) {
			while (begin <= end && "".equals(words[end])) {
				end--;
			}
			if (begin > end)
				return -1;
			int m = (begin + end) / 2;
			while ("".equals(words[m]))
				m++;// sempre tera algum diferente ate chegar no end
			int r = target.compareTo(words[m]);
			if (r == 0) {
				return m;
			}
			if (r < 0) {
				end = m - 1;
			} else {
				begin = m + 1;
			}
		}
		return -1;
	}
	//O(m+n)
	public static boolean find(int mat[][], int el, int m, int n) {
		// {1,2,3}
		// {4,5,6}
		// {7,8,9}
		int row = 0;
		int column = n - 1;
		while (row < m && column >= 0) {
			if (mat[row][column] == el) {
				System.out.printf("%d %d\n", row, column);
				return true;
			}
			if (el < mat[row][column]) {// esta nesta lina
				column--;
			} else {
				row++;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		int array[] = new int[200];
		for (int i = 1; i < array.length; i++) {
			array[i] = Math.abs(rand.nextInt()) % 300;
		}
		array[0] = 14;
		Arrays.sort(array);
		int index = iterBinarySearch(array, 14);
		System.out.printf("%d %d\n", index, array[index]);
		int arr[] = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		index = binarySearchShiftedArray(arr, 25, 0, arr.length - 1);
		System.out.printf("%d %d\n", index, arr[index]);

		System.out.println();

		String[] words = { "alex", "", "", "", "busca", "", "", "curva", "",
				"", "dado", "", "" };
		index = searchString(words, "alex", 0, words.length - 1);
		System.out.printf("%d %s\n", index, words[index]);
		int mat[][] = { {1,2,3},
					{4,5,6},
					{7,8,9}};
		find(mat, 8, 3, 3);
	}

}
