package topcoder.alex.misc;

public class ArraysProblems {
	/**
	 * 
	 Find subarray with given sum
	 * 
	 * Given an unsorted array of nonnegative integers, find a continous
	 * subarray which adds to a given number.
	 */
	public static void subArraySum(int[] a, int sum) {
		int start = 0;
		int currSum = a[0];
		for (int i = 1; i < a.length; i++) {
			while (currSum > sum && start < i - 1) {
				currSum -= a[start++];
			}
			if (currSum == sum) {
				System.out.printf("start = %d end = %d\n", start, i - 1);
				return;
			}
			currSum += a[i];
		}
		System.out.println("Subarray not found\n");
	}

	/**
	 * Given an unsorted array arr[0..n-1] of size n, find the minimum length
	 * subarray arr[s..e] such that sorting this subarray makes the whole array
	 * sorted.
	 * 
	 */
	public static void printMinimumUnsorted(int[] a) {
		int start = 0;
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				start = i;
				break;
			}
		}
		if (start == a.length - 1) {
			System.out.println("Array is sorted");
			return;
		}
		int end = a.length - 1;
		for (int i = a.length - 1; i > 0; i--) {
			if (a[i] < a[i - 1]) {
				end = i;
				break;
			}
		}
		int max = a[start];
		int min = a[start];
		for (int i = start + 1; i <= end; i++) {
			if (a[i] < min) {
				min = a[i];
			}
			if (a[i] > max) {
				max = a[i];
			}
		}
		for(int i=0; i < start;i++){
			if(a[i] > min){
				start = i;
				break;
			}
		}
		for(int i=a.length-1; i> end;i--){
			if(a[i] < max){
				end = i;
				break;
			}
		}
		System.out.printf("Minimum array to be sorted %d %d\n", start, end);
	}
	/**
	 * Kadane's algorithm 
	 */
	public static int maxSubArraySum(int a[]){
		int maxSofar = a[0];
		int maxEndingHere=a[0];
		int startTemp=0;
		int start=0;
		int end=0;
		for(int i=1; i < a.length;i++){
			
			if(maxEndingHere < 0){
				maxEndingHere = a[i];
				startTemp=i;
			}else{
				maxEndingHere +=a[i];
			}
			if(maxEndingHere > maxSofar){
				start = startTemp;
				end=i;
				maxSofar = maxEndingHere;
			}
		}
		System.out.printf("%d %d\n", start, end);
		return maxSofar;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 15, 2, 4, 8, 9, 5, 10, 23 };
		subArraySum(arr, 23);
		int a[] = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
		printMinimumUnsorted(a);
		System.out.printf("Maxsum = %d\n", maxSubArraySum(new int[]{-2, -3, 4, -1, -2, 1, 5, -3}));
	}

}
