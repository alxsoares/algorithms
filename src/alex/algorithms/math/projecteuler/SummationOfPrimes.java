package alex.algorithms.math.projecteuler;

public class SummationOfPrimes {

	public static void main(String[] args) {
		boolean [] sieve = new boolean[2000001];
		sieve[2] = false;
		long sum=0;
		for(int i=2; i<=2000000;i++){
			if(!sieve[i]){//isprime
				sum+=i;
			}
			for(int j=2*i; j<=2000000;j+=i){
				sieve[j] = true;
			}
		}
		System.out.printf("%d\n",sum);
	}

}
