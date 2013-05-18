package alex.algorithms.math.projecteuler.level3;

import java.util.ArrayList;
import java.util.TreeSet;

public class Euler074 {
	static int[] f = {1,1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
	static int sum(int num){
		int sum =0;
		while(num > 0){
			sum+=f[num%10];
			num = num/10;
		}
		
		return sum;
	}

	public static void main(String[] args) {
		TreeSet<Integer> chain = new TreeSet<>();
		int chainNumber = 0;
		for(int i=0;i<=1000000;i++){
			int num =i;
			while(!chain.contains(num)){
				chain.add(num);
				num = sum(num);
			}
			if(chain.size()==60){
				chainNumber++;
			}
			chain.clear();
		}
		System.out.printf("%d\n", chainNumber);
	}

}
