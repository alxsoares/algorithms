package alex.algorithms.math.projecteuler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

public class Euler233 {
	// static long limit = 100000000000L;
	// static long limit2 = (limit / (5 * 5 * 5 * 13 * 13));
	// static int limit3 = (int) (limit2 / 17 + 1);
	//
	// public static void main(String[] args) {
	// ArrayList<Long> bases = new ArrayList<Long>();
	// ArrayList<Long> fourKPlusOnePrimes = new ArrayList<Long>();
	// long limit = 100000000000l;
	//
	// ArrayList<Long> multipliers = new ArrayList<Long>();
	//
	// Integer[] candidatePrimes = Eratosthenes.sieve(2, 4733728);
	//
	// for (int i = 1; i < candidatePrimes.length; i++) {
	// if ((candidatePrimes[i] - 1) % 4 == 0) {
	// fourKPlusOnePrimes.add(candidatePrimes[i].longValue());
	// }
	// }
	// long tenthPower = fourKPlusOnePrimes.get(0);
	// int a = 1;
	// long candidate = (long) Math.pow(fourKPlusOnePrimes.get(a), 2)
	// * (long) Math.pow(tenthPower, 10);
	// while (candidate <= limit) {
	// bases.add(candidate);
	// candidate = (long) Math.pow(fourKPlusOnePrimes.get(++a), 2)
	// * (long) Math.pow(tenthPower, 10);
	// }
	//
	//
	// for (int seventhPower = 0; seventhPower < 3; seventhPower++) {
	// long qToPower7 = (long) Math.pow(
	// fourKPlusOnePrimes.get(seventhPower), 7);
	// long qToPowerThree;
	// for (int thirdPower = 0; (qToPowerThree = (long) Math.pow(
	// fourKPlusOnePrimes.get(thirdPower), 3)) <= (limit / qToPower7);
	// thirdPower++) {
	// if (thirdPower != seventhPower) {
	// candidate = qToPower7 * qToPowerThree;
	// bases.add(candidate);
	// }
	// }
	// }
	// long qToPowerThree;
	// long qToPowerTwo;
	// long qToPowerOne;
	// long partialResult;
	//
	// long maxResult = 0;
	// long[] maxResFactors = new long[3];
	//
	// int numPrimesInArray = fourKPlusOnePrimes.size();
	//
	// for (int thirdPower = 0; (qToPowerThree = (long) Math.pow(
	// fourKPlusOnePrimes.get(thirdPower), 3)) <= (limit / (25 * 13));
	// thirdPower++) {
	// long dividedLimit = (limit / (qToPowerThree * ((thirdPower == 0) ? 13
	// : 5)));
	// for (int secondPower = 0; (qToPowerTwo = (long) Math.pow(
	// fourKPlusOnePrimes.get(secondPower), 2)) <= dividedLimit; secondPower++)
	// {
	// partialResult = qToPowerThree * qToPowerTwo;
	//
	// if ((secondPower != thirdPower)
	// && (partialResult <= (limit / 5))) {
	// for (int firstPower = 0; firstPower < numPrimesInArray
	// && (qToPowerOne = fourKPlusOnePrimes
	// .get(firstPower)) <= (limit / (partialResult)); firstPower++) {
	// long fullResult = partialResult * qToPowerOne;
	// if (firstPower != secondPower
	// && firstPower != thirdPower
	// && fullResult <= limit) {
	//
	// bases.add(fullResult);
	// if (fullResult > maxResult) {
	// maxResult = fullResult;
	// maxResFactors[0] = fourKPlusOnePrimes
	// .get(thirdPower);
	// maxResFactors[1] = fourKPlusOnePrimes
	// .get(secondPower);
	// maxResFactors[2] = fourKPlusOnePrimes
	// .get(firstPower);
	// }
	// }
	// }
	// }
	// }
	// }
	//
	// int multLimit = (int) (limit / Collections.min(bases)) + 5;
	// boolean[] multiplierTrue = new boolean[multLimit];
	// Arrays.fill(multiplierTrue, true);
	//
	// for (int kk = 0; kk < fourKPlusOnePrimes.size(); kk++) {
	// int thisPrime = fourKPlusOnePrimes.get(kk).intValue();
	// for (int ll = 1; ll * thisPrime < multLimit; ll++) {
	// multiplierTrue[ll * thisPrime] = false;
	// }
	// }
	//
	// for (int mm = 1; mm < multiplierTrue.length; mm++) {
	// if (multiplierTrue[mm]) {
	// multipliers.add((long) mm);
	// }
	// }
	//
	// long requiredSum = 0;
	// for (int ii = 0; ii < bases.size(); ii++) {
	// int jj = -1;
	// long thisBase = bases.get(ii);
	// long thisMult;
	// long soln;
	// while ((thisMult = multipliers.get(++jj)) <= (limit / thisBase)) {
	// soln = (thisMult * thisBase);
	// requiredSum += soln;
	// }
	// }
	//
	// System.out.println(requiredSum);
	//
	// }

	public static Integer[] sieve(int lowerLimit, int upperLimit) {

		List<Integer> numbers = new ArrayList<Integer>(
				(int) (upperLimit / (Math.log(upperLimit) - 1.08366)));

		BitSet bprimes = new BitSet(upperLimit + 1);
		int sqrt = upperLimit;
		for (int i = 2; i <= sqrt; i++) {
			if (!bprimes.get(i)) {
				for (int j = 2 * i; j <= upperLimit; j += i) {
					bprimes.set(j);
				}
			}

		}
		for (int i = lowerLimit; i <= upperLimit; i++) {
			if (!bprimes.get(i) && (i % 4 == 1 || i % 4 == 3)) {
				numbers.add(i);
			}
		}
		return numbers.toArray(new Integer[0]);
	}

}
