package alex.algorithms.math;

public class MathWithBitOperations {

	public static int sum(int a, int b) {
		do {
			int sum = a ^ b;
			int carry = (a & b) << 1;
			b = carry;
			a = sum;
		} while (b != 0);

		return a;
	}
	public static int subtract( int a, int b){
		return sum(a, sum(~b,1));//java is two complement's
	}
	public static int multiply(int a, int b){
		int result=0;
		boolean minus = false;
		if((a < 0 && b > 0) || (a> 0 && b < 0)){
			minus =true;
		}
		if(a < 0){
			a = sum(~a,1);
		}
		if(b < 0){
			b = sum(~b,1);System.out.println(multiply(10,10));
		}
		while(b > 0){
			if((b & 1)>0){
				result = sum(result,a);
			}
			b = b >> 1;
			a = a << 1;
		}
		if(minus){
			result = sum(~result,1);
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(sum(10,2));
		System.out.println(subtract(10,2));
		System.out.println(sum(Integer.MAX_VALUE/2,Integer.MAX_VALUE/2));
		System.out.println(subtract(Integer.MAX_VALUE/2,Integer.MAX_VALUE/2));
		
		System.out.println(multiply(10,10));
		System.out.println(multiply(10,-10));
	}

}
