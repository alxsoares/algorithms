package topcoder.alex;

public class CorrectMultiplicationTwo {

	public int getMinimum(int a, int b, int c){
		int res = (a + b + c -3);
		for(int A=1; A <= c+res; A++){
			for(int B=1; B <=(c+res)/A; B++){
				int C = A*B;
				res =Math.min(res, Math.abs(A-a)+Math.abs(B-b)+Math.abs(C-c));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		CorrectMultiplicationTwo c = new CorrectMultiplicationTwo();
		System.out.printf("Minimum difference is %d\n", c.getMinimum(19, 28, 522));
		System.out.printf("Minimum difference is %d\n", c.getMinimum(1000, 100,10));
		System.out.printf("Minimum difference is %d\n", c.getMinimum(399, 522,199999));
	}

}
