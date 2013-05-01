package alex.algorithms.arrays;

/**
 * 
 * http://www.codeforces.com/problemset/problem/266/C
 * 
 */
public class BellowTheDiagonal {

    int x[] = new int[1024];
    int y[] = new int[1024];

    public void solveMatrix(final int n, int m) {
        int a[] = new int[1 << 17];
        int b[] = new int[1 << 17];
        int c[] = new int[1 << 17];
        for (int i = 1; i < n; ++i) {
            if (x[i] != i + 1) {
                for (int j = i + 1; j < n; ++j)
                    x[j] = x[j] == i + 1 ? x[i] : x[j] == x[i] ? i + 1 : x[j];
                a[m] = 1;
                b[m] = x[i];
                c[m] = i + 1;
                m++;
            }
            if (y[i] > i) {
                for (int j = i + 1; j < n; ++j)
                    y[j] = y[j] == i ? y[i] : y[j] == y[i] ? i : y[j];
                a[m] = 2;
                b[m] = y[i];
                c[m] = i;
                m++;
            }
        }
        for (int i = 0; i < m; ++i)
            System.out.printf("%d %d\n", x[i], y[i]);
    }
}
