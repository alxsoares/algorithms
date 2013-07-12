package alex.algorithms.math.projecteuler;

public class Euler434 {
    private static final int MOD = 1000000033;

    public static void main(final String[] args) {
        System.out.println(solve(100));
    }

    static public long solve(final int n) {
        long sum = 0L;
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                sum = (sum + connected(a, b)) % MOD;
            }
        }
        return sum;
    }

    static private Long[][] memo = new Long[101][101];

    static private long connected(final int m, final int n) {
        if (memo[m][n] != null) {
            return memo[m][n];
        }
        return memo[m][n] = (configs(m, n) - disconnected(m, n) + MOD) % MOD;
    }

    static private long disconnected(final int m, final int n) {
        long sum = 0L;
        for (int a = 1; a <= m; a++) {
            for (int b = 0; b <= n; b++) {
                if (a == m && b == n)
                    continue;
                sum = (sum + C(m - 1, a - 1) * C(n, b) % MOD * connected(a, b) % MOD * configs(m - a, n - b) % MOD)
                        % MOD;
            }
        }
        return sum;
    }

    static private long configs(final int m, final int n) {
        return pow2(m * n);
    }

    static private Long[] pmemo = new Long[10001];

    static private long pow2(final int n) {
        if (n == 0) {
            return 1L;
        }
        if (pmemo[n] != null) {
            return pmemo[n];
        }
        return pmemo[n] = 2L * pow2(n - 1) % MOD;
    }

    static private Long[][] combCache = new Long[101][101];

    static private long C(final int n, final int k) {
        if (k == 0 || k == n) {
            return 1L;
        }
        if (combCache[n][k] != null) {
            return combCache[n][k];
        }
        return combCache[n][k] = (C(n - 1, k - 1) + C(n - 1, k)) % MOD;
    }
}
