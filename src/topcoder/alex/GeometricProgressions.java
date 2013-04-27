package topcoder.alex;

import java.util.Set;
import java.util.TreeSet;

public class GeometricProgressions {
	public int count(int b1, int q1, int n1, int b2, int q2, int n2) {
		long a1 = b1, a2 = b1, mod1 = 1000000007, mod2 = 1000000009;
		Set<Pair> s = new TreeSet<Pair>();
		while (n1 > 0) {
			s.add(new Pair(a1, a2));
			a1 *= q1;
			a2 *= q1;
			a1 %= mod1;
			a2 %= mod2;
			n1--;
		}
		a1 = b2;
		a2 = b2;
		while (n2 > 0) {
			s.add(new Pair(a1, a2));
			a1 *= q2;
			a2 *= q2;
			a1 %= mod1;
			a2 %= mod2;
			n2--;
		}
		return s.size();
	}

	public static void main(String[] args) {
		System.out.println(new GeometricProgressions().count(3, 2, 5, 6, 2, 5));
	}

}

class Pair implements Comparable<Pair> {
	long first, second;

	public Pair(long first, long second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public int compareTo(Pair o) {
		if (this.first > o.first)
			return 1;
		if (this.first < o.first)
			return -1;
		if (this.second > o.second)
			return 1;
		if (this.second < o.second)
			return -1;
		return 0;
	}

}
