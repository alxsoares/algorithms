package alex.algorithms.math.projecteuler;

public class SumSquareDifference {
	

	public static void main(String[] args) {
		int sum = 100*(1+100)/2;
		int sumSquares =0;
		for(int i=1;i<=100;i++){
			sumSquares+=i*i;
		}
		System.out.printf("%d\n",sum*sum - sumSquares);
	}

}
