package alex.algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class MedianInStream2 {
	// greater half elements
	PriorityQueue<Integer> minHeap;
	// lesser half elements
	PriorityQueue<Integer> maxHeap;

	public MedianInStream2() {
		minHeap = new PriorityQueue<Integer>();
		maxHeap = new PriorityQueue<Integer>(1024, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
	}

	/**
	 * Invariant: elements on minHeap are always greater than elements on
	 * maxHeap.
	 */
	public void insert(Integer num) {
		// if we have an even number of elements, we insert on minHeap
		if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
			if (maxHeap.size() > 0 && num < maxHeap.peek()) {
				maxHeap.add(num);
				num = maxHeap.poll();
			}
			minHeap.add(num);
		} else {
			if (minHeap.size() > 0 && num > minHeap.peek()) {
				minHeap.add(num);
				num = minHeap.poll();
			}
			maxHeap.add(num);
		}
	}

	public Integer getMedian() {
		int size = minHeap.size() + maxHeap.size();
		if (size == 0)
			throw new RuntimeException("No numbers yet.");
		if (size % 2 == 1) {
			return minHeap.peek();
		} else {
			return (minHeap.peek() + maxHeap.peek()) / 2;
		}
	}

	public static void main(String[] args) {
		Random r = new Random(System.nanoTime());
		int array[] = new int[20];
		MedianInStream2 m = new MedianInStream2();
		for(int i=0; i< array.length;i++){
			int num = r.nextInt(100);
			m.insert(num);
			array[i] = num;
		}
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			int j = array[i];
			System.out.printf("%d ",j);
		}
		System.out.println();
		System.out.println((array[(array.length-1)/2]+array[array.length/2])/2);
		System.out.println(m.getMedian());
	}

}
