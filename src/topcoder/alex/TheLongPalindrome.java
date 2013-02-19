package topcoder.alex;

/**
 * 
 * Problem Statement      John and Brus are studying string theory at the
 * university. Their task is to create a list of all the palindromes that
 * contain between 1 and n lowercase letters ('a'-'z'), inclusive. A palindrome
 * is a string that reads the same forward and backward. Additionally, each
 * palindrome in their list must contain no more than k distinct letters. Return
 * the number of palindromes in the list modulo 1234567891. Definition     
 * Class: TheLongPalindrome Method: count Parameters: int, int Returns: int
 * Method signature: int count(int n, int k) (be sure your method is public)
 *     
 * 
 * Constraints - n will be between 1 and 1,000,000,000, inclusive. - k will be
 * between 1 and 26, inclusive.
 * 
 */
public class TheLongPalindrome {
    final int maxN = 30;
    long matrix[][] = new long[maxN][maxN];

    public int count(final int n, final int k) {
        return 0;
    }
}
