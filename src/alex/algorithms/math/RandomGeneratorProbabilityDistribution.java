package alex.algorithms.math;

import java.util.Random;

public class RandomGeneratorProbabilityDistribution {
	// Utility function to find ceiling of r in arr[l..h]
	static int findCeil(int arr[], int r, int l, int h) {
		int mid;
		while (l < h) {
			mid = (l+h)>>1; // Same as mid = (l+h)/2
			if (r > arr[mid]) {
				l = mid + 1;
			} else {
				h = mid;
			}
		}
		if(arr[l]>=r){
			return l;
		}
		return -1;
	}

	// The main function that returns a random number from arr[] according to
	// distribution array defined by freq[]. n is size of arrays.
	static int customRand(int arr[], int freq[]) {
		int n = arr.length;
		Random rand = new Random(System.nanoTime());
		// Create and fill prefix array
		int prefix[] = new int[n];
		prefix[0] = freq[0];
		for (int i = 1; i < n; i++)
			prefix[i] = prefix[i - 1] + freq[i];

		// prefix[n-1] is sum of all frequencies. Generate a random number
		// with value from 1 to this sum
		int r = rand.nextInt(prefix[n - 1]) + 1;

		// Find index of ceiling of r in prefix arrat
		int indexc = findCeil(prefix, r, 0, n - 1);
		return arr[indexc];
	}

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 4 };
		int freq[] = { 10, 5, 20, 100 };
		for (int i = 0; i < 5; i++)
			System.out.printf("%d ", customRand(arr, freq));
		System.out.println();

	}

}
