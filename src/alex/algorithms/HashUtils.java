package alex.algorithms;

public class HashUtils {
	public static int stringHash(String s, int radix, int m) {
		if (s == null)
			return 0;
		int r = s.length();
		int hashValue = 0;
		for (int i = r - 1; i >= 0; i--) {
			int prod = 0xFF & s.charAt(i);
			for (int j = r - i - 1; j > 0; j--) {
				prod *= radix;
				prod %= m;
			}
			hashValue += prod;
			hashValue %= m;
		}
		return hashValue;
	}

	/**
	 * n=c0+128(c1+128(c2+...+128(cr−2+128⋅cr−1)…))
	 */
	public static int stringHash2(String s, int radix, int m) {
		int hashValue = 0;
		int r = s.length();

		for (int i = 0; i < r; ++i) {
			hashValue = (hashValue * radix + s.charAt(i)) % m;
		}
		return hashValue;
	}

	public static void main(String[] args) {
		
	}

}
