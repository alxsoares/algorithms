package alex.algorithms.dynamicprogramming;

public class BinomialCoefficients {
    public static int binomialCoefficient(final int n, final int k) {
        int C[][] = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i && j <= k; j++) {
                if (i == 0 || j == 0)
                    C[i][j] = 1;
                else {
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }
        return C[n][k];
    }

    public static void main(final String[] args) {
        System.out.printf("C(%d,%d)=%d\n", 5, 2, binomialCoefficient(5, 2));
        System.out.printf("C(%d,%d)=%d\n", 10, 2, binomialCoefficient(10, 2));
    }

}
