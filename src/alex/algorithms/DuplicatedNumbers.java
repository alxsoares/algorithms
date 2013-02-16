package alex.algorithms;

public class DuplicatedNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = {0,1,2,3,4,5,6,7,8,4,9};
		int offset = 1;
		int a= 0;
		for(int i=0;i<arr.length;i++) {
		 a= a^ (arr[i]+offset) ^i;
		}
		System.out.printf("%d\n",(a-offset));

	}

}
