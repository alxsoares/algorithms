package topcoder.alex.amazon;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class PrimeNumbers {
	static int[] num = new int[1000];
	static void calculatePrimes(){
		Set<Integer> primes = new TreeSet<>();
		for(int i=2; i < num.length; i++){
			if(num[i]==0){
				primes.add(i);
				for(int j=2;j*i < num.length; j++ ){
					num[j*i] = 1;
				}
			}
		}
		System.out.printf("%d prime numbers\n",primes.size());
		for (Iterator<Integer> iterator = primes.iterator(); iterator.hasNext();) {
			Integer p =  iterator.next();
			System.out.printf("Numero %d is prime\n",p);
		}
	}
	public static void main(String[] args) {
		calculatePrimes();
	}

}
