package alex.algorithms;

public class DuplicatedNumbers {

	public static int findUniqueNumber(int[] arr){
		int a =0;
		for(int i=0; i < arr.length; i++){
			a=a^(arr[i]);
		}
		return a;
	}
	public static int findDuplicateNumber(int array[]){
		int slow= array.length-1;
		int fast = array.length-1;
		while(true){
			slow = array[slow];
			fast = array[array[fast]];
			if(slow == fast) break;
		}
		//  Start up another pointer from the end of the array and march it forward
	    // until it hits the pointer inside the array.
		int finder = array.length-1;
		while(true){
			slow = array[slow];
			finder = array[finder];
			if(slow  == finder)
				return slow;
		}
	}
	public static int findDuplicated(int[] array){
		int a=0;
		for(int i=0; i< array.length; i++){
			a = a ^ array[i] ^ i;
		}
		return a;
	}
	public static void main(String[] args) {
		int arr[] = {1,2,3,4,5,6,7,8,9,2};
		System.out.printf("%d\n",findUniqueNumber(arr));
		System.out.printf("%d\n",findDuplicated(arr));
	}

}
