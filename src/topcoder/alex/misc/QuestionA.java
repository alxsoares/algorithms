package topcoder.alex.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuestionA {
	public interface Stream {
		public char getNext();

		public boolean hasNext();
	}

	public static char firstChar(final Stream input) {
		LinkedList<String> notRepeated = new LinkedList<String>();
		HashSet<String> repeadTed = new HashSet<String>();
		while (input.hasNext()) {
			char c = input.getNext();
			String charStr = String.valueOf(c);
			if (notRepeated.contains(charStr)) {
				notRepeated.remove(charStr);
				repeadTed.add(charStr);
			} else if (!repeadTed.contains(charStr)) {
				notRepeated.add(charStr);
			}
		}

		if (!notRepeated.isEmpty()) {
			return notRepeated.get(0).charAt(0);
		}
		return '0';
	}

	public static int[] lis2(final int array[]) {
		int dp[] = new int[array.length];
		dp[0] = 1;
		for (int i = 1; i < array.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[i] > array[j]) {
					if (dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
					}
				}
			}
		}
		return dp;
	}

	public static int maxAthletes(final Integer n,
			final List<Pair<Integer, Integer>> parameterList) {
		Collections.sort(parameterList,
				new Comparator<Pair<Integer, Integer>>() {
					@Override
					public int compare(final Pair<Integer, Integer> o1,
							final Pair<Integer, Integer> o2) {
						if (o1.m > o2.m)
							return 1;
						if (o1.m < o2.m)
							return -1;
						if (o1.m == o2.m) {
							if (o1.s > o2.s)
								return 1;
							if (o1.s < o2.s)
								return -1;
						}
						return 0;
					}
				});
		int tower[] = new int[parameterList.size()];
		int totalWeight[] = new int[parameterList.size()];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < parameterList.size(); i++) {
			tower[i] = 1;
			totalWeight[i] = parameterList.get(i).m;
			Pair<Integer, Integer> c = null;
			for (int j = 0; j < i; j++) {
				if (tower[i] < tower[j] + 1
						&& totalWeight[j] <= parameterList.get(i).s) {
					tower[i] = tower[j] + 1;
					totalWeight[i] = parameterList.get(i).m + totalWeight[j];
					if (tower[i] > max) {
						max = tower[i];
						c = parameterList.get(i);
					}
				}
			}
			if(c!= null)
			System.out.printf("%d %d\n", c.m, c.s);
		}
		return max;
	}

	public static int maxAthletes2(final Integer n,
			final List<Pair<Integer, Integer>> parameterList) {
		Collections.sort(parameterList,
				new Comparator<Pair<Integer, Integer>>() {
					@Override
					public int compare(final Pair<Integer, Integer> o1,
							final Pair<Integer, Integer> o2) {
						if (o1.m > o2.m)
							return 1;
						if (o1.m < o2.m)
							return -1;
						if (o1.m == o2.m) {
							if (o1.s > o2.s)
								return 1;
							if (o1.s < o2.s)
								return -1;
						}
						return 0;
					}
				});
		int max = 1;
		int tw = parameterList.get(0).m;
		for (int i = 1; i < parameterList.size(); i++) {
			Pair<Integer, Integer> p = parameterList.get(i);
			if (p.s >= tw) {
				max++;
				tw += p.m;
			}
		}
		return max;
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		String[] arr = { "y", "x", "a", "A", "b", "B", "A", "B", "a", "c", "y" };
		List<Pair<Integer, Integer>> l = new ArrayList<Pair<Integer, Integer>>();
		l.add(new Pair<Integer, Integer>(3, 4));
		l.add(new Pair<Integer, Integer>(2, 2));
		l.add(new Pair<Integer, Integer>(7, 6));
		l.add(new Pair<Integer, Integer>(4, 5));
		// l.add(new Pair<Integer, Integer>(1, 1));
		// l.add(new Pair<Integer, Integer>(1, 1));
		// l.add(new Pair<Integer, Integer>(10, 1000));
		// l.add(new Pair<Integer, Integer>(2, 3));
		// l.add(new Pair<Integer, Integer>(2, 2));
		// l.add(new Pair<Integer, Integer>(7, 6));
		// l.add(new Pair<Integer, Integer>(4, 5));
		// l.add(new Pair<Integer, Integer>(11, 2000));
		Random r = new Random();
		for (int i = 0; i < 200; i++) {
			int m = Math.abs(r.nextInt())%1000;
			int s = m + Math.abs(r.nextInt())%1000;
			l.add(new Pair<Integer, Integer>(m, s));
		}
		System.out.println(maxAthletes(4, l));
		System.out.println(maxAthletes2(4, l));

	}

}
