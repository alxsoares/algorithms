package alex.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

import alex.datastructures.MaxHeap;

public class MedianInStream {

	private PriorityQueue<Integer> minHeap;
	private PriorityQueue<Integer> maxHeap;

	public MedianInStream() {
		this.minHeap = new PriorityQueue<Integer>();
		this.maxHeap = new PriorityQueue<Integer>(1024,
				new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						return o2.compareTo(o1);
					}
				});
	}

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

	public void t(int num) {
		if (maxHeap.size() == minHeap.size()) {
			if (minHeap.peek() != null && num > minHeap.peek()) {
				maxHeap.offer(minHeap.poll());
				minHeap.offer(num);
			} else {
				maxHeap.offer(num);
			}
		} else {
			if (num < maxHeap.peek()) {
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(num);
			} else {
				minHeap.offer(num);
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

	public static void main(String[] args) {
		MedianInStream mi = new MedianInStream();
		for (int i = 0; i <= 100; i++) {
			mi.addNewNumber(100-i);
		}
		System.out.printf("%.4f\n", mi.getMedian());
	}

}
