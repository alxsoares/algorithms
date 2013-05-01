package alex.algorithms.arrays;

import java.util.Arrays;

public class MininumDifference {
	private static MinMax minMax(Pointer[] pointers) {
		MinMax mm = new MinMax();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < pointers.length; i++) {
			Pointer a = pointers[i];
			if (a.val > max) {
				mm.maxP = a;
				max = a.val;
			}
			if (a.val < min) {
				mm.minP = a;
				min = a.val;
			}
		}
		return mm;
	}
	public static void myWay(int a[][]){
		int n = a.length;
		int k = a[0].length;
		Pointer p[] = new Pointer[n*k];
		int l=0;
		for(int i=0;i < n; i++){
			for(int j=0; j<k; j++){
				p[l++] = new Pointer(a[i][j], j, i);
			}
		}
		Arrays.sort(p);
		int minDiff = Integer.MAX_VALUE;
		MinMax mm = new MinMax();
		for(int i=0; i < n*k; i++){
			Pointer p1 = p[i];
			for(int j=i+1; j < n*k; j++){
				if(i==j) continue;
				Pointer p2 = p[j];
				if(p1.i!= p2.i){
					int diff = p2.val -p1.val;
					if( diff < minDiff ){
						minDiff = diff;
						mm.maxP = p2;
						mm.minP = p1;
					}else{
						break;
					}
				}
			}
		}
		System.out.printf("Mindiff %d, imax=%d, jmax=%d, imin=%d, imax=%d",
				minDiff, mm.maxP.i, mm.maxP.j, mm.minP.i, mm.minP.j);
	}
	public static void minimumDiff(int a[][]) {
		int n = a.length;
		int k = a[0].length;
		Pointer pointers[] = new Pointer[n];
		for (int i = 0; i < n; i++) {
			pointers[i] = new Pointer(a[i][0], 0, i);
		}
		MinMax mm = minMax(pointers);
		int minDiff = mm.maxP.val - mm.minP.val;
		MinMax m = new MinMax();
		m.maxP = mm.maxP; m.minP=mm.minP;
		m.minP.j++;
		while (m.minP.j < k) {
			int val = a[m.minP.i][m.minP.j];
			pointers[m.minP.i] = new Pointer(val, m.minP.j, m.minP.i);
			m = minMax(pointers);
			int diff = m.maxP.val - m.minP.val;
			if (diff < minDiff) {
				mm = m;
				minDiff = diff;
			} 
			m.minP.j++;
		
		}
		System.out.printf("Mindiff %d, imax=%d, jmax=%d, imin=%d, jmax=%d",
				minDiff, mm.maxP.i, mm.maxP.j, mm.minP.i, mm.minP.j);
	}

	
	public static void main(String[] args) {
		int a[][] = { { 6, 16, 67 }, { 11, 17, 68 }, { 10, 15, 100 } };
		minimumDiff(a);

	}
}

class Pointer implements Comparable<Pointer> {
	public Pointer(int val, int j, int i) {
		this.val = val;
		this.i = i;
		this.j = j;
	}

	public int val;
	public int i, j;

	@Override
	public int compareTo(Pointer o) {
		if (this.val > o.val)
			return 1;
		if (this.val < o.val)
			return -1;
		return 0;
	}
}

class MinMax {
	Pointer minP;
	Pointer maxP;

}
