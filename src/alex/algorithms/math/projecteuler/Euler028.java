package alex.algorithms.math.projecteuler;

public class Euler028 {
    public static void main(final String[] args) {
        int t = 0;
        for (int n = 3; n <= 1001; n += 2)
            t += 4 * Math.pow(n, 2) - 6 * n + 6;
        System.out.printf("%d\n", t);
    }
}
