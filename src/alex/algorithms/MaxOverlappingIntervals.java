package alex.algorithms;

import java.util.Arrays;

public class MaxOverlappingIntervals {
	
	public static int maxOverlappingIntervalSorting(Pair[] pairs) {
		int max = 0;
		Arrays.sort(pairs);
		printArr(pairs);
		for(int i=1; i < pairs.length;i++){
			if(pairs[i-1].second >=pairs[i].first){
				max++;
			}
		}
		return max;
	}
	public static void printArr(Pair[] p){
		for (int i = 0; i < p.length; i++) {
			System.out.printf("%d %d ", p[i].first,p[i].second);
		}
		System.out.println();
	}
	public static void main(String[] args) {
		int[] arr = { 1, 3, 12, 14, 2, 4, 13, 15, 5, 10 };
		System.out.printf("%d\n", maxOverlappingIntervalSorting(Pair.toList(arr)));
	}

}

class Pair implements Comparable<Pair>{
	public int first;
	public int second;

	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}

	public static Pair[] toList(int[] arr) {
		Pair[] r = new Pair[arr.length / 2];

		for (int i = 0, j = 0; i < arr.length; i += 2, j++) {
			Pair p = new Pair(arr[i], arr[i + 1]);
			r[j] = p;
		}
		return r;
	}

	@Override
	public int compareTo(Pair o) {
		return this.first - o.first;
	}
}
