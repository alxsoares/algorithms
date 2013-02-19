package topcoder.alex;

import java.util.Arrays;

public class LuckyString {

    public int count(final String s) {
        char[] ss = s.toCharArray();
        Arrays.sort(ss);
        int res = 0;
        do {
            boolean good = true;
            for (int i = 1; i < ss.length; i++) {
                if (ss[i] == ss[i - 1]) {
                    good = false;
                    break;
                }
            }
            if (good) {
                res++;
            }

        } while (next_permutation(ss));
        return res;
    }

    boolean next_permutation(final char[] p) {
        for (int a = p.length - 2; a >= 0; --a)
            if (p[a] < p[a + 1])
                for (int b = p.length - 1;; --b)
                    if (p[b] > p[a]) {
                        char t = p[a];
                        p[a] = p[b];
                        p[b] = t;
                        for (++a, b = p.length - 1; a < b; ++a, --b) {
                            t = p[a];
                            p[a] = p[b];
                            p[b] = t;
                        }
                        return true;
                    }
        return false;
    }

    public static void main(final String[] args) {
        LuckyString l = new LuckyString();
        System.out.println(l.count("abcdefghij"));
    }
}
