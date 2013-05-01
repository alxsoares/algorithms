package topcoder.alex;

import java.util.Arrays;

/**
 * 
 * N friends (numbered from 0 to N-1) play a game called Mafia. The exact rules
 * of the game are not important for this problem. What's important is that at
 * some point in the game they will need to choose one player who will lose and
 * leave the game.
 * 
 * 
 * 
 * It is known that some players have a definite opinion on who should lose.
 * Their opinions are given in the int[] decisions, where each element
 * corresponds to a single opinion and is the number of a player who should lose
 * according to that opinion. All opinions in decisions belong to different
 * players. If decisions contains less than N elements, then all other players
 * do not have a definite opinion on who should lose.
 * 
 * 
 * 
 * In order to determine who will lose, one or more rounds of voting will be
 * conducted. In each round, there's a set of players for whom the players are
 * allowed to vote. The players in this set are called "vulnerable". It's
 * impossible to vote for players not in this set. Before the first round of
 * voting, all N players are included in this set.
 * 
 * 
 * 
 * All N players will vote in each round. The voting is held according to the
 * following scheme:
 * 
 * First, each player who has a definite opinion on who should lose is allowed
 * to vote if the player he thinks should lose is "vulnerable" in this round.
 * All of these players will vote according to their opinions. Then all other
 * players vote, in order. Each of them votes for the "vulnerable" player who
 * currently has the smallest number of votes (only considering the votes in the
 * current round). If there are several such players, he/she chooses one of them
 * uniformly at random and votes for the chosen player. Once all players have
 * voted, if there is only one player who has the largest number of votes in the
 * current round, this player loses and leaves the game. In this case there will
 * be no more rounds of voting. Otherwise, the set of "vulnerable" players in
 * the next round is set to all players who have the largest number of votes in
 * the current round.
 * 
 * If it is possible that an infinite number of voting rounds will be held, then
 * return 0. Otherwise, consider an array containing exactly N elements, where
 * the i-th element (0-based) is equal to the probability that the i-th player
 * will lose. Return the maximum value among all elements of this array.
 * 
 */
public class MafiaGame {

	public static double probabilityToLose(int N, int[] decisions) {
		int[] count = new int[N];
		Arrays.fill(count, 0);
		for (int k = 0; k < decisions.length; k++) {
			count[decisions[k]]++;
		}
		Arrays.sort(count);
		int tval = 1;
		for (int j = count.length - 1; j > -1; j--) {
			if (count[j] <= 1)
				return 0.0;
			if (j > 0 && count[j] == count[j - 1]) {
				tval++;
			} else
				break;
		}
		double tans = 1 / ((double) (tval));
		while (tval != 1) {
			if (N % tval == 0)
				return 0.0;
			tval = N % tval;
		}
		return tans;
	}

}
