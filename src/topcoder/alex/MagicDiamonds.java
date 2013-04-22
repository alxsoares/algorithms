package topcoder.alex;

/**
 * You found n Magic Diamonds in the mountain. You are now thinking about
 * transfering them to your home. The only way you can transfer Magic Diamonds
 * is to use Transfer Magic one or more times.
 * 
 * 
 * 
 * The Magic Diamonds are very strange. For any positive integer x you can use
 * Transfer Magic to transfer x Magic Diamonds at once. However, if x is a prime
 * number, the Magic Diamonds will disappear instead of getting transferred. You
 * are not allowed to lose any of the Magic Diamonds, therefore you may never
 * use Transfer Magic on a prime number of Magic Diamonds. Your task is to
 * transfer all Magic Diamonds using Transfer Magic as few times as possible.
 * 
 */
public class MagicDiamonds {

	public boolean isPrime(long n) {
		if (n == 1)
			return false;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public long minimalTransfer(long N) {
		if (!isPrime(N))
			return 1;
		if (N == 3)
			return 3; 
		return 2;
	}

}
