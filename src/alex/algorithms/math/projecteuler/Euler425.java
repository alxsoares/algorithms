package alex.algorithms.math.projecteuler;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Euler425 {
    public static void main(final String[] args) {
        System.out.println(new Euler425().run());
    }

    private static final int LIMIT = (int) Math.pow(10, 7);

    public String run() {
        Set<Integer> isPrime = Eratosthenes.sieve(LIMIT);

        int[] pathMax = new int[LIMIT];
        Arrays.fill(pathMax, Integer.MAX_VALUE);

        Queue<IntPair> queue = new PriorityQueue<IntPair>();
        queue.add(new IntPair(2, 2));
        while (!queue.isEmpty()) {
            IntPair item = queue.remove();
            int n = item.b;
            int pmax = item.a;
            if (pmax >= pathMax[n]) {
                continue;
            }

            pathMax[n] = pmax;

            int[] digits = toDigits(n);
            int[] tempDigits = digits.clone();
            for (int i = 0; i < tempDigits.length; i++) {
                for (int j = 0; j < 10; j++) {
                    tempDigits[i] = j;
                    int m = toNumber(tempDigits);
                    int nextPmax = Math.max(m, pmax);
                    if (isPrime.contains(m) && nextPmax < pathMax[m])
                        queue.add(new IntPair(nextPmax, m));
                }
                tempDigits[i] = digits[i];
            }
        }

        long sum = 0;
        for (Iterator<Integer> iterator = isPrime.iterator(); iterator.hasNext();) {
            int i = iterator.next();
            if (pathMax[i] > i)
                sum += i;
        }
        return Long.toString(sum);
    }

    private static int[] toDigits(int n) {
        if (n < 0)
            throw new IllegalArgumentException();

        int[] temp = new int[11];
        int len = 0;
        do {
            temp[len] = n % 10;
            n /= 10;
            len++;
        } while (n > 0);

        int[] result = new int[len + 1];
        for (int i = 0; i < result.length; i++)
            result[i] = temp[len - i];
        return result;
    }

    private static int toNumber(final int[] digits) {
        int result = 0;
        for (int x : digits)
            result = result * 10 + x;
        return result;
    }

    private static class IntPair implements Comparable<IntPair> {

        public final int a;
        public final int b;

        public IntPair(final int a, final int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(final IntPair other) {
            if (a < other.a)
                return -1;
            else if (a > other.a)
                return 1;
            else
                return 0;
        }

    }
}
