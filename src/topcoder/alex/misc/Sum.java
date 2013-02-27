package topcoder.alex.misc;

import java.util.Arrays;

public class Sum {

	public static void printSum(final int[] numbers, final int sum) {
		Arrays.sort(numbers);
		int begin = 0;
		int end = numbers.length - 1;
		while (begin < end) {
			int nb = numbers[begin];
			int ne = numbers[end];
			if (ne - sum > nb) {
				end--;
				continue;
			}
			if (sum - nb > ne) {
				begin++;
				continue;
			}
			if (nb + ne == sum) {
				System.out.printf("%d %d\n", nb, ne);
				end--;
				begin++;
			}

		}
	}

	public static void printSum4(final int[] numbers) {
		Arrays.sort(numbers);
		for (int i = 0; i < numbers.length - 3; i++) {
			int begin = i + 1;
			int end = numbers.length - 1;
			int first = numbers[i];
			while (begin <= end) {
				int nb = numbers[begin];
				int ne = numbers[end];
				int sum = first + ne + nb;
				if (sum == 0) {
					System.out.printf("%d %d %d\n", first, nb, ne);
					break;
				}
				if (sum > 0) {
					end--;
				} else if (sum < 0) {
					begin++;
				}

			}
		}
	}

	public static void sumZero(final int[] A) {
		Arrays.sort(A);

		int n = A.length;
		for (int i = 0; i < n - 3; i++) {
			int j = i + 1;
			int k = n - 1;
			while (k >= j) {
				if (A[i] + A[j] + A[k] == 0) {
					System.out.printf("%d %d %d\n", i, j, k);
					break;
				}
				int s = A[i] + A[j] + A[k];
				if (s > 0) {
					k--;
				} else {
					j++;
				}

			}
		}
	}

	/**
	 * Given an array arr[] of integers, find out the difference between any two
	 * elements such that larger element appears after the smaller number in
	 * array[].
	 * 
	 */
	public static int maxDiff(int arr[]) {
		int maxDiff = arr[1] - arr[0];
		int min = arr[0];
		int i;
		for (i = 1; i < arr.length; i++) {
			if (arr[i] - min > maxDiff )
				maxDiff = arr[i] - min;
			if (arr[i] < min)
				min = arr[i];
		}
		return maxDiff;
	}
	public static int maxDiff2(int arr[])
	{    
	  int max_diff = arr[1] - arr[0];
	  int i, j;
	  for(i = 0; i < arr.length; i++)
	  {
	    for(j = i+1; j < arr.length; j++)
	    {       
	      if(arr[j] - arr[i] > max_diff )  
	         max_diff = arr[j] - arr[i];
	    }   
	  }         
	  return max_diff;
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		int arr2[] = { -4, -2, -1, 1, 2, 3, 4, 5,-10};
		printSum4(arr2);
		int arr3[] = { -4, -2, -1, 1, 2, 3, 4, 5,-10,1};
		System.out.println(maxDiff(arr3));
		System.out.println(maxDiff2(arr3));
	}

}
