package alex.algorithms;

public class SquareRoot {
	/**
	 *Approximation x = (x + n/x)/2
	 * 
	 */
	public static double sqrt(double d){
		double x = (0+ d)/2;
		while(Math.abs(x*x -d) > 0.0001 ){
			x = (x + d/x)/2;
		}
		return x;
	}
	
	public static void main(String[] args) {
		System.out.printf("%.4f\n",sqrt(16));
		System.out.printf("%.4f\n",sqrt(36));
		System.out.printf("%.4f\n",sqrt(37));
		System.out.printf("%.4f\n",sqrt(1.21));
	}

}
