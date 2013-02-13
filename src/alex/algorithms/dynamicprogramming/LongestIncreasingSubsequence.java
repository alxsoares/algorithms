package alex.algorithms.dynamicprogramming;

public class LongestIncreasingSubsequence {

	public static int [] lis(int arr[], int anc[]){
		int dp[] = new int[arr.length];
		dp[0] =0;
		anc[0] =-1;
		for(int i=1; i < arr.length; i++){
			dp[i] =0;
			anc[i] =-1;
			for(int j=0; j<i;j++){
				if(arr[i]> arr[j]){
					if(dp[i]< dp[j]+1){
						dp[i]= dp[j] +1;
						anc[i] =j;
					}
				}
			}
		}
		return dp;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] ={70, 1, 2,3,4,5,6, 80, 8, 9};
		int anc[] = new int[arr.length];
		int dp[]=lis(arr,anc);
		String comma="";
		for (int i = 0; i < anc.length; i++) {
			int j = anc[i];
			System.out.print(comma+j);
			comma=",";
		}
		System.out.println();
		comma="";
		for (int i = 0; i < dp.length; i++) {
			int j = dp[i];
			System.out.print(comma+j);
			comma=",";
		}
		System.out.println();
	}

}
