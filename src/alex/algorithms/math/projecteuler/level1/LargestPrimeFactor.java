package alex.algorithms.math.projecteuler.level1;

public class LargestPrimeFactor {

	public static int largePrimeFactor(int n){
		int maxPrime = Integer.MIN_VALUE;
		for(int i=2; i*i <= Integer.MAX_VALUE;i++){
			boolean isPrime = true;
			for(int j=2; j*j<=i && isPrime;j++){
				if(i%j==0){
					isPrime = false;
				}
			}
			if(isPrime){
				if(n%i ==0 && i > maxPrime){
					maxPrime = i;
				}
			}
		}
		return maxPrime;
	}
	public static int largestPrime(long n){
		int [] sieve = new int[(int) (2*java.lang.Math.sqrt(n))];
		int maxPrime = Integer.MIN_VALUE;
		for(int i=2; i <sieve.length;i++){
			if(sieve[i]==0){
				if(n % i ==0){
					if(maxPrime < i){
						maxPrime =i;
					}
					if(n/i < i ){
						break;
					}
				}
				for(int j=2*i;j<sieve.length;j+=i){
					sieve[j]++;
				}
			}
		}
		return maxPrime;
	}
	
	public static void main(String[] args) {
		long x = 600851475143L;
		System.out.printf("%d\n", largestPrime(x));
		System.out.println(x/6857);
	}

}
