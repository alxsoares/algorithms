package alex.algorithms.arrays;

import java.util.Arrays;

public class TwoArraysIntersection {
	public static int[] twoArraysIntersection(int a[], int b[]) {
		int[] inter = new int[Math.max(a.length, b.length)];
		Arrays.sort(a);
		Arrays.sort(b);
		int index = 0;
		int i = 0;
		int j = 0;
		while (i < a.length && j < b.length) {
			if (a[i] == b[j]) {
				inter[index++] = a[i];
				i++;
				j++;
			}else if(a[i] < b[j]){
				i++;
			}else if(b[j] < a[i]){
				j++;
			}
		}
		for(int x=0; x< index;x++){
			System.out.printf("%d ", inter[x]);
		}
		return inter;
	}

	public static void main(String[] args) {
		int a[] = {100,3,1,2,200,10,5};
		int b[] = {-100,1,2,-200,3,10,5};
		twoArraysIntersection(a, b);
	}

}
