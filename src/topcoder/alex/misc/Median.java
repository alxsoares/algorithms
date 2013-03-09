package topcoder.alex.misc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class Median {
	class MaxHeapComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}

	}

	private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10,
			new MaxHeapComparator());
	private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

	public void addNewNumber(int randomNumber) {
		if (maxHeap.size() == minHeap.size()) {
			if (minHeap.peek() != null && randomNumber > minHeap.peek()) {
				maxHeap.offer(minHeap.poll());
				minHeap.offer(randomNumber);
			} else {
				maxHeap.offer(randomNumber);
			}
		} else {
			if (randomNumber < maxHeap.peek()) {
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(randomNumber);
			} else {
				minHeap.offer(randomNumber);
			}
		}

	}

	public double getMedian() {
		if (maxHeap.isEmpty())
			return minHeap.peek();
		else if (minHeap.isEmpty())
			return maxHeap.peek();
		if (maxHeap.size() == minHeap.size()) {
			return (minHeap.peek() + maxHeap.peek()) / 2;
		} else if (maxHeap.size() > minHeap.size()) {
			return maxHeap.peek();
		} else {
			return minHeap.peek();
		}
	}
	public static int getMedianRec(int ar1[], int ar2[], int left, int right,
			int n) {
		if (left > right) {
			return getMedianRec(ar2, ar1, 0, n - 1, n);
		}
		int i = (left + right) / 2;
		int j = n - i - 1;
		if (ar1[i] > ar2[j] && (j == n - 1 || ar2[j + 1] >= ar1[i])) {
			if (ar2[j] > ar1[i - 1] || i == 0) {
				return (ar1[i] + ar2[j]) / 2;
			} else {
				return (ar1[i] + ar1[i - 1]) / 2;
			}
		} else if (ar1[i] > ar2[j] && (j != n - 1 && ar1[i] > ar2[j + 1])) {
			return getMedianRec(ar1, ar2, left, i - 1, n);
		} else {
			return getMedianRec(ar1, ar2, i + 1, right, n);
		}
	}

	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		Median m = new Median();
		int numbers[] = new int[100];
		for(int i=0;i < 100; i++){
			numbers[i] = rand.nextInt(100);
			m.addNewNumber(numbers[i]);
		}
		Arrays.sort(numbers);
		for (int i = 0; i < numbers.length; i++) {
			System.out.printf("%d ", numbers[i]);
		}
		System.out.println();
		System.out.println(m.getMedian());
	}

}
