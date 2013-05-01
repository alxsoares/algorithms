package topcoder.alex;

public class FruitTrees {

	int gcd(int a, int b) {
		while (b != 0) {
			int c = b;
			b = a % b;
			a = c;
		}
		return a;
	}

	int mind(int x, int m, int y, int n) {
		// assume x < y, else just swap
		if (x < y) {
			return mind(y, n, x, m);
		}
		// Offset y -> 0, x -> x - y
		x -= y;
		int g = gcd(m, n);
		return Math.min(x % g, (g - x % g) % g);

	}

	public int maxDist(int apple, int kiwi, int grape) {
		int res = -1;
		// fix grape tree at 0:
		for (int a = 0; a < apple; a++) {
			for (int k = 0; k < kiwi; k++) {
				// minimum distance between apple and grape trees
				int p = mind(a, apple, 0, grape);
				// minimum distance between kiwi and grape trees
				int q = mind(k, kiwi, 0, grape);
				// minimum distance between apple and kiwi trees
				int r = mind(a, apple, k, kiwi);
				// The minimum of them all is the result:
				res = Math.max(res, Math.min(Math.min(p, q), r));
			}
		}
		return res;
	}

	public static void main(String[] args) {
 
		FruitTrees ft = new FruitTrees();
		System.out.println(ft.maxDist(2000, 2000, 2000));
	}

}
