package topcoder.alex.misc;

import java.util.ArrayList;
import java.util.Collections;

public class MaxTower {
	public static int maxTower(ArrayList<Pair2<Integer, Integer>> arr ) {
		Collections.sort(arr);
		int max[] = new int[arr.size()];
		int number = Integer.MIN_VALUE;
		for (int i = 0; i < arr.size(); i++) {
			max[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr.get(j).h < arr.get(i).h && arr.get(j).w < arr.get(i).w) {
					if (max[i] < max[j] + 1) {
						max[i] = max[j] + 1;
						number = Math.max(number, max[i]);
					}
				}
			}
		}
		return number;
	}

	public static void main(String[] args) {
		ArrayList<Pair2<Integer, Integer>> l = new ArrayList<>();
		l.add(new Pair2<Integer, Integer>(65, 100));
		l.add(new Pair2<Integer, Integer>(70, 150));
		l.add(new Pair2<Integer, Integer>(56, 90));
		l.add(new Pair2<Integer, Integer>(75, 190));
		l.add(new Pair2<Integer, Integer>(60, 95));
		l.add(new Pair2<Integer, Integer>(68, 110));
		System.out.println(maxTower(l));
	}

}

class Pair2<T extends Comparable<T>, D extends Comparable<D>> implements
		Comparable<Pair2<T, D>> {

	public T w;
	public D h;

	public Pair2(D h, T w) {
		this.w = w;
		this.h = h;
	}

	public int compareTo(Pair2<T, D> o) {
		int r = this.h.compareTo(o.h);
		if (r == 0) {
			return this.w.compareTo(o.w);
		}
		return r;
	}

}
