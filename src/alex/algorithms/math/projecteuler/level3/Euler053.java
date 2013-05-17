package alex.algorithms.math.projecteuler.level3;

public class Euler053 {

    public static void main(final String[] args) {
        int count = 0;
        int n = 100;
        long[][] C = new long[n + 1][n + 1];
        count = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                // Base
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
                if (C[i][j] > 1000000) {
                    count++;
                    C[i][j] = 1000000;
                }
            }
        }
        System.out.printf("%d\n", count);
    }
}
