package alex.algorithms.strings;

public class KMP {
    // wikipedia
    public static int searchKMP(final char[] w, final char[] s, final int[] t) {
        int m = 0;
        int i = 0;
        while (((m + i) < s.length) && (i < w.length)) {
            if (s[m + i] == w[i])
                i++;
            else {
                m += i - t[i];
                if (i > 0)
                    i = t[i];
                i++;
            }
        }
        if (i == w.length)
            return m;
        else
            return -1;
    }
}
