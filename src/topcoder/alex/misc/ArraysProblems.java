package topcoder.alex.misc;

public class ArraysProblems {
	/**
	 * 
	 Find subarray with given sum
	 * 
	 * Given an unsorted array of nonnegative integers, find a continous
	 * subarray which adds to a given number.
	 */
	public static void subArraySum(int [] a, int sum) {
		int start =0;
		int currSum = a[0];
		for(int i=1; i < a.length;i++){
			while(currSum > sum && start < i-1){
				currSum-=a[start++];
			}
			if(currSum == sum){
				System.out.printf("start = %d end = %d", start,i-1);
				return;
			}
			currSum+=a[i];
		}
		System.out.println("Subarray not found");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = {15, 2, 4, 8, 9, 5, 10, 23};
		subArraySum(arr, 23);
	}

}
