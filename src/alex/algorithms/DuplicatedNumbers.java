package alex.algorithms;

public class DuplicatedNumbers {

	public static int findUniqueNumber(int[] arr){
		int a =0;
		for(int i=0; i < arr.length; i++){
			a=a^(arr[i]);
		}
		return a;
	}
//	public static int findDuplicatedNumber(int[] arr){
//		int a =0;
//		for(int i=0; i < arr.length; i++){
//			a=a^(arr[i]);
//		}
//		return a;
//	}
	public static void main(String[] args) {
		int arr[] = {1,1,10,10,5,6,7,7,6};
		System.out.printf("%d\n",findUniqueNumber(arr));

	}

}
