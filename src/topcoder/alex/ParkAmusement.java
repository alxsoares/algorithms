package topcoder.alex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Problem Statement A new ride is opened in an amusement park. It consists of N
 * landings numbered from 0 to N-1. Some of the landings are connected with
 * pipes. All of the landings are at different heights, so the pipes are all
 * inclined and can only be traversed downwards.
 * 
 * 
 * 
 * A ride-taker begins his ride at some landing. The pipes are long enough that
 * he cannot see where they lead before entering them. Therefore, at each
 * landing, any descending pipe adjacent to it has an equal probability of being
 * used by a ride-taker who reached this landing.
 * 
 * 
 * 
 * A ride is finished when a ride-taker reaches a landing which has no adjacent
 * descending pipes. There are two types of such landings: exits and crocodile
 * ponds. If the ride-taker reaches the exit landing, his ride is over and he
 * safely returns home. If one reaches the crocodile pond, his trip is also
 * over, but he never returns home.
 * 
 * 
 * 
 * You're given a String[] landings describing the ride. Element i of landings
 * describes the i-th landing. If the landing is an exit, the i-th character of
 * landings[i] will be 'E' and the rest of the characters will be '0's (zeroes).
 * If it is a crocodile pond, the i-th character will be 'P' and the rest will
 * be '0's. If the landing has at least one adjacent descending pipe, the j-th
 * character of landings[i] will be '1' (one) if a pipe descends from the i-th
 * landing to the j-th, and '0' (zero) otherwise.
 * 
 * 
 * 
 * A ride-taker began his ride at a randomly chosen landing, used a total of K
 * pipes throughout his descent and safely returned home afterwards. Each of the
 * landings has the same probability of being chosen as the initial landing of
 * the ride. Compute the probability that he started the ride at landing
 * startLanding.
 * 
 * Definition
 * 
 * Class: ParkAmusement Method: getProbability Parameters: String[], int, int
 * Returns: double Method signature: double getProbability(String[] landings,
 * int startLanding, int K) (be sure your method is public)
 * 
 * 
 * Notes - Your return value must have an absolute or relative error less than
 * 1e-9.
 * 
 * Constraints - landings will contain exactly N elements, where N is between 2
 * and 50, inclusive. - Each element of landings will contain exactly N
 * characters. - Each character in landings will be '0' (zero), '1' (one), 'E',
 * or 'P'. - If the i-th element of landings contains an 'E', it will contain
 * only one 'E' as its i-th character, and all other characters in that element
 * will be '0'. - If the i-th element of landings contains a 'P', it will
 * contain only one 'P' as its i-th character, and all other characters in that
 * element will be '0'. - If the i-th element of landings doesn't contain an 'E'
 * or a 'P', it will contain at least one '1' character. The i-th character of
 * such element will always be '0'. - K will be between 1 and N-1, inclusive. -
 * startLanding will be between 0 and N-1, inclusive. - There will be no cycles
 * in landings, i.e. it's never possible to return to the same landing after
 * descending through several pipes. - There will be at least one landing from
 * which it is possible to reach an exit using exactly K pipes.
 * 
 */
public class ParkAmusement {

	Vector<LinkedList<Integer>> adjList;
	int[] table;
	double[][] dp;

	double calcPr(int startNode, int K) {
		if (dp[startNode][K] >= 0.0)
			return dp[startNode][K];
		if (K == 0) {
			if (table[startNode] != 1)
				return dp[startNode][K] = 0;
			return dp[startNode][K] = 1.0;
		}
		double res = 0.0;
		int poss = adjList.get(startNode).size();

		for (int i = 0; i < adjList.get(startNode).size(); i++) {
			res += (1.0 / poss) * calcPr(adjList.get(startNode).get(i), K - 1);
		}
		dp[startNode][K] = res;
		return res;
	}

	public double getProbability(String[] landings, int startLanding, int K) {
		dp = new double[51][51];
		adjList = new Vector<LinkedList<Integer>>();
		for (int i = 0; i < landings.length; i++) {
			adjList.add(i, new LinkedList<Integer>());
		}
		table = new int[51];
		for (int i = 0; i < landings.length; i++) {
			if (landings[i].charAt(i) != '0') {
				if (landings[i].charAt(i) == 'P')
					table[i] = 2; // pond
				else
					table[i] = 1; // exit
				continue;
			}
			for (int j = 0; j < landings[i].length(); j++) {
				if (landings[i].charAt(j) == '1')
					adjList.get(i).add(j);
			}
		}
		double tot = 0.0;
		for (int i = 0; i < 51; i++)
			Arrays.fill(dp[i], -1);
		for (int i = 0; i < landings.length; i++) {
			tot += calcPr(i, K);
		}
		return calcPr(startLanding, K) / tot;
	}

	public static void main(String[] args) {
		String[] landings = { "01000100", "00111000", "00001010", "000E0000",
				"0000E000", "00000P00", "000000P0", "01000000" };
		ParkAmusement pa = new ParkAmusement();
		System.out.printf("%1.10f", pa.getProbability(landings, 1, 2));
	}

}
