package alex.algorithms.math.projecteuler;

public class Euler026 {
    public static void main(final String[] args) {
        int max = 0;
        int maxi = 0;
        for (int i = 2; i <= 1000; i++) {
            int rest = 1;
            for (int j = 0; j < i; j++) {
                rest = (rest * 10) % i;
            }
            int r0 = rest;
            int length = 0;
            do {
                rest = (rest * 10) % i;
                length++;
            } while (rest != r0);
            if (length > max) {
                maxi = i;
                max = length;
            }
        }
        System.out.printf("%d %d\n", maxi, max);
    }
}
