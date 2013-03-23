package alex.datastructures;

import java.util.PriorityQueue;

public class MinInInterval {

	public static int[] minInInterval(int[] A, int k) {
		int[] B = new int[A.length];
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		for (int i = 0; i < k; i++) {
			minHeap.add(A[i]);
		}

		int j = k;

		for (int i = 0; i < A.length; i++) {
			B[i] = minHeap.peek();
			minHeap.remove(A[i]);
			if (j < A.length) {
				minHeap.add(A[j++]);
			}
		}
		return B;
	}

	public static void main(String[] args) {
		int[] A = { 1, 2, -1, 3, 4, 5, 6, 7, 8, 9, 10, 9, 1 };
		int[] B = minInInterval(A, 3);
		for (int i = 0; i < B.length; i++) {
			System.out.printf("%d ", B[i]);
		}
		System.out.println();
	}

}
