package alex.algorithms.sorting;

public class SelectionSort {
	public static void sort(int a[]){
		for(int i=0 ; i < a.length-1; i++){
			int min =i;
			for(int j=i+1; j< a.length;j++){
				if(a[min] > a[j]){
					min =j;
				}
			}
			if(min!= i){
				swap(a,i,min);
			}
		}
	}
	private static void swap(int[] a, int i, int min) {
		int aux = a[i];
		a[i] = a[min];
		a[min] = aux;
	}
	public static void main(String[] args) {
		int arr[] ={70, 1, 2,3,4,5,6, 80, 8, 9};
		sort(arr);
		String comma="";
		for (int i = 0; i < arr.length; i++) {
			int j = arr[i];
			System.out.print(comma+j);
			comma=",";
		}
	}

}
