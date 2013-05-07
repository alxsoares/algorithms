package alex.algorithms.math;

public class Math {
    private static final double EPSLON = Double.MIN_VALUE;

    // public static void IntegerDivision(int numerator, final int denominator)
    // {
    // int quot = 0;
    // while (numerator > denominator) {
    // numerator = numerator - denominator;
    // quot++;
    // }
    // int rest = numerator;
    //
    // System.out.println("Quotient=[" + quot + "] and Denominator=[" + rest +
    // "]");
    // }
    public static int abs(final int n) {
        if (n < 0) {
            return -n;
        }
        return n;
    }

    public static int integerDivisionQuotient(int numerator, int denominator) {
        if (denominator == 0)
            throw new RuntimeException("Division By ZERO.");

        boolean negative = false;

        if (numerator < 0 && denominator > 0)
            negative = true;
        else if (numerator > 0 && denominator < 0)
            negative = true;

        numerator = abs(numerator);
        denominator = abs(denominator);
        int quot = 0;
        while (numerator >= denominator) {
            numerator -= denominator;
            quot++;
        }

        if (negative) {
            return -quot;
        }
        return quot;
    }

    public static int integerDivisionRest(int numerator, int denominator) {
        if (denominator == 0)
            throw new RuntimeException("Division By ZERO.");

        boolean negative = false;

        if (numerator < 0)
            negative = true;
        numerator = abs(numerator);
        denominator = abs(denominator);

        while (numerator >= denominator) {
            numerator -= denominator;
        }
        if (negative)
            return -numerator;
        return numerator;
    }

    public static double sqrt(final double n) {
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

    public static double pow2(final double n, final int e) {
        if (e == 0)
            return 1;
        double r = n;
        for (int i = 1; i < e; i++) {
            r *= n;
        }
        return r;
    }

    public static double powRecursive(final double n, final int e) {
        if (e == 0) {
            return 1;
        }
        if (e == 1)
            return n;
        double r = powRecursive(n, e / 2);
        if (e % 2 == 0) {
            return r * r;
        }
        return n * r * r;
    }

    public static double powIter(final double n, int e) {
        double factor = n;
        double res = 1;
        while (e > 0) {
            if ((e & 1) > 0) {
                res = res * factor;
            }
            e = e >> 1;
            factor *= factor;
        }
        return res;
    }

    public static double sqrt2(final double val) {
        double low = 0;
        double mid;
        double high = mid = val;
        double oldmid = -1;
        int step = 0;
        while (Math.abs(oldmid - mid) >= 0.00001) {
            oldmid = mid;

            // Get midpoint and see if we need lower or higher.

            mid = (high + low) / 2;
            double midsqr = mid * mid;
            System.out.printf("%4d  %10.4f  %10.4f  %10.4f  %10.4f  %10.4f  ", ++step, val, low, high, mid, midsqr);
            if (mid * mid > val) {
                high = mid;
                System.out.printf("- too high\n");
            } else {
                low = mid;
                System.out.printf("- too low\n");
            }
        }

        // Desired accuracy reached, print it.

        System.out.printf("sqrt(%.4f) = %.4f\n", val, mid);
        return mid;
    }

    public static double sqrtNewton(final double n) {
        double guess = (1 + n) / 2;
        while (Math.abs(n - guess * guess) >= 0.00001) {
            guess = (guess + n / guess) / 2;
            System.out.printf("sqrt(%.4f) = %.4f\n", n, guess);
        }
        System.out.printf("sqrt(%.4f) = %.4f\n", n, guess);
        return guess;
    }

    private static double abs(final double d) {
        if (d < 0)
            return -d;
        return d;
    }

    public static double pow(final double n, final double e) {
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

    public static double pow(final double n, int e) {
        int one = 0x01;
        double factor = n;
        double res = 1;
        while (e > 0) {
            if ((e & one) == 1) {
                res = res * factor;
            }
            e = e >> 1;
            factor = factor * factor;
        }
        return res;
    }

    public static void primes(final int n) {
        int[] p = new int[n + 1];
        p[2] = 0;
        for (int i = 4; i <= n; i += 2) {
            p[i]++;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (p[i] == 0) {
                for (int j = i * 2; j <= n; j += i) {
                    p[j] = p[j] + 1;
                }
            }
        }
        for (int i = 2; i < p.length; i++) {
            int j = p[i];
            if (j == 0) {
                System.out.printf("%d is prime\n", i);
            }
            // else {
            // System.out.printf("%d tem %d divisores\n", i, j);
            // }
        }
    }

    static boolean isPrime(final long n) {
        if (n == 2 || n == 3)
            return true;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static int nthPrime(final int n) {
        int i = 2;
        for (int j = 4; j <= Long.MAX_VALUE; j++) {
            if (isPrime(j)) {
                i++;
                if (i == n)
                    return j;
            }
        }
        return -1;
    }

    public static int trailingZeroes(int num) {
        int r = 0;
        if (num < 5)
            return r;
        while (num > 0) {
            r += num / 5;
            num /= 5;
        }
        return r;
    }

    public static int numZeros(final int num) {
        int count = 0;
        if (num < 0) {
            System.out.println("Factorial is not defined for < 0");
            return 0;
        }
        for (int i = 5; num / i > 0; i *= 5) {
            count += num / i;
        }
        return count;
    }

    public static int max(final int a, final int b) {
        int c = (a - b);
        int k = (c >> 31) & 1;
        return a - k * (c);
    }

    public static int sum(final int a, final int b) {
        if (b == 0)
            return a;
        int sum = a ^ b;
        int carry = (a & b) << 1;
        return sum(sum, carry);
    }

    // e^x = 1 + x/1! + x^2/2! + x^3/3! + ......
    // e^x = 1 + (x/1) (1 + (x/2) (1 + (x/3) (........) ) )
    public static float exp(final int n, final float x) {
        float sum = 1.0f;
        for (int i = n - 1; i > 0; i--) {
            sum = 1 + x * sum / i;
        }
        return sum;
    }

    public static void main(final String[] args) {
        // IntegerDivision(200, 7);
        // System.out.println(sqrt(36.0));
        // System.out.println(pow(2, 0.5));
        // System.out.println(pow2(2, 3));
        // primes(20000);
        // System.out.println(trailingZeroes(200));
        // System.out.println(numZeros(200));
        // System.out.println(max(-300,-400));
        // System.out.println(sum(0x00ff,0x00ff));
        // System.out.printf("%f",exp(1000, 1));
        System.out.printf("%d\n", nthPrime(10001));
        System.out.printf("%.4f\n", sqrt2(16.23));
        System.out.printf("%.4f\n", sqrtNewton(16));
        System.out.printf("%d\n", integerDivisionQuotient(11, -2));
        System.out.printf("%.4f\n", powRecursive(2, 1));
    }
}
