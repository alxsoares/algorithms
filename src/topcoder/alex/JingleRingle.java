package topcoder.alex;

import java.util.Arrays;

/**
 * Problem Statement Your favorite online game operates on two types of currency
 * - jingles and ringles. They are obtained from different sources, and players
 * can trade them in an exchange market. Each individual trade is the result of
 * an agreement between two people - a seller who wants to sell 1 jingle and a
 * buyer who wants to buy it for an agreed upon integer price of X ringles. Each
 * trade is executed as follows: The buyer pays X ringles to the seller. The
 * seller pays 1 jingle to the buyer. The seller pays a tax of floor((X * tax) /
 * 100) ringles to the game host. The buyer pays no taxes. You have a lot of
 * ringles and no jingles, and you want to perform several trades to make a
 * profit. The exchange market is described as two sets of offers. Offers from
 * buyers are given in the int[] buyOffers, where each element is the price that
 * some buyer is willing to pay for 1 jingle. Offers from sellers are given in
 * the int[] sellOffers, where each element is the price some seller wants to
 * receive for 1 jingle. You are also given the tax rate tax.
 * 
 * Return the maximum profit in ringles you can get after accepting some of
 * these offers and paying the applicable taxes. Note that you can accept as
 * many offers from sellers as you wish, but you can only accept offers from
 * buyers if you already have enough jingles to sell. If you can't make a
 * positive profit, return 0.
 * 
 * Definition
 * 
 * Class: JingleRingle Method: profit Parameters: int[], int[], int Returns: int
 * Method signature: int profit(int[] buyOffers, int[] sellOffers, int tax) (be
 * sure your method is public)
 * 
 * 
 * Notes - floor(X) is the largest integer which is less than or equal to X. -
 * Assume that you have enough ringles to accept all offers from sellers.
 * 
 * Constraints - buyOffers and sellOffers will each contain between 0 and 50
 * elements, inclusive. - Each element of buyOffers and sellOffers will be
 * between 100 and 10000, inclusive. - tax will be between 0 and 20, inclusive.
 * 
 */
public class JingleRingle {
    int taxValue(final int p, final int tax) {
        return (p * tax) / 100;
    }

    public int profit(final int[] buyOffers, final int[] sellOffers, final int tax) {
        Arrays.sort(buyOffers);
        Arrays.sort(sellOffers);
        int buyJ = buyOffers.length - 1;
        int res = 0;
        for (int i = 0; i < sellOffers.length; i++) {

            int taxValue = taxValue(buyOffers[buyJ], tax);

            if (buyJ >= 0 && (buyOffers[buyJ] - taxValue - sellOffers[i] >= 0)) {

                res += buyOffers[buyJ] - taxValue - sellOffers[i];
            }

            buyJ--;
        }
        return res;
    }

    public static void main(final String[] args) {
        JingleRingle jr = new JingleRingle();
        int[] buyOffers = { 1000, 1001, 1002 };
        int[] sellOffers = { 980, 981, 982 };
        System.out.println(jr.profit(buyOffers, sellOffers, 2));
    }
}
