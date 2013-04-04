package alex.acm;

/**
 * Consider an n × m grid. Initially all the cells of the grid are colored
 * white. Lenny has painted some of the cells (at least one) black. We call a
 * painted grid convex if one can walk from any black cell to any another black
 * cell using a path of side-adjacent black cells changing his direction at most
 * once during the path. In the figure below, the left grid is convex while the
 * right one is not convex, because there exist two cells which need more than
 * one time to change direction in their path.
 * 
 * 
 * You're given a painted grid in the input. Tell Lenny if the grid is convex or
 * not.
 * 
 * Input The first line of the input contains two integers n and m
 * (1 ≤ n, m ≤ 50) — the size of the grid. Each of the next n lines contains m
 * characters "B" or "W". Character "B" denotes a black cell of the grid and "W"
 * denotes a white cell of the grid.
 * 
 * It's guaranteed that the grid has at least one black cell.
 * 
 * Output On the only line of the output print "YES" if the grid is convex,
 * otherwise print "NO". Do not print quotes.
 * 
 */
public class ConvexShape {

    private final int[] x = new int[3000];
    private final int[] y = new int[3000];

    private final char[][] s = new char[50][50];

    boolean check(final int x, final int a1, final int b1, final int y, final int a2, final int b2) {
        for (int i = Math.min(a1, b1); i <= Math.max(a1, b1); i++)
            if (s[x][i] != 'B')
                return false;
        for (int i = Math.min(a2, b2); i <= Math.max(a2, b2); i++)
            if (s[i][y] != 'B')
                return false;
        return true;
    }

    void process(final int n, final int m, final char[][] s) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (s[i][j] == 'B') {
                    x[++count] = i;
                    y[++count] = j;
                }
            }
        }
        for (int i = 1; i < count; i++)
            for (int j = i + 1; j <= count; j++) {
                if (check(x[i], y[i], y[j], y[j], x[i], x[j]))
                    continue;
                if (check(x[j], y[i], y[j], y[i], x[i], x[j]))
                    continue;
                System.out.println("NO");
                return;
            }
        System.out.println("YES");
    }

    public static void main(final String[] args) {
        //@formatter:off
        char[][] s = { 
                        { 'W', 'W', 'B', 'W' }, 
                        { 'B', 'W', 'W', 'W' }, 
                        { 'W', 'W', 'W', 'B' } 
                        };
      //@formatter:on
        ConvexShape cs = new ConvexShape();
        cs.process(3, 4, s);
    }
}
