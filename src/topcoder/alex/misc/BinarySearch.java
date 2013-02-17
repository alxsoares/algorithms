package topcoder.alex.misc;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
	public static int binarySearch(int array[], int target, int begin , int end){
		if(begin > end) throw new RuntimeException("Target no present in the array.");
		if(begin==end){
			if(array[begin]== target) return begin;
			throw new RuntimeException("Target no present in the array.");
		}
		int center = (begin +end)/2;
		if(array[center]==target) return center;
		if(target < array[center]) return binarySearch(array, target, begin, center-1);
		return binarySearch(array, target, center+1, end);
	}
	public static int iterBinarySearch(int array[], int target){
		int begin =0;
		int end = array.length-1;
		if(begin > end) throw new RuntimeException("Target not present.");
		while(true){
			if(begin > end )throw new RuntimeException("Target not present.");
			if(begin == end && array[begin]==target) return begin;
			int center = (begin + end)/2;
			if(array[center]==target) return center;
			else if(array[center] > target) end = center-1;
			else begin = center+1;
		}
	}
	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		int array[] = new int[200];
		for(int i=1; i < array.length;i++){
			array[i] = Math.abs(rand.nextInt())%300;
		}
		array[0]=14;
		Arrays.sort(array);
		int index = iterBinarySearch(array, 14);
		System.out.printf("%d %d\n", index, array[index]);
	}

}
