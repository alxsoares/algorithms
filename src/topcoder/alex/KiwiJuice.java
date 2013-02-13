package topcoder.alex;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 
 * Problem Statement Taro has prepared delicious kiwi fruit juice. He poured it
 * into N bottles numbered from 0 to N-1. Each bottle has a capacity of C
 * liters, and he poured bottles[i] liters of kiwi juice into the i-th bottle
 * initially. Taro will sell these bottles after performing an arbitrary number
 * of operations of the following type (possibly zero). In each operation, he
 * chooses two distinct bottles A and B, and pours kiwi juice from bottle A to
 * bottle B until either bottle A becomes empty or bottle B becomes full,
 * whichever happens earlier.
 * 
 * 
 * 
 * If a bottle contains x liters of kiwi juice at the end (where x is an integer
 * between 0 and C, inclusive), then Taro can sell the bottle for prices[x]
 * dollars. Return the maximum amount of money he can earn.
 * 
 * 
 * 
 * 
 * Definition
 * 
 * Class: KiwiJuice Method: theProfit Parameters: int, int[], int[] Returns: int
 * Method signature: int theProfit(int C, int[] bottles, int[] prices) (be sure
 * your method is public)
 * 
 * 
 * Constraints - C will be between 1 and 49, inclusive. - bottles will contain
 * between 1 and 15 elements, inclusive. - Each element of bottles will be
 * between 0 and C, inclusive. - prices will contain exactly C + 1 elements. -
 * Each element of prices will be between 0 and 1,000,000, inclusive.
 * 
 * Examples 0)
 * 
 * 
 * 10
 * 
 * {5, 8}
 * 
 * {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10}
 * 
 * Returns: 10
 * 
 * If Taro pours kiwi juice from bottle 0 to bottle 1, then bottle 0 will
 * contain 3 liters of kiwi juice and bottle 1 will contain 10 liters of kiwi
 * juice. He can sell bottle 1 for 10 dollars. 1)
 * 
 * 
 * 10
 * 
 * {5, 8}
 * 
 * {0, 0, 0, 0, 0, 10, 10, 10, 10, 10, 10}
 * 
 * Returns: 20
 * 
 * Sell both bottles without pouring. 2)
 * 
 * 
 * 10
 * 
 * {4, 5, 3, 7}
 * 
 * {14, 76, 12, 35, 6, 94, 26, 3, 93, 90, 420}
 * 
 * Returns: 625
 * 
 * It is possible that a bottle containing lesser juice is more expensive. 3)
 * 
 * 
 * 1
 * 
 * {0}
 * 
 * {1000000, 1000000}
 * 
 * Returns: 1000000
 * 
 * 4)
 * 
 * 
 * 49
 * 
 * {2, 47, 24, 14, 0, 32, 9, 12, 31, 46, 39, 12, 16, 22, 29}
 * 
 * {428668, 995687, 128567, 37241, 496916, 238624, 304781, 997819, 85856,
 * 417008, 398570, 951499, 750349, 333625, 927594, 188616, 942498, 259414,
 * 654344, 354809, 25303, 226854, 98578, 207140, 305527, 358343, 393213, 256248,
 * 282644, 103327, 667191, 103402, 609183, 619323, 189127, 518006, 778846,
 * 400460, 128334, 795097, 605203, 669142, 121825, 934404, 837430, 554265,
 * 610818, 546228, 80784, 949440}
 * 
 * Returns: 12873962
 */
public class KiwiJuice {
	int dp[][] = new int[51][1<<16];
	int C;
	private int[] bottles;
	private int[] prices;

	private int f(int i, int mask) {
//		System.out.printf("Mask %02x\n",mask);
		//memoization
		if(dp[i][mask]!=-1) return dp[i][mask];
		
		if (mask+1 == (1 << (bottles.length))-1) {
		      return prices[i];
		   }
		int max=Integer.MIN_VALUE;
		for(int j=1; j <bottles.length; j++){
//			System.out.println("j="+j);
			if((mask&(1<<j))>0) continue;//garrafa já foi usada.
			int next = bottles[j];
			int d1=0;
			if(i+next > C){
				d1= prices[C]+f(i+next-C, (mask|(1<<j)));//sobrou um pouco na garrafa
			}else{
				d1 = prices[0] + f(i+next, (mask|(1<<j)));//não sobrou nada.
			}
			int d2 = prices[i] + f(next,(mask|(1<<j)));//troco com a guarrafa J.
			max = Math.max(max, Math.max(d1, d2));
		}
		dp[i][mask] = max;
		return max;
	}

	public int theProfit(int C, int[] bottles, int[] prices) {
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		this.C = C;
		this.prices = prices;
		this.bottles = bottles;
		return f(bottles[0], 0);
	}
	

	public static void main(String[] args) {
		KiwiJuice k = new KiwiJuice();
		int bottles[] = {2, 47, 24, 14, 0, 32, 9, 12, 31, 46, 39, 12, 16, 22, 29};
		int prices[] = {428668, 995687, 128567, 37241, 496916, 238624, 304781, 997819, 85856, 417008,
				398570, 951499, 750349, 333625, 927594, 188616, 942498, 259414, 654344, 354809,
				25303, 226854, 98578, 207140, 305527, 358343, 393213, 256248, 282644, 103327,
				667191, 103402, 609183, 619323, 189127, 518006, 778846, 400460, 128334, 795097,
				605203, 669142, 121825, 934404, 837430, 554265, 610818, 546228, 80784, 949440};
		System.out.println(k.theProfit(49, bottles, prices));
	}

}
