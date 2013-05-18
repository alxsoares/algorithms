package alex.algorithms.math.projecteuler.level3;

import alex.algorithms.math.projecteuler.Eratosthenes;

public class Euler070 {
	static boolean isPerm(long m, long n) {
		int[] arr = new int[10];

		long temp = n;
		while (temp > 0) {
			arr[(int) (temp % 10)]++;
			temp /= 10;
		}

		temp = m;
		while (temp > 0) {
			arr[(int) (temp % 10)]--;
			temp /= 10;
		}

		for (int i = 0; i < 10; i++) {
			if (arr[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Integer[] primes = Eratosthenes.sieve(2000, 5000);
		long sol=1;
		long solPhi=1;
		double phiRatio =Double.POSITIVE_INFINITY;
		for(int i=0; i<primes.length;i++){
			for(int j=i+1;j<primes.length;j++){
				long n = primes[i]*primes[j];
				if(n > 10000000) break;
				long phi = (primes[i]-1)*(primes[j]-1);
				double ratio = ((double) n)/phi;
				if(isPerm(n, phi) && ratio < phiRatio){
					sol = n;
					phiRatio = ratio;
					solPhi = phi;
				}
			}
		}
		System.out.printf("%d %f %d\n", sol,phiRatio, solPhi);
	}

}
