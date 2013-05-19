package alex.algorithms.math;

import java.util.Random;

public class MonteCarloPIEstimation {

	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		int countIn=0;
		int ITERATIONS = 9999999;
		for(int i=0; i< ITERATIONS;i++){
			double x = rand.nextDouble();
			double y = rand.nextDouble();
			double r = x*x + y*y;
			if(r <=1.0){
				countIn++;
			}
		}
		System.out.printf("%f\n%f\n", 4*((double)countIn/ITERATIONS),java.lang.Math.PI);
		
	}

}
