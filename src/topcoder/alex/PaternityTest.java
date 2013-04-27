package topcoder.alex;

import java.util.BitSet;

public class PaternityTest {

	public int[] possibleFathers(String child, String mother, String[] men) {
		BitSet motherPos = compareDNA(child, mother);

		int c = 0;
		int[] fathers = new int[men.length];

		for (int i = 0; i < men.length; ++i)
			if (isFather(child, motherPos, men[i]))
				fathers[c++] = i;

		int[] res = new int[c];
		System.arraycopy(fathers, 0, res, 0, c);
		return res;
	}

	private BitSet compareDNA(String child, String parent) {
		BitSet pos = new BitSet(child.length());

		for (int i = 0; i < child.length(); ++i)
			pos.set(i, child.charAt(i) == parent.charAt(i));

		return pos;
	}

	private boolean isFather(String child, BitSet motherPos, String father) {
		BitSet fatherPos = compareDNA(child, father);

		if (fatherPos.cardinality() < child.length() / 2)
			return false;

		fatherPos.or(motherPos);

		if (fatherPos.cardinality() < child.length())
			return false;

		return true;
	}

}
