package alex.algorithms.math.projecteuler;

public class Euler128 {
	public static boolean isPrime(long n) {
		if (n < 2)
			return false;
		if (n < 4)
			return true;
		if (n % 2 == 0)
			return false;
		if (n < 9)
			return true;
		if (n % 3 == 0)
			return false;
		if (n < 25)
			return true;

		int s = (int) Math.sqrt(n);
		for (int i = 5; i <= s; i += 6) {
			if (n % i == 0)
				return false;
			if (n % (i + 2) == 0)
				return false;
		}

		return true;
	}

	public static void main(String[] args) {
		int count = 1;
        int limit = 2000;
        long n = 0;
        long number = 0;

        while (count < limit) {
            n++;
            if (isPrime(6 * n - 1) && isPrime(6 * n + 1) && isPrime(12 * n + 5)) {
                count++;
                number = (3 * n * n - 3*n + 2);
                if (count >= limit) break;

            }
            if (isPrime(6 * n + 5) && isPrime(6 * n - 1) && isPrime(12 * n - 7) && n != 1) {
                count++;
                number = (3 * n * n + 3*n + 1);                                       
            }             
        }   
        System.out.println(number);
	}
}
