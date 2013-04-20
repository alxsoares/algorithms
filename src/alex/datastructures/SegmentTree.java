package alex.datastructures;

public class SegmentTree {

	public static int sum(int[] segTree, int start, int end, int queryStart,
			int queryEnd, int index) {
		
		if (queryStart <= start && queryEnd >= end) {
			return segTree[index];
		}
		
		if (end < queryStart || start > queryEnd) {
			return 0;
		}
		
		int mid = start + (end - start) / 2;
		
		return sum(segTree, start, mid, queryStart, queryEnd, 2 * index + 1)
				+ sum(segTree, mid + 1, end, queryStart, queryEnd,
						2 * index + 2);
	}

	public static int getSum(int[] segTree, int n, int queryStart, int queryEnd) {
		if (queryStart < 0 || queryEnd > n - 1 || queryStart > queryEnd) {
			return -1;// Invalid
		}
		return sum(segTree, 0, n - 1, queryStart, queryEnd, 0);
	}
	
	public static void updateValue(int[] segTree, int start, int end, int i,
			int diff, int index) {
		if (i < start || i > end) {
			return;
		}
		segTree[index] += diff;
		if (end != start) {
			int mid = start + (end - start) / 2;
			updateValue(segTree, start, mid, i, diff, 2 * index + 1);
			updateValue(segTree, mid + 1, end, i, diff, 2 * index + 2);
		}
	}

	public static void update(int[] array, int[] segTree, int i, int newValue) {
		if (i < 0 || i > array.length - 1) {
			return;// error
		}
		int diff = newValue - array[i];
		array[i] = newValue;
		updateValue(segTree, 0, array.length - 1, i, diff, 0);
	}


	public static int constructSegmentTree(int[] array, int start, int end,
			int[] segTree, int sIndex) {
		if (start == end) {
			segTree[sIndex] = array[start];
			return array[start];
		}
		int mid = start + (end - start) / 2;
		segTree[sIndex] = constructSegmentTree(array, start, mid, segTree,
				2 * sIndex + 1)
				+ constructSegmentTree(array, mid + 1, end, segTree,
						2 * sIndex + 2);
		return segTree[sIndex];
	}

	public static int[] constructSegmentTree(int[] array) {
		
		int x = (int) (Math.ceil(Math.log10(array.length) / Math.log10(2)));
		int maxSize = 2 * (int) Math.pow(2, x) - 1;
		
		int[] segTree = new int[maxSize];
		
		constructSegmentTree(array, 0, array.length - 1, segTree, 0);
		
		return segTree;
	}

	public static void main(String[] args) {
		int array[] = {1, 3, 5, 7, 9, 11};
		int [] segTree = constructSegmentTree(array);
		System.out.printf("Sum of values in given range = %d\n", getSum(segTree, array.length, 1, 3));
		
		update(array, segTree, 1, 10);
		 
		System.out.printf("Sum of values in given range = %d\n", getSum(segTree, array.length, 1, 3));
	}

}
