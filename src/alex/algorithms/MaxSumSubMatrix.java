package alex.algorithms;

public class MaxSumSubMatrix {
	public static int N=3;
	private static int sumMatrix[][] = new int[N][N];

	public static void preComputeMatrix(int a[][]) {
		int N = a.length;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == 0 && j == 0)
					sumMatrix[i][j] = a[i][j];
				else if (i == 0)
					sumMatrix[i][j] += sumMatrix[i][j - 1] + a[i][j];
				else if (j == 0)
					sumMatrix[i][j] += sumMatrix[i - 1][j] + a[i][j];
				else
					sumMatrix[i][j] += sumMatrix[i - 1][j]
							+ sumMatrix[i][j - 1] - sumMatrix[i - 1][j - 1]
							+ a[i][j];
			}
		}
	}

	public static int sum(int a[][], int i, int ii, int j, int jj) {

		if (i == 0 && j == 0)
			return sumMatrix[ii][jj];
		else if (i == 0)
			return sumMatrix[ii][jj] - sumMatrix[ii][j - 1];
		else if (j == 0)
			return sumMatrix[ii][jj] - sumMatrix[i - 1][jj];
		else
			return sumMatrix[ii][jj] - sumMatrix[ii][j - 1]
					- sumMatrix[i - 1][jj] + sumMatrix[i - 1][j - 1];
	}

	public static int getMaxMatrix(int a[][])
	{
		int N = a.length;
		
	    int maxSum = Integer.MIN_VALUE;
	    for(int row1=0; row1<N; row1++)
	    {
	        for(int row2=row1; row2<N; row2++)
	        {
	            for(int col1=0; col1<N; col1++)
	            {
	                for(int col2=col1; col2<N; col2++)
	                {
	                    maxSum = Math.max(maxSum,sum(a,row1,row2,col1,col2));
	                }
	            }
	        }
	    }
	    return maxSum;
	}
	public static void main(String[] args) {
		int a[][] = {
				{-1,-2,-3},
				{10,-5,15},
				{6,8,20}
				};
	    preComputeMatrix(a);
	    for(int i=0; i<3; i++)
	    {
	        for(int j=0; j<N; j++){
	        	System.out.printf("%d ",sumMatrix[i][j]);
	        }
	        System.out.println();
	    }
	    System.out.println(getMaxMatrix(a));

	}

}
