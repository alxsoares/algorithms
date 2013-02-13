package topcoder.alex.amazon;

public class Subtraction {
	
	public static int sub( int a, int b){
		int max = Math.max(a, b);
		int min = Math.min(a, b);
		int t = 0;
		while(min > 1){
			t += max/min;
			max = max % min;
			int aux = min;
			min = max;
			max = aux;
		}
		if(min==1) t+=max;
		return t;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(sub(4, 4));
	}

}
