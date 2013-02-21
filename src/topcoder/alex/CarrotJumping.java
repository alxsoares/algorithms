package topcoder.alex;

/**
 * 
 * Problem Statement Rabbits often feel hungry, so when they go out to eat
 * carrots, they jump as quickly as possible.
 * 
 * 
 * 
 * Initially, rabbit Hanako stands at position init. From position x, she can
 * jump to either position 4*x+3 or 8*x+7 in a single jump. She can jump at most
 * 100,000 times because she gets tired by jumping.
 * 
 * 
 * 
 * Carrots are planted at position x if and only if x is divisible by
 * 1,000,000,007 (i.e. carrots are planted at position 0, position
 * 1,000,000,007, position 2,000,000,014, and so on). Return the minimal number
 * of jumps required to reach a carrot. If it's impossible to reach a carrot
 * using at most 100,000 jumps, return -1.
 * 
 * 
 * 
 * 
 * Definition
 * 
 * Class: CarrotJumping Method: theJump Parameters: int Returns: int Method
 * signature: int theJump(int init) (be sure your method is public)
 * 
 * 
 * Constraints - init will be between 1 and 1,000,000,006, inclusive.
 * 
 */

public class CarrotJumping {
    long MOD = 1000000007;

    // 2^n (mod MOD)
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
        // System.out.printf("%d\n", j.theJump(125000000));
        // System.out.printf("%d\n", j.theJump(281250001));
        // System.out.printf("%d\n", j.theJump(18426114));
        // System.out.printf("%d\n", j.theJump(4530664));
        // System.out.printf("%d\n", j.theJump(705616876));
        // System.out.printf("%d\n", j.theJump(852808441));
        System.out.printf("%d\n", j.theJump(140625000));
    }
}
