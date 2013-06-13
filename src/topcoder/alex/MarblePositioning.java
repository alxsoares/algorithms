package topcoder.alex;

public class MarblePositioning {
	int n = 0;
	double[] radius;
	double best = Double.MAX_VALUE;

	// Given the radiuses in a fixed order, find the minimum
	// p[n-1] - p[0]:
	double minWidth()
	{
	    double[] p = new double[n];
	    // Place the first marble on point 0.0:
	    p[0] = 0.0;
	    for (int i=1; i<n; i++) {
	        // Decide where to place marble i:
	        p[i] = 0;
	        for (int j=0; j<i; j++) {
	            double dis = p[j] + 2 * Math.sqrt(radius[i] * radius[j]);
	            // Dis is the minimum distance where we can place i
	            // without overlaping with j.
	            p[i] = Math.max(p[i], dis );
	            // The maximum of all the distances is the best place
	            // for p[i]
	        }
	    }
	    return p[n-1] - p[0];
	}

	// Use backtracking to generate each of the permutations of
	// the original radius array:
	void rec(int p)
	{
	    if (p == n) {
	        // Remember the permutation with the best result:
	        best = Math.min(best, minWidth());
	    } else {
	        // Decide the radius for position p.
	        // We have already decided the smaller positions,
	        // so we need to pick a radius from indices >= p:
	        double radp = radius[p];
	        for (int i=p; i<n; i++) {
	            // Swap radius[p] and radius[i]
	            radius[p] = radius[i];
	            radius[i] = radp;
	            
	            // Step in the recursion:
	            rec(p + 1);
	            
	            // Restore radius[i]
	            radius[i] = radius[p];
	        }
	        radius[p] = radp;
	    }
	}

	public double totalWidth(int[] radius)
	{
	    n = radius.length;
	    this.radius = new double[n];
	    best = Double.MAX_VALUE;
	    for (int i = 0; i < n; i++) {
	        this.radius[i] = (double)radius[i];
	    }
	    // Start backtracking:
	    rec(0);
	    return best;
	}
	public static void main(String[] args) {
		System.out.println(new MarblePositioning().totalWidth(new int[]{1, 2}));
		System.out.println(new MarblePositioning().totalWidth(new int[]{7,7,7}));
		System.out.println(new MarblePositioning().totalWidth(new int[]{10, 20,30}));
	}

}
