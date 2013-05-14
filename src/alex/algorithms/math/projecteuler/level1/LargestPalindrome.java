package alex.algorithms.math.projecteuler.level1;


public class LargestPalindrome {
	// assuming n nonnegative
	public static boolean isPalindrome(int num) {
		int p = 0;
		int n = num;
		while (n != 0) {
			p = p * 10 + n % 10;
			n = n / 10;
		}
		return (num == p);
	}
	public static boolean isPal(int num){
		String s = String.valueOf(num);
		char [] a= s.toCharArray();
		for(int i=0; i< a.length/2;i++){
			char aux = a[i];
			a[i] = a[a.length-1-i];
			a[a.length-1-i] = aux;
		}
		return new String(a).equals(s);
	}
	public static int largestPalindrome(){
		int max = 0;
		int imax=0;
		int jmax=0;
		for(int i=999; i>=100;i--){
			for(int j=i;j>=100;j--){
				if(isPalindrome(i*j)){
					System.out.printf("%d %d %d\n", i,j,i*j);
					if(i*j > max){
						max = i*j;
						imax=i;
						jmax =j;
					}
					//return i*j;
				}
			}
		}
		System.out.printf("%d %d %d\n", imax,jmax,max);
		return max;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(isPalindrome(1));
		System.out.println(isPalindrome(9399));
		System.out.println(isPal(993 * 913));
		largestPalindrome();
	}

}
