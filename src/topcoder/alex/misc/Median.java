package topcoder.alex.misc;

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

	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		Median m = new Median();
		for(int i=0;i < 100; i++){
			m.addNewNumber(rand.nextInt(100));
		}
		System.out.println(m.getMedian());
	}

}
