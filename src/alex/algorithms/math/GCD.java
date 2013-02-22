package alex.algorithms.math;

import java.math.BigInteger;

public class GCD {
    public static BigInteger bitwiseGcd(BigInteger u, BigInteger v) {
        if (u.equals(BigInteger.ZERO))
            return v;
        if (v.equals(BigInteger.ZERO))
            return u;

        int uBit = u.getLowestSetBit();
        int vBit = v.getLowestSetBit();
        int k = (uBit <= vBit ? uBit : vBit);

        while (u.signum() > 0) {
            u = u.shiftRight(u.getLowestSetBit());
            v = v.shiftRight(v.getLowestSetBit());
            if (u.subtract(v).signum() >= 0) {
                u = (u.subtract(v)).shiftRight(1);
            } else {
                v = (v.subtract(u)).shiftRight(1);
            }
        }

        return v.shiftLeft(k);
    }

    public static int gcd(int a, int b) {
        while (b > 0) {
            a = a % b;
            System.out.println("a=>" + a);
            System.out.println("b=>" + b);
            a ^= b;
            System.out.println("a=>" + a);
            b ^= a;
            System.out.println("b=>" + b);
            a ^= b;
            System.out.println("a=>" + a);
        }
        return a;
    }

    public static int gcdRecursive(final int u, final int v) {
        if (u == v)
            return u;
        if (u == 0)
            return v;
        if (v == 0)
            return u;
        if ((~u & 1) > 0) {// u é par
            if ((v & 1) > 0) {// v impar
                return gcdRecursive(u >> 1, v);// v impar e u par
            }
            return gcdRecursive(u >> 1, v >> 1) << 1;// ambos são pares
        }
        if ((~v & 1) > 0)
            return gcdRecursive(u, v >> 1);// v par e u impar
        if (u > v) {
            return gcdRecursive((u - v) >> 1, v);
        } else {
            return gcdRecursive(u, (v - u) >> 1);
        }
    }

    public static int lcm(final int a, final int b) {
        return a * b / gcd(a, b);
    }

    /**
     * TODO: fazer iterativo
     * 
     * @param args
     */
    public static void main(final String[] args) {
        long begin = System.currentTimeMillis();
        System.out.println(gcd(943094, 343434));
        long end = System.currentTimeMillis();
        System.out.println("Tempo=>" + (end - begin));
        begin = System.currentTimeMillis();
        System.out.println(gcdRecursive(943094, 343434));
        end = System.currentTimeMillis();
        System.out.println("Tempo=>" + (end - begin));
        // System.out.println(lcm(3, 6));
        // System.out.println(bitwiseGcd(new BigInteger("94839048390483944"),
        // new BigInteger("893748937489374")));
    }

}
