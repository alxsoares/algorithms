package alex.algorithms.arrays;

public class LargetProductIncreasingSubSequence {
	public static int maxThreeIncreasingProduct(int[] a) {
		int LSL[] = new int[a.length];
		int LGR[] = new int[a.length];
		LSL[0] = a[0];
		for (int i = 1; i < a.length; i++) {
			LSL[i] = Math.max(LSL[i - 1], a[i]);
		}
		LGR[a.length - 1] = a[a.length - 1];
		for (int i = a.length - 2; i >= 0; i--) {
			LGR[i] = Math.max(LGR[i + 1], a[i]);
		}
		int prod = Integer.MIN_VALUE;
		for (int i = 1; i < a.length - 1; i++) {
			if (a[i] >= LSL[i - 1] && a[i] <= LGR[i + 1]) {
				int t = LSL[i - 1] * a[i] * LGR[i + 1];
				if (t > prod) {
					prod = t;
				}
			}
		}
		return prod;
	}
	
	public static void main(String[] args) {
		int [] a = {1, 5, 10, 8, 9};
		System.out.println(maxThreeIncreasingProduct(a));
	}
}
