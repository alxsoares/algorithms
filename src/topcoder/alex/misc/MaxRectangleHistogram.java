package topcoder.alex.misc;

public class MaxRectangleHistogram {

	public static int findMinimum(int[] hist, int start, int end) {
		Integer min = start;
		for (int i = start + 1; i <= end && i < hist.length; i++) {
			if (hist[min] > hist[i]) {
				min = i;
			}
		}
		return min;
	}

	public static int getMaxAreaRectangle(int[] hist, int left, int right) {
		if (left > right)
			return Integer.MIN_VALUE;
		if (left == right)
			return hist[left];
		int min = findMinimum(hist, left, right);
		return Math.max(
				getMaxAreaRectangle(hist, left, min - 1),
				Math.max(getMaxAreaRectangle(hist, min + 1, right), (right
						- left + 1)
						* hist[min]));
	}

	public static int getMaxArea(int hist[]) {
		return getMaxAreaRectangle(hist, 0, hist.length - 1);
	}

	public static void main(String[] args) {
		int hist[] = { 6, 1, 5, 4, 5, 2, 6 };
		System.out.printf("Max area is %d\n", getMaxArea(hist));
	}

}
