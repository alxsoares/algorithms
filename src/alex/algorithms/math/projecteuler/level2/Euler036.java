package alex.algorithms.math.projecteuler.level2;

public class Euler036 {

	public static boolean isPalindrome(int n, int b) {
		int r = 0;
		int c = n;
		while (c > 0) {
			r = r * b + c % b;
			c = c / b;
		}
		return (n == r);
	}

	public static void main(String[] args) {
		int sum =0;
		for(int i=1;i<1000000;i++){
			if(isPalindrome(i, 10) && isPalindrome(i, 2)){
				sum+=i;
			}
		}
		System.out.printf("%d\n",sum);
	}

}
