package alex.algorithms.arrays;

public class ReorderEvenOdd {
	public static void reorderEvenOdd(int[] array, Criterion c) {
		int begin = 0;
		int end = array.length - 1;
		while (begin < end) {
			while (begin < end && !c.check(array[begin])) {
				begin++;
			}
			while (begin < end && c.check(array[end])) {
				end--;
			}
			if (begin < end) {
				int a = array[begin];
				array[begin] = array[end];
				array[end] = a;
			}
		}
	}

	public static void main(String[] args) {
		int array[] = {1,2,3,4,5,6,8,6,6,66,6,6,6,7,7,7,7,7,7,7};
		Criterion c = new Criterion() {
			
			@Override
			public boolean check(int num) {
				if((num & 1)==1) return true;
				return false;
			}
		};
		reorderEvenOdd(array, c);
		for (int i = 0; i < array.length; i++) {
			System.out.printf("%d ", array[i]);
		}
		System.out.println();
	}

}

interface Criterion {
	boolean check(int num);
}
