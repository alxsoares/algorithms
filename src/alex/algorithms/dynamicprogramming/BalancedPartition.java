package alex.algorithms.dynamicprogramming;

public class BalancedPartition {
    public static int balancedPartition(final int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        System.out.println("Sum = " + sum);
        boolean s[] = new boolean[sum + 1];
        s[0] = true;
        int diff = Integer.MAX_VALUE, ans = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = sum; j >= a[i]; j--) {
                s[j] = s[j] || s[j - a[i]];
                if (s[j]) {
                    if (diff > Math.abs(sum / 2 - j)) {
                        diff = Math.abs(sum / 2 - j);
                        ans = j;
                    }

                }
            }
        }
        return Math.min(ans, sum - ans);
    }

    public static void main(String[] args) {
        int diff = balancedPartition(new int[] { 3, 34, 4, 12, 5, 2 });
        System.out.println(diff);

        diff = balancedPartition(new int[] { 1, 1, 1, 1, 4, 4 });
        System.out.println(diff);
    }
}
