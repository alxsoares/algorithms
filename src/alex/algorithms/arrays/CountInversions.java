package alex.algorithms.arrays;

import java.util.Arrays;

public class CountInversions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int countInversions(int[] arr) {
		if (arr.length < 2)
			return 0;
	
		int m = (arr.length + 1) / 2;
		int left[] = Arrays.copyOfRange(arr, 0, m);
		int right[] = Arrays.copyOfRange(arr, m, arr.length);
	
		return countInversions(left) + countInversions(right) + CountInversions.merge(arr, left, right);
	}

    public static int merge(int[] arr, int[] left, int[] right) {
    	int i = 0, j = 0, count = 0;
    	while (i < left.length || j < right.length) {
    		if (i == left.length) {
    			arr[i + j] = right[j];
    			j++;
    		} else if (j == right.length) {
    			arr[i + j] = left[i];
    			i++;
    		} else if (left[i] <= right[j]) {
    			arr[i + j] = left[i];
    			i++;
    		} else {
    			arr[i + j] = right[j];
    			// right element is lesser than all the rest of array left
    			count += left.length - i;
    			j++;
    		}
    	}
    	return count;
    }

}
