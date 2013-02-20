package topcoder.alex;

public class CarrotJumping {
    long MOD = 1000000007;

    long pow(final int v) {
        if (v == 0)
            return 1;
        if (v == 1)
            return 2;
        long x = pow(v / 2);
        if (v % 2 != 0)
            return (x * x * 2) % MOD;
        return (x * x) % MOD;
    }

    public int theJump(final int init) {
        int res = Integer.MAX_VALUE;
        for (int a = 0; a <= 100000; a++) {
            for (int b = 0; b < 3; b++) {
                long v = pow(a * 3 + b * 2) % MOD;
                long c = (v * init + (v - 1 + MOD) % MOD) % MOD;
                if (c == 0) {
                    res = Math.min(res, (a + b));
                }
            }
        }
        if (res > 100000) {
            res = -1;
        }
        return res;
    }

    public static void main(final String[] args) {
        CarrotJumping j = new CarrotJumping();
        System.out.printf("%d\n", j.theJump(125000000));
        System.out.printf("%d\n", j.theJump(281250001));
        System.out.printf("%d\n", j.theJump(18426114));
        System.out.printf("%d\n", j.theJump(4530664));
        System.out.printf("%d\n", j.theJump(705616876));
        System.out.printf("%d\n", j.theJump(852808441));
    }
}
