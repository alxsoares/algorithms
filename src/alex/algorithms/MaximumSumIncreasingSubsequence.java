package alex.algorithms;

public class MaximumSumIncreasingSubsequence {
	public static int maxSumIncreasingSubSequence(int array[]){
		int [] dp = new int[array.length];
		int max = Integer.MIN_VALUE;
		dp[0] = array[0];
		for(int i=1; i<array.length;i++){
			dp[i] = array[i];
			for(int j=0; j<=i;j++){
				if(array[i] > array[j] && dp[i] < dp[j] + array[i]){
					dp[i] = dp[j] + array[i];
				}
			}
			if(dp[i] > max){
				max = dp[i];
			}
		}
		return max;
	}
	public static void main(String[] args) {
		int array[] = {1, 101, 2, 3, 100, 4, 5};
		System.out.printf("%d\n", maxSumIncreasingSubSequence(array));
	}

}
