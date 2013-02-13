package alex.algorithms.dynamicprogramming;

public class LIS {

	 public static int[] lis2(final int array[]) {
	        int dp[] = new int[array.length];
	        dp[0] = 1;
	        for (int i = 1; i < array.length; i++) {
	            dp[i] = 1;
	            for (int j = 0; j < i; j++) {
	                if (array[i] > array[j]) {
	                    if (dp[i] < dp[j] + 1) {
	                        dp[i] = dp[j] + 1;
	                    }
	                }
	            }
	        }
	        return dp;
	    }

	    public static void main(final String[] args) {
	        int array[] = { 70, 1, 2, 3, 4, 88, 5, 100, 6, 7 };
	        int lis[] = lis2(array);
	        String comma = "";
	        for (int i = 0; i < lis.length; i++) {
	            System.out.print(comma + lis[i]);
	            comma = ",";
	        }
	        System.out.println();
	    }
}
