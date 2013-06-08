package alex.algorithms.math.projecteuler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Copied from forum.
 * 
 */
public class Euler375 {
	/**
	 * Determine the new accumulator after
	 * 
	 * @param blocks
	 *            blocks are run.
	 */
	public static TreeMap<Integer, BigInteger> findAccumulator(int blockSize,
			int minValue, TreeMap<Integer, BigInteger> base,
			TreeMap<Integer, BigInteger> diff, int blocks) {
		TreeMap<Integer, BigInteger> result = new TreeMap<Integer, BigInteger>(
				base);

		// At the point where the accumulator is checked, we have
		// (blocks + 2) blocks of size blockSize, or blockSize * (blocks + 2)
		// entries. The values in the accumulator should sum to this count.
		BigInteger minCount = BigInteger.valueOf(blocks)
				.add(BigInteger.valueOf(2))
				.multiply(BigInteger.valueOf(blockSize));
		// long minCount = blockSize * (blocks + 2);

		// Add in the deltas for each block
		for (Entry<Integer, BigInteger> entry : diff.entrySet()) {
			BigInteger v = result.get(entry.getKey());
			BigInteger valCount = entry.getValue()
					.multiply(BigInteger.valueOf(blocks))
					.add((v != null) ? v : BigInteger.ZERO);

			minCount = minCount.subtract(valCount);

			result.put(entry.getKey(), valCount);
		}
		result.put(minValue, minCount);

		return result;
	}

	/**
	 * Determine the new sum after
	 * 
	 * @param blocks
	 *            blocks are run.
	 */
	public static BigInteger findTotal(int blockSize, int blockCount,
			BigInteger nonMinPerBlock, int minValue, BigInteger nonMinCount) {
		BigInteger total = nonMinPerBlock.multiply(BigInteger
				.valueOf(blockCount));

		BigInteger rHigh = BigInteger.valueOf(blockCount)
				.add(BigInteger.valueOf(2))
				.multiply(BigInteger.valueOf(blockSize));
		BigInteger rLow = BigInteger.valueOf(2).multiply(
				BigInteger.valueOf(blockSize));

		BigInteger expectedMin = rHigh.multiply(rHigh.add(BigInteger.ONE))
				.divide(BigInteger.valueOf(2));
		expectedMin = expectedMin.subtract(rLow.multiply(
				rLow.add(BigInteger.ONE)).divide(BigInteger.valueOf(2)));

		expectedMin = expectedMin.subtract(nonMinCount.multiply(BigInteger
				.valueOf(blockCount)));

		total = total.add(BigInteger.valueOf(minValue).multiply(expectedMin));

		return total;
	}

	/**
	 * Given the current accumulator and order list, and a new value x, return
	 * the new accumulator and order list.
	 * 
	 * @param val
	 * @param acc
	 * @param N
	 * @return
	 */
	public static BigInteger fold(TreeMap<Integer, BigInteger> val,
			BigInteger acc, int x) {
		BigInteger count = BigInteger.ZERO;

		Iterator<Entry<Integer, BigInteger>> it = val.tailMap(x).entrySet()
				.iterator();

		while (it.hasNext()) {
			Entry<Integer, BigInteger> entry = it.next();
			count = count.add(entry.getValue());

			acc = acc.subtract(entry.getValue().multiply(
					BigInteger.valueOf(entry.getKey())));
			it.remove();
		}

		val.put(x, count.add(BigInteger.ONE));
		acc = acc
				.add(count.add(BigInteger.ONE).multiply(BigInteger.valueOf(x)));

		return acc;
	}

	public static long hardN(List<Integer> values, int N) {
		ArrayList<Integer> si = new ArrayList<Integer>();

		while (si.size() <= N) {
			si.addAll(values);
		}

		long sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = i; j <= N; j++) {
				Integer min = null;

				for (int p = i; p <= j; p++) {
					int val = si.get(p - 1);
					if (min == null || val < min) {
						min = val;
					}
				}
				sum += min;
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		List<Integer> a = new ArrayList<Integer>();

		long s = 290797L;
		long mod = 50515093L;
		long nv = (s * s) % mod;

		s = nv;

		do {
			a.add((int) s);
			s = (s * s) % mod;
		} while (s != nv);

		System.out.println(runN(a, 2000000000));
	}

	public static BigInteger runN(List<Integer> values, int N) {
		TreeMap<Integer, BigInteger> runMap = new TreeMap<Integer, BigInteger>();
		BigInteger sum = BigInteger.ZERO;
		int count = 0;
		BigInteger minCount = BigInteger.ZERO;
		Integer min = Collections.min(values);

		BigInteger acc = BigInteger.ZERO;

		// Run the first block.
		for (int v : values) {
			acc = fold(runMap, acc, v);
			if (runMap.containsKey(min)) {
				minCount = minCount.add(runMap.get(min));
			}
			sum = sum.add(acc);
			count++;
			if (count >= N) {
				return sum;
			}
		}

		// Figure out the accumulator contents after the first block
		TreeMap<Integer, BigInteger> checkpoint = new TreeMap<Integer, BigInteger>(
				runMap);
		BigInteger checkpointSum = sum.subtract(BigInteger.valueOf(min)
				.multiply(minCount));
		checkpoint.remove(min);

		BigInteger nonMinCount = BigInteger.ZERO;

		// Run the second block, using it as a reference for the next T complete
		// blocks.
		for (int v : values) {
			acc = fold(runMap, acc, v);
			if (runMap.containsKey(min)) {
				minCount = minCount.add(runMap.get(min));
			}
			for (BigInteger nonMinPiece : runMap.tailMap(min, false).values()) {
				nonMinCount = nonMinCount.add(nonMinPiece);
			}

			sum = sum.add(acc);
			count++;
			if (count >= N) {
				return sum;
			}
		}

		// Compare the change between the first and second blocks
		TreeMap<Integer, BigInteger> blockDiff = new TreeMap<Integer, BigInteger>(
				runMap);
		BigInteger blockSum = sum.subtract(BigInteger.valueOf(min).multiply(
				minCount));
		blockDiff.remove(min);
		for (Entry<Integer, BigInteger> entry : blockDiff.entrySet()) {
			entry.setValue(entry.getValue().subtract(
					checkpoint.get(entry.getKey())));
		}

		// Now see where we end up after running the middle blocks
		int extraBlocks = (N - 2 * values.size()) / values.size();
		sum = sum.add(findTotal(values.size(), extraBlocks,
				blockSum.subtract(checkpointSum), min, nonMinCount));
		runMap = findAccumulator(values.size(), min, runMap, blockDiff,
				extraBlocks);
		count += extraBlocks * values.size();

		// Reset the accumulator to match the new contents.
		acc = BigInteger.ZERO;
		for (Entry<Integer, BigInteger> entry : runMap.entrySet()) {
			acc = acc.add(entry.getValue().multiply(
					BigInteger.valueOf(entry.getKey())));
		}

		if (count >= N) {
			return sum;
		}

		// Now run the final block
		for (int v : values) {
			acc = fold(runMap, acc, v);
			sum = sum.add(acc);
			count++;
			if (count >= N) {
				return sum;
			}
		}

		return sum;
	}
}
