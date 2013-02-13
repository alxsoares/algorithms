package topcoder.alex;

import java.util.ArrayList;
import java.util.Arrays;

public class PlanarGraphShop {
    int dp[] = new int[50001];
    ArrayList<Integer> costs = new ArrayList<Integer>();

    int func(final int N) {
        if (N == 0)
            return 0;

        if (N < 0)
            return Integer.MAX_VALUE;

        if (dp[N] != -1)
            return dp[N];

        dp[N] = Integer.MAX_VALUE;

        for (int i = 0; i < costs.size(); i++) {
            int c = costs.get(i);
            if (c <= N) {
                int r = func(N - c) + 1;
                if (r < dp[N])
                    dp[N] = r;
            }
        }
        return dp[N];
    }

    public int bestCount(final int N) {
        Arrays.fill(dp, -1);
        costs.clear();
        costs.add(1);
        costs.add(8);
        costs.add(9);
        for (int v = 1; v < 37; v++)
            for (int e = 0; e <= 3 * v - 6; e++)
                costs.add(v * v * v + e * e);
        return func(N);
    }

    public static void main(final String[] args) {

        PlanarGraphShop s = new PlanarGraphShop();
        System.out.printf("%d\n", s.bestCount(36));
        System.out.printf("%d\n", s.bestCount(7));
        System.out.printf("%d\n", s.bestCount(72));
        System.out.printf("%d\n", s.bestCount(5000));
    }

}
