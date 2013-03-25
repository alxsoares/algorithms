package topcoder.alex;

/**
 * Problem Statement
 * 
 * Manao has recently invented a brand new single-player game called The Number
 * Game.
 * 
 * The player starts with a number A. Also, another number B is chosen. Note
 * that neither A nor B contain a zero digit in their base 10 representation.
 * 
 * The player's goal is to obtain B from A. In each move, the player can either
 * reverse his current number, or he can divide it by 10 (using integer
 * division). For example, if the current number is 12849, the player can either
 * reverse it to obtain 94821, or he can divide it by 10 to obtain 1284. (Note
 * that we always round down when using integer division.)
 * 
 * You are given two ints A and B. If it is possible to obtain B from A, return
 * the minimum number of moves necessary to reach this goal. Otherwise, return
 * -1.
 * 
 */
public class TheNumberGameDiv2 {
    private int reverseInt(final int n) {
        char r[] = String.valueOf(n).toCharArray();
        for (int i = 0; i < r.length / 2; i++) {
            char temp = r[i];
            r[i] = r[r.length - i - 1];
            r[r.length - i - 1] = temp;
        }
        return Integer.valueOf(new String(r));
    }

    public int minimumMoves(final int A, final int B) {
        if (A == B)
            return 0;
        if (B > A && B > reverseInt(A)) {
            return -1;
        }
        if (B == reverseInt(A))
            return 1;
        if (A / 10 == B) {
            return 1;
        }
        if (A / 10 == B)
            return 1;
        int m1 = minimumMoves(A / 10, B);
        int m2 = minimumMoves(reverseInt(A) / 10, B);

        if (m1 == -1 && m2 == -1) {
            return -1;
        }
        if (m1 == -1) {
            return 2 + m2;
        }
        if (m2 == -1) {
            return 1 + m1;
        }
        return Math.min(m1 + 1, m2 + 2);
    }

    public static void main(final String[] args) {
        TheNumberGameDiv2 t = new TheNumberGameDiv2();
        System.out.println(t.minimumMoves(5162, 16));
        System.out.println(t.minimumMoves(334, 12));

    }
}
