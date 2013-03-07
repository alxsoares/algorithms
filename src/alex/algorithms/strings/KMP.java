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

    public static void main(final String abc[]) {
        System.out.println("Testing KMP");
        test("abc", "a", 0);
        test("abc", "b", 1);
        test("abc", "c", 2);
        test("abc", "d", -1);
        test("catdog", "tdo", 2);
        test("ratatat", "at", 1);
        test("foo", "", 0);
        test("", "bar", -1);
    }

    public static void test(final String text, final String word, final int exp) {
        char[] textC = text.toCharArray();
        char[] wordC = word.toCharArray();
        int result = kmp(textC, wordC);
        if (result == exp)
            System.out.println("PASSED");
        else {
            System.out.println("FAILED");
            System.out.println("\ttext: " + text);
            System.out.println("\tword: " + word);
            System.out.println("\texp: " + exp + ", res: " + result);
        }

    }

    // W := word
    public static Integer[] createTable(final char[] W) {
        Integer[] table = new Integer[W.length];
        int pos = 2; // cur pos to compute in T
        int cnd = 0; // index of W of next character of cur candidate substr

        // first few values are fixed
        table[0] = -1; // table[0] := -1
        table[1] = 0; // table[1] := 0

        while (pos < W.length) {
            // first case: substring is still good
            if (W[pos - 1] == W[cnd]) {
                table[pos] = cnd;
                cnd += 1;
                pos += 1;
            } else if (cnd > 0)
                cnd = table[cnd];
            else {
                table[pos] = 0;
                pos += 1;
            }
        }
        return table;
    }

    // S := text string
    // W := word
    public static int kmp(final char[] S, final char[] W) {
        if (W.length == 0) // substr is empty string
            return 0;
        if (S.length == 0) // text is empty, can't be found
            return -1;
        int m = 0; // index of beg. of current match in S
        int i = 0; // pos. of cur char in W
        Integer[] T = createTable(S);

        while (m + i < S.length) {
            if (W[i] == S[m + i]) {
                if (i == W.length - 1)
                    return m;
                i += 1;
            } else {
                m = (m + i - T[i]);
                if (T[i] > -1)
                    i = T[i];
                else
                    i = 0;
            }
        }
        return -1;
    }
}
