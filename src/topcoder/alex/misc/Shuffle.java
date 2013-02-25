package topcoder.alex.misc;

import java.util.Random;

public class Shuffle {

	public static void shuffle(int array[]) {
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < array.length; i++) {
			int index = rand.nextInt(array.length-i) + i;
			int temp = array[i];
			array[i] = array[index];
			array[index] = temp;
		}
	}
	//the same
	public static void FisherYatesShuffling(int array[]){
		int N = array.length;
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < N; i++) {
            int r = rand.nextInt(array.length-i) + i;
            int swap = array[r];
            array[r] = array[i];
            array[i] = swap;
        }
	}
	public static int rand(int lower, int higher){
		Random rand = new Random(System.currentTimeMillis());
		int index = lower+ (lower+rand.nextInt(higher-lower-1));
		return index;
	}
	public static int[] pickSubSet(int original[], int k){
		int subset[] = new int[k];
		int array[] = new int[original.length];
		System.arraycopy(original, 0, array, 0, original.length);
		for(int i=0; i < k; i++){
			int index = rand(i, array.length-1);
			subset[i] = array[index];
			array[index] = array[i];
		}
		return subset;
	}
	public static void main(String[] args) {
		int array[] = { 1, 2, 3, 4, 5, 6 };
		shuffle(array);
		for (int i = 0; i < array.length; i++) {
			System.out.printf("%d ", array[i]);
		}
		System.out.println();
		int subset[] = pickSubSet(array, 3);
		for (int i = 0; i < subset.length; i++) {
			System.out.printf("%d ", subset[i]);
		}
		System.out.println();
		
	}

}
