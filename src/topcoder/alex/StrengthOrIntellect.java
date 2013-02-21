package topcoder.alex;

import java.util.Arrays;

public class StrengthOrIntellect {
    Pair preComp[][] = new Pair[1011][1011];
    int memo[][] = new int[1011][1011];

    public int numberOfMissions(final int[] strength, final int[] intellect, final int[] points) {
        for (int i = 0; i <= 1000; i++) {
            for (int j = 0; j <= 1000; j++) {
                int pointsGained = 0, missions = 0;
                for (int k = 0; k < strength.length; k++) {
                    if (strength[k] <= i || intellect[k] <= j) {
                        pointsGained += points[k];
                        missions++;
                    }
                }
                int extraPoints = pointsGained - i - j + 2;
                preComp[i][j] = new Pair(extraPoints, extraPoints < 0 ? 0 : missions);
            }
        }
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        for (int i = 0; i < strength.length; i++) {
            for (int j = 0; j < intellect.length; j++) {
                memo[i][j] = preComp[i][j].second;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int s = 1; s < strength.length; s++) {
            for (int i = 1; i < intellect.length; i++) {
                int extra = preComp[s][i].first;
                int res = (extra <= 0 ? memo[s - 1][i - 1] : Math.max(memo[s][i],
                        Math.max(memo[s][i - 1], memo[s - 1][i])));
                memo[s][i] = Math.max(memo[s][i], res);
                max = Math.max(max, memo[s][i]);
            }
        }
        // return func(1, 1);
        return max;
    }

    int func(final int strength, final int intellect) {
        if (memo[strength][intellect] != -1)
            return memo[strength][intellect];
        memo[strength][intellect] = preComp[strength][intellect].second;
        int extra = preComp[strength][intellect].first;
        int res = (extra <= 0 ? memo[strength][intellect] : Math.max(func(strength + 1, intellect),
                func(strength, intellect + 1)));
        memo[strength][intellect] = Math.max(memo[strength][intellect], res);
        return res;
    }

    class Pair {
        public int first, second;

        public Pair(final int first, final int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(final String[] args) {
        // int S[] = { 1, 3, 1, 10, 3 };
        // int I[] = { 1, 1, 3, 20, 3 };
        // int P[] = { 2, 1, 1, 5, 1 };
        int S[] = { 1, 2, 100, 5, 100, 10, 100, 17, 100 };
        int I[] = { 1, 100, 3, 100, 7, 100, 13, 100, 21 };
        int P[] = { 1, 2, 3, 4, 5, 6, 7, 8, 1 };
        StrengthOrIntellect si = new StrengthOrIntellect();
        System.out.printf("%d\n", si.numberOfMissions(S, I, P));
    }
}
