package alex.algorithms;

public class MaxSubarrayEqualOnesZeros {

	public static int max(int[] a) {
		if (a == null || a.length < 2)
			return 0;
		Integer hash[] = new Integer[2 * a.length + 1];
		int sum = 0;
		hash[a.length] = -1;
		int maxLength = 0;
		int maxStart = -1;
		int maxEnd = -1;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 0) {
				sum -= 1;
			} else {
				sum += 1;
			}
			Integer index = hash[a.length + sum];
			if (index == null) {
				hash[a.length + sum] = i;
			} else {
				if (i - index > maxLength) {
					maxLength = i - index;
					maxStart = index +1;
					maxEnd = i;
				}
			}
		}
		System.out.println("indices (" + maxStart + "," + maxEnd + ")");
		System.out.println("length=" + maxLength);
		return maxLength;
	}

	public static void main(String[] args) {
		int[] arr =
			  { 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1,
			    0, 1, 1, 0, 1, 0, 0, 0 };
			  System.out.println(max(arr));

	}

}
