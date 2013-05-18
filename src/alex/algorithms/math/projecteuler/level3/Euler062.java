package alex.algorithms.math.projecteuler.level3;

import java.util.HashMap;

public class Euler062 {

	static long largestPermutation(long n) {
		int digits[] = new int[10];
		long nn = n;
		while (nn > 0) {
			digits[(int) (nn % 10)]++;
			nn = nn / 10;
		}
		long perm = 0;
		for (int i = 9; i >= 0; i--) {
			for (int j = 0; j < digits[i]; j++) {
				perm = perm * 10 + i;
			}
		}
		return perm;
	}

	public static void main(String[] args) {
		long res =0;
		HashMap<Long, Integer> cubes= new HashMap<>();
		HashMap<Long, Long> track= new HashMap<>();
		boolean found = false;
		for(long n=345;!found;n++){
			long perm = largestPermutation(n*n*n);
			if(!cubes.containsKey(perm)){
				cubes.put(perm, 1);
				track.put(perm, n);
			}else{
				cubes.put(perm,cubes.get(perm)+1);
			}
			if(cubes.get(perm)==5){
				res = track.get(perm);
				System.out.printf("%d\n", res);
				found = true;
			}
		}
		System.out.printf("%d\n", res*res*res);
	}

}
