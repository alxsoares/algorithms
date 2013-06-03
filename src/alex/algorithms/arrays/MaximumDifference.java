package alex.algorithms.arrays;

public class MaximumDifference {

	public static int maxDiff(int arr[]) {
		int diff = arr[1] - arr[0];
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] - min > diff) {
				diff = arr[i] - min;
			}
			if (min > arr[i]) {
				min = arr[i];
			}
		}
		return diff;
	}

	public static int maxIndexDiff(int array[]) {
		int[] LMIN = new int[array.length];
		int[] RMAX = new int[array.length];
		LMIN[0] = array[0];
		for (int i = 1; i < array.length; i++) {
			LMIN[i] = Math.min(array[i], LMIN[i - 1]);
		}
		RMAX[array.length - 1] = array[array.length - 1];
		for (int i = array.length - 2; i >= 0; i--) {
			RMAX[i] = Math.max(array[i], RMAX[i + 1]);
		}

		int maxDiff = Integer.MIN_VALUE;

		for (int i = 0; i < array.length; i++) {
			int diff = RMAX[i] - LMIN[i];
			if (diff > maxDiff) {
				maxDiff = diff;
			}
		}
		return maxDiff;
	}

	// O(2^n)???
	public static int maxIndexDiffRec(int[] array, int start, int end) {
		if (end < start)
			return -1;
		if (array[end] > array[start]) {
			int x = end - start;
			return x;
		} else {
			int x = maxIndexDiffRec(array, start + 1, end);
			int y = maxIndexDiffRec(array, start, end - 1);
			return Math.max(x, y);
		}
	}

	public static int maxDiffKadane(int[] array) {
		int diff = array[1] - array[0];
		int currentDiff = diff;
		int maxDiff = currentDiff;
		for (int i = 1; i < array.length - 1; i++) {
			diff = array[i + 1] - array[i];
			if (currentDiff > 0) {
				currentDiff += diff;
			} else
				currentDiff = diff;
			if (currentDiff > maxDiff)
				maxDiff = currentDiff;
		}
		return maxDiff;
	}

	public static int maxDiffKadane2(int array[]) {
		if (array == null || array.length <= 1)
			return -1;
		if (array.length == 2) {
			return array[1] - array[0];
		}
		int maxDiff = array[1] - array[0];
		int currentDiff = maxDiff;
		int begin =0;
		int end=1;
		for (int i = 2; i < array.length; i++) {
			int diff = array[i] - array[i-1];
			if(currentDiff > 0){
				currentDiff+=diff;
			}else{
				currentDiff = diff;
				begin =i-1;
			}
			if(currentDiff>maxDiff){
				maxDiff = currentDiff;
				end=i;
			}
		}
		System.out.printf("%d %d\n", begin,end);
		return maxDiff;
	}

	public static void main(String[] args) {
		int array[] = { 80, 2, 6, 3, 100 };
		System.out.printf("%d\n", maxDiff(array));
		System.out.printf("%d\n", maxIndexDiff(array));
		System.out.printf("%d\n", maxDiffKadane(array));
		System.out.printf("%d\n", maxDiffKadane2(array));
	}

}
