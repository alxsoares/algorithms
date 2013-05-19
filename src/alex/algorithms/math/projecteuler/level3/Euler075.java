package alex.algorithms.math.projecteuler.level3;

/**
 * 
 * http://en.wikipedia.org/wiki/Pythagorean_triple
 */
public class Euler075 {

	public static long gcd(final long u, final long v) {
		if (u == v)
			return u;
		if (u == 0)
			return v;
		if (v == 0)
			return u;
		if ((~u & 1) > 0) {// u é par
			if ((v & 1) > 0) {// v impar
				return gcd(u >> 1, v);// v impar e u par
			}
			return gcd(u >> 1, v >> 1) << 1;// ambos são pares
		}
		if ((~v & 1) > 0)
			return gcd(u, v >> 1);// v par e u impar
		if (u > v) {
			return gcd((u - v) >> 1, v);
		} else {
			return gcd(u, (v - u) >> 1);
		}
	}
	public static void main(String[] args) {
		int bound = 1500000;
		int[] triangles = new int[bound+1];
		 
		int numWays =0;
		int mlimit = (int)Math.sqrt(bound / 2);
		 
		for (long m = 2; m < mlimit; m++) {
		    for (long n = 1; n < m; n++) {
		        if (((n + m) % 2) == 1 && gcd(n, m) == 1) {
		            long a = m * m + n * n;
		            long b = m * m - n * n;
		            long c = 2 * m * n;
		            long p = a + b + c;
		            while(p <= bound){
		                triangles[(int) p]++;
		                p += a+b+c;
		            }
		        }
		    }
		}
		for(int p=1; p <= bound;p++){
			if(triangles[p]==1) numWays++;
		}
		System.out.printf("%d\n", numWays);
	}

}
