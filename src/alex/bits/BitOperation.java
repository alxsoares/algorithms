package alex.bits;

public class BitOperation {
	public static boolean getBit(int n, int i) {
		return ((n & (1 << i)) > 0);
	}

	public static int setBit(int n, int i, boolean b) {
		if (b) {
			return (n | (1 << i));
		} else {
			return n & (~(1 << i));
		}
	}

	public static int getPreviousBigger(int n) {
		if (n <= 0)
			return -1;
		int index = 0;
		int countZeros = 0;
		// encontra primeiro zero
		while (getBit(n, index))
			index++;
		// Desliga próximo 1
		while (!getBit(n, index)) {
			index++;
			countZeros++;
		}
		n = setBit(n, index, false);
		index--;
		n = setBit(n, index, true);
		countZeros--;
		for (int i = index - 1; i >= countZeros; i--) {
			n = setBit(n, i, true);
		}
		for (int i = countZeros - 1; i >= 0; i--) {
			n = setBit(n, i, false);
		}
		return n;
	}

	public static int getNextLess(int n) {
		if (n <= 0)
			return -1;
		int index = 0;
		int countOnes = 0;
		while (!getBit(n, index))
			index++;
		while (getBit(n, index)) {
			index++;
			countOnes++;
		}
		n = setBit(n, index, true);
		index--;
		n = setBit(n, index, false);
		countOnes--;
		// mover 1 para direita.
		for (int i = index - 1; i >= (countOnes); i--) {
			n = setBit(n, i, false);
		}
		for (int i = countOnes - 1; i >= 0; i--) {
			n = setBit(n, i, true);
		}
		return n;
	}

	public static boolean isPowerOfTwo(int n) {
		// n=0001000.....
		return ((n & (n - 1)) == 0);
	}

	public static int updateBits(int n, int m, int i, int j) {
		int allOnes = ~0;
		// 1110000000000...
		int left = allOnes - ((1 << j) - 1);
		// 00000000001111
		int right = ((1 << i) - 1);

		int mask = left | right;

		return (mask & n) | (m << i);
	}

	public static int diffBits(int a, int b) {
		int count = 0;
		for (int c = a ^ b; c != 0; c = c >> 1) {
			count += c & 1;
		}
		return count;
	}

	public static int swappOddEvenBits(int n) {
		return ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
