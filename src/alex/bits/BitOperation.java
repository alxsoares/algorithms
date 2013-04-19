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
	public static int countNumberOfOnes(int n){
		int count =0;
		while(n > 0){
			n = n & (n-1);
			count++;
		}
		return count;
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
	
	public static int diffBits2(int a, int b) {
		int count = 0;
		for (int c = a ^ b; c != 0; c = c&(c-1)) {
			count ++;
		}
		return count;
	}

	public static int swappOddEvenBits(int n) {
		return ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
	}

	public static boolean isBitPalindrome(int n) {
		int m = reverseBits(n);
		int r = m ^ n;
		if (r == 0)
			return true;
		return false;
	}

	public static int reverse(int i) {
		i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
		i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
		i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
		i = (i << 24) | ((i & 0xff00) << 8) | ((i >>> 8) & 0xff00) | (i >>> 24);
		return i;
	}

	public static int reverseBits(int n) {
		int m = 0;
		for (int i = 0; i < 32; i++) {
			int temp = 1 & (n >> i);
			m = m | (temp << (31 - i));
		}
		return m;
	}

	public static int reverseBits2(int n) {
		int r = 0;
		for (int i = 0; i < 32; i++) {
			r <<= 1;
			r = r | (1 & n);
			n >>= 1;
		}
		return r;
	}
	public static int negate(int a){
		return (~a)+1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.printf("%d\n", Integer.reverse(0XF0FFFF0F));
		System.out.println(isBitPalindrome(0XF0FFFF0F));
		int i = 0X0000FFFF;
		System.out.printf("%s %s %s\n", Integer.toBinaryString(i),
				Integer.toBinaryString(reverseBits(i)),
				Integer.toBinaryString(reverseBits2(i)));
		System.out.printf("%d %d %d %d\n", Integer.reverse(i), reverse(i),
				reverseBits(i), reverseBits2(i));
		System.out.printf("Num of ones =%d\n",countNumberOfOnes(3));
		
		System.out.println(negate(12));
		System.out.println(negate(-1));
		
		int xor = 14;
		int rightMost = xor & ~(xor -1);
		System.out.println(Integer.toBinaryString((rightMost)));
		System.out.printf("Diff Bits =%d\n",diffBits(2,3));
		System.out.printf("Diff Bits =%d\n",diffBits(10,8));
		System.out.printf("Diff Bits =%d\n\n",diffBits(16,8));
		
		System.out.printf("Diff Bits =%d\n",diffBits2(2,3));
		System.out.printf("Diff Bits =%d\n",diffBits2(10,8));
		System.out.printf("Diff Bits =%d\n",diffBits2(16,8));
	}

}
