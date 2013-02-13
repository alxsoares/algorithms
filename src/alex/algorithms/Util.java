package alex.algorithms;

public class Util {

	public static double power(double b, double e) {
		double ee = 0.5;
		double sq = Math.sqrt(b);
		double r = 1;
		double epslon = sq;
		while (epslon > 0) {
			if (e - ee > 0) {
				r = r * sq;
				e = e - ee;
			}
			ee = ee / 2;
			epslon = sq;
			sq = Math.sqrt(sq);
			epslon = epslon - sq;
		}
		return r;
	}

	public static int exp(int x, int n){
	    int result = 1;
	    int fact = x;
	    while(n > 0){
	      if( (n &1) > 0 ){
	        result = result*fact;
	      }
	      n = n>>1;
	      fact *=fact;
	    }
	    return result;
	  }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(power(2, 0.91));
		System.out.println(Math.pow(2, 0.91));
	}

}
