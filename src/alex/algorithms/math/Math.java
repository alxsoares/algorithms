package alex.algorithms.math;

public class Math {
    private static final double EPSLON = Double.MIN_VALUE;

    public static void IntegerDivision(int numerator, int denominator) {
        int quot = 0;
        while (numerator > denominator) {
            numerator = numerator - denominator;
            quot++;
        }
        int rest = numerator;

        System.out.println("Quotient=[" + quot + "] and Denominator=[" + rest
                + "]");
    }

    public static double sqrt(double n) {
        double x0 = 6 * 100000;
        double variation = Double.MAX_VALUE;
        while (variation > EPSLON) {
            double newX0 = 0.5 * (x0 + n / x0);
            variation = x0 - newX0;
            x0 = newX0;
        }
        System.out.println("Variation = [" + variation + "]");
        return x0;
    }

    public static double pow2(double n, int e) {
        if (e == 0)
            return 1;
        double r = n;
        for (int i = 1; i < e; i++) {
            r *= n;
        }
        return r;
    }

    public static double pow(double n, int e) {
        int one = 0x01;
        double factor = n;
        double res = 1;
        while (e > 0) {
            if ((e & one) == 1) {
                res = res * factor;
            }
            e = e >> 1;
            factor = factor*factor;
        }
        return res;
    }

    public static double pow(double n, double e) {
        double decExp = e - (int) e;
        double partialExp = 0.5;
        double res = 1;
        double factor = sqrt(n);
        while (partialExp > Math.EPSLON) {
            if (decExp >= partialExp) {
                res = res * factor;
                decExp -= partialExp;
            }
            partialExp *= 0.5;
            factor = sqrt(factor);
        }

        return res * pow(n, (int) e);
    }

    public static void main(String[] args) {
        IntegerDivision(200, 7);
        System.out.println(sqrt(36.0));
        System.out.println(pow(2, 0.5));
        System.out.println(pow2(2,3));
    }
}
