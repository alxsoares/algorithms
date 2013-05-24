package alex.algorithms.dynamicprogramming;

public class DiceThrow {
    // number of ways to get sum 'x'
    // with 'n' dice and 'm' with m faces
    public static int findWays(final int m, final int n, final int x) {
        int S[][] = new int[n + 1][x + 1];
        // Table entries for only one dice
        for (int j = 1; j <= m && j <= x; j++)
            S[1][j] = 1;
        // Fill rest of the entries in table using recursive relation
        // i: number of dice, j: sum
        for (int i = 2; i <= n; i++)
            for (int j = 1; j <= x; j++)
                for (int k = 1; k <= m && k < j; k++)
                    S[i][j] += S[i - 1][j - k];

        return S[n][x];

    }

    public static void main(final String[] args) {
        System.out.println(findWays(4, 2, 4));
        System.out.println(findWays(2, 2, 3));
        System.out.println(findWays(6, 3, 8));
        System.out.println(findWays(4, 2, 5));
        System.out.println(findWays(4, 3, 5));
    }
}
