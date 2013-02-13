package topcoder.alex.misc;

import java.util.Arrays;

public class Sum {

	public static void printSum(int[] numbers, int sum){
		Arrays.sort(numbers);
		int begin=0;
		int end=numbers.length-1;
		while(begin < end){
			int nb= numbers[begin];
			int ne = numbers[end];
			if(ne-sum > nb ) {
				end--;
				continue;
			}
			if(sum-nb > ne){
				begin++;
				continue;
			}
			if(nb+ne == sum){
				System.out.printf("%d %d\n", nb, ne);
				end--;
				begin++;
			}
			
			
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = {1, 2, 3, 4, 5};
		printSum(arr, 7);
	}

}
