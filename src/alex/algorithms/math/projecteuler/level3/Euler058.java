package alex.algorithms.math.projecteuler.level3;

public class Euler058 {
	static boolean isPrime(int n) {
		int upperBound = (int) Math.sqrt(n);
		for (int i = 2; i <= upperBound; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int primes = 3;
        int sideLength = 2;
        int corner = 9;

        while( ((double)primes)/(2*sideLength+1) > 0.10){
            sideLength += 2;
            for(int i = 0; i < 3; i++){
                corner += sideLength;
                if(isPrime(corner)) primes++;                    
            }
            corner+= sideLength;                
        }       
        System.out.printf("%d\n", sideLength);
	}

}
