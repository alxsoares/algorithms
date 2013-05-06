package alex.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumKNumvers {

	public static void printKMinimumNumbers(int[] input, int k) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(1, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		for (int i = 0; i < input.length; i++) {
			if(i < k){
				maxHeap.add(input[i]);
			}else{
				int max = maxHeap.peek();
				if(input[i] < max){
					maxHeap.poll();
					maxHeap.add(input[i]);
				}
			}
		}
		while(!maxHeap.isEmpty()){
			System.out.printf("%d ", maxHeap.poll());
		}
	}

	public static void main(String[] args) {
		int input[] = {10,1,9,12,334,5,5,5,5,1,20,-1};
		printKMinimumNumbers(input, 4);
	}

}
