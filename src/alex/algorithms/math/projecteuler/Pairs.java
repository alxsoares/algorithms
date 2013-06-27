package alex.algorithms.math.projecteuler;

import topcoder.alex.misc.Pair;

public class Pairs {

	public static Pair<? extends Long, ? extends Long> of(long count, long sum) {
		return new Pair<Long, Long>(count, sum);
	}

}
