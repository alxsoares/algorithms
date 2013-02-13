package alex.algorithms;

public class MaxSum {
	public static int maxSubVect(int dados[], int n) {
		int imax = 0, jmax = 0, maxsum = 0xffffffff;
		int auxsum;
		for (int i = 0; i < n; i++) {
			auxsum = 0;
			for (int j = i; j < n; j++) {
				auxsum += dados[j];
				if (auxsum > maxsum) {
					imax = i;
					jmax = j;
					maxsum = auxsum;
				}
			}
		}
		System.out.println("Inicio: " + imax + " fim: " + jmax);
		return maxsum;
	}

	public static void main(String[] args) {
		MaxSum maxsum = new MaxSum();
	}
}
