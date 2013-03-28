package topcoder.alex.misc;

/**
 * 
 * You are climbing a stair case. Each time you can either make 1 step or 2
 * steps. The staircase has n steps. In how many distinct ways can you climb the
 * staircase?
 */
public class ClimbingStairs {
	/**
	 * L(i) = L(i-1) + L(i-2)
	 * Fibonacci 
	 */
	int stairs(int n) {
		if (n == 0)
			return 0;
		int a = 1;
		int b = 1;
		for (int i = 1; i < n; i++) {
			int c = a;
			a = b;
			b += c;
		}
		return b;
	}

	public static void main(String[] args) {

	}

}
