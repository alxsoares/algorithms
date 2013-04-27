package alex.algorithms.math;

public class PalindromeNumber {

	public static boolean isPalindrome(int num){
		if(num < 0){
			num =-num;
		}
		int copy = num;
		int reverse =0;
		while( num > 0){
			reverse = reverse*10+num%10;
			num = num / 10;
		}
		return (copy==reverse);
	}
	
	public static void main(String[] args) {
		System.out.println(isPalindrome(0));
		System.out.println(isPalindrome(121));
		System.out.println(isPalindrome(-121));
		System.out.println(isPalindrome(123));
		System.out.println(isPalindrome(-123));
		System.out.println(isPalindrome(-12321));
		}

}
