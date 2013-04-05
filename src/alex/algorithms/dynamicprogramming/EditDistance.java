package alex.algorithms.dynamicprogramming;

public class EditDistance {

    public static int editDistance(final String X, final String Y) {
        int m = Y.length() + 1;
        int n = X.length() + 1;
        int T[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                T[i][j] = -1;
            }
        }
        // T[i][0] = i
        for (int i = 0; i < n; i++) {
            T[i][0] = i;
        }
        // T[0][j] = j
        for (int j = 0; j < m; j++) {
            T[0][j] = j;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int left = T[i][j - 1] + 1;
                int top = T[i - 1][j] + 1;
                int corner = T[i - 1][j - 1];
                if (X.charAt(i - 1) != Y.charAt(j - 1))
                    corner += 1;
                T[i][j] = Math.min(left, Math.min(top, corner));
            }
        }

        return T[n - 1][m - 1];
    }

    public static int editDistanceRecursive(final String X, final String Y, final int m, final int n) {
        // Base cases
        if (m == 0 && n == 0)
            return 0;

        if (m == 0)
            return n;

        if (n == 0)
            return m;

        // Recurse
        int left = editDistanceRecursive(X, Y, m - 1, n) + 1;
        int right = editDistanceRecursive(X, Y, m, n - 1) + 1;
        int corner = editDistanceRecursive(X, Y, m - 1, n - 1) + (X.charAt(m) != Y.charAt(n) ? 1 : 0);

        return Math.min(left, Math.min(right, corner));
    }

    public static void main(final String[] args) {
        System.out.printf("%d\n", editDistance("aaaBcc", "AaaaBc"));
        System.out
                .printf("%d", editDistanceRecursive("aaaBcc", "AaaaBc", "aaaBcc".length() - 1, "AaaaBc".length() - 1));
    }

}
