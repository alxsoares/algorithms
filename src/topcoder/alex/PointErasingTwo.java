package topcoder.alex;

/**
 * http://community.topcoder.com/stat?c=problem_statement&pm=11582&rd=14547
 * 
 */
public class PointErasingTwo {

	public int getMaximum(int[] y) {
		int n = y.length;
		int max = 0;

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int count = 0;
				for (int k = 0; k < n; k++) {
					if (i < k && k < j) {
						if (Math.min(y[i], y[j]) < y[k]
								&& y[k] < Math.max(y[i], y[j])) {
							count++;
						}
					}
				}
				max = Math.max(max, count);
			}

		}

		return max;
	}

	public static void main(String[] args) {
		PointErasingTwo p = new PointErasingTwo();
		System.out.printf(
				"Maximum number of points erased by a single operation= %d \n",
				p.getMaximum(new int[] { 0, 23, 49, 50, 32, 0, 18, 50, 0, 28,
						50, 27, 49, 0 }));
	}
}
