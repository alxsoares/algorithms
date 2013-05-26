package alex.algorithms.math.projecteuler;

public class Euler144 {

	public static void main(String[] args) {
		int result = 0;
		 
		double xA = 0.0;
		double yA = 10.1;
		 
		double xO = 1.4;
		double yO = -9.6;
		 
		while(xO > 0.01 || xO < -0.01 || yO < 0){
		 
		    //Calculate the slope of A
		    double slopeA = (yO - yA) / (xO - xA);
		 
		    //Calculate the slope of the ellipse tangent
		    double slopeO = -4*xO/yO;
		 
		    //Calculate the slope of B
		    double tanA = (slopeA - slopeO)/(1+slopeA*slopeO);
		    double slopeB = (slopeO- tanA)/ (1+ tanA*slopeO);
		 
		    //calculate intercept of line B
		    double interceptB = yO - slopeB * xO;
		 
		    //solve the quadratic equation for finding
		    // the intersection of B and the ellipse
		    // a*x^2 + b*x + c = 0
		    double a = 4 + slopeB*slopeB;
		    double b = 2 * slopeB * interceptB;
		    double c = interceptB * interceptB - 100;
		 
		    double ans1 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		    double ans2 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		 
		    xA = xO;
		    yA = yO;
		 
		    xO = (Math.abs(ans1 - xO) > Math.abs(ans2 - xO)) ? ans1 : ans2;
		    yO = slopeB * xO + interceptB;
		 
		    result++;
		}
		System.out.println(result);
	}

}
