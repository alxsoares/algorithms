package alex.algorithms.dynamicprogramming;

public class MaximumIncreasingSubSequence {

	public static int maxSumIS(int[] array) {
		int n = array.length;
		int[] sums = new int[n];
		
		for (int i = 0; i < n; i++) {
			sums[i] = array[i];
		}
		int maxSum=Integer.MIN_VALUE;
		for(int i=1;i<n;i++){
			for(int j=0; j <i;j++){
				if(array[j] < array[i] && sums[i] < sums[j]+array[i]){
					sums[i] = sums[j] + array[i];
				}
			}
			maxSum = Math.max(maxSum, sums[i]);
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int arr[] = {1, 101, 2, 3, 100, 4, 5};
		  System.out.printf("Sum of maximum sum increasing subsequence is %d\n",
		         maxSumIS( arr) );
	}

}
