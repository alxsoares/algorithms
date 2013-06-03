package alex.algorithms.arrays;

import java.util.Arrays;

public class ProductArrayPuzzle {
	public static void product(int a[]) {
		if (a == null || a.length <= 1) {
			return;
		}
		int n = a.length;
		int left[] = new int[n];
		int right[] = new int[n];
		left[0] = 1;
		right[n - 1] = 1;
		for (int i = 1; i < n; i++) {
			left[i] = a[i - 1] * left[i - 1];
		}
		for (int i = n - 2; i >= 0; i--) {
			right[i] = a[i + 1] * right[i + 1];
		}
		for(int i=0; i<n;i++){
			System.out.printf("%d ", right[i]*left[i]);
		}
		System.out.println();
	}
	public static void product2(int a[]) {
		if (a == null || a.length <= 1) {
			return;
		}
		int n = a.length;
		int prod[] = new int[n];
		Arrays.fill(prod, 1);
		int temp =1;
		for(int i=0;i<n;i++){
			prod[i] = temp;
			temp*=a[i];
		}
		temp=1;
		for(int i=n-1;i>=0;i--){
			prod[i]*=temp;
			temp*=a[i];
		}
		for (int i = 0; i < prod.length; i++) {
			System.out.printf("%d ", prod[i]);
		}
		System.out.println();
	}
	public static void main(String[] args) {
		int arr[] = {10, 3, 5, 6, 2};
		product(arr);
		product2(arr);
	}

}
