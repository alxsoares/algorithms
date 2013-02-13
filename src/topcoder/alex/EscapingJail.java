package topcoder.alex;

/**
 * 
 * Problem Statement
 * 
 * 
 * In a jail, some pairs of prisoners are chained together to make it more
 * difficult for them to escape. A group of rebels has decided to escape, and
 * one important part of their plan involves pressing two distant buttons
 * simultaneously. They must determine the two prisoners best suited for this
 * task.
 * 
 * Given the configuration of chains between prisoners, return the maximum
 * distance that can be achieved between two prisoners. If there is no limit to
 * that distance, return -1. You will be given a String[] chain, where the ith
 * character of the jth element represents the length of the chain between
 * prisoners i and j:
 * 
 * '0'-'9': Distances 0 to 9 feet, in order.
 * 
 * 'a'-'z': Distances 10 to 35 feet, in order.
 * 
 * 'A'-'Z': Distances 36 to 61 feet, in order.
 * 
 * ' ': Space means there is no chain between that pair of prisoners.
 * 
 * Definition
 * 
 * Class: EscapingJail Method: getMaxDistance Parameters: String[] Returns: int
 * Method signature: int getMaxDistance(String[] chain) (be sure your method is
 * public)
 * 
 * 
 * Constraints - chain will have between 2 and 50 elements, inclusive. - Each
 * element of chain will have exactly N characters, where N is the number of
 * elements of chain. - Character i of element j of chain and character j of
 * element i of chain will be equal. - Character i of element i of chain will be
 * '0'. - Each character of each element of chain will be a digit ('0'-'9'), an
 * uppercase letter ('A'-'Z'), a lowercase letter ('a'-'z') or a space ' '.
 */
public class EscapingJail {

	int[][] adjMatrix = new int[51][51];

	int translate(char ch) {
		if (Character.isDigit(ch))
			return ch - '0';
		if (Character.isLowerCase(ch))
			return ch - 'a' + 10;
		if (Character.isUpperCase(ch))
			return ch - 'A' + 36;
		return Integer.MAX_VALUE;
	}

	public int getMaxDistance(String[] chain) {
		for (int i = 0; i < chain.length; i++) {
			for (int j = 0; j < chain[i].length(); j++) {
				adjMatrix[i][j] = translate(chain[i].charAt(j));
			}
		}
		for (int i = 0; i < chain.length; i++) {
			for (int j = 0; j < chain.length; j++) {
				for (int k = 0; k < chain.length; k++) {
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k]
							+ adjMatrix[k][j]);
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < chain.length; i++) {
			for (int j = 0; j < chain.length; j++) {
				if(i==j) continue;
				if(adjMatrix[i][j] >= Integer.MAX_VALUE) return -1;
				max = Math.max(max, adjMatrix[i][j]);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] chains={"0568",
			 "5094",
			 "6903",
			 "8430"};
		EscapingJail e = new EscapingJail();
		System.out.printf("%d ", e.getMaxDistance(chains));
	}

}
