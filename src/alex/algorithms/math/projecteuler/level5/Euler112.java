package alex.algorithms.math.projecteuler.level5;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Euler112 {

	private static int msum(int n) {
		int[] m = new int[n + 1];
		Set<BitSet> curgen = new HashSet<BitSet>();
		int sum = 0;
		m[1] = 0;
		BitSet progenitor = new BitSet();
		progenitor.set(1);
		curgen.add(progenitor);
		int generation = 0;
		while (curgen.size() > 0) {
			Set<BitSet> nextgen = new HashSet<BitSet>();
			generation++;
			for (Iterator<BitSet> it = curgen.iterator(); it.hasNext();) {
				BitSet product = it.next();
				int a = product.length() - 1;
				for (int b = product.nextSetBit(0); b >= 0; b = product
						.nextSetBit(b + 1)) {
					BitSet child = (BitSet) product.clone();
					child.set(a + b);
					if (a + b <= n) {
						if (m[a + b] == 0) {
							m[a + b] = generation;
							sum += generation;
						}
						if (m[a + b] == generation)
							nextgen.add(child);
					}
				}
			}
			curgen = nextgen;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(msum(200));
	}

}
