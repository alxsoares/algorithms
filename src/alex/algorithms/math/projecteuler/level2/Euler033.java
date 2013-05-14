package alex.algorithms.math.projecteuler.level2;

public class Euler033 {
    public static void main(final String[] args) {
        int denproduct = 1;
        int nomproduct = 1;

        for (int i = 1; i < 10; i++) {
            for (int den = 1; den < i; den++) {
                for (int nom = 1; nom < den; nom++) {
                    if ((nom * 10 + i) * den == nom * (i * 10 + den)) {
                        denproduct *= den;
                        nomproduct *= nom;
                    }
                }
            }
        }
        denproduct /= gcd(nomproduct, denproduct);
        System.out.printf("%d\n", denproduct);
    }

    private static int gcd(final int u, final int v) {
        if (u == v)
            return v;
        if (u == 0)
            return v;
        if (v == 0)
            return u;
        if ((u & 1) == 0) {// u é par
            if ((v & 1) > 0) {// v é impar
                return gcd(u >> 1, v);
            }
            return gcd(u >> 1, v >> 1) << 1;
        }
        if ((v & 1) == 0) {// par
            return gcd(u, v >> 1);// v par u impar
        }
        if (u > v) {// ambos são impares
            return gcd((u - v) >> 1, v);
        } else {
            return gcd(u, (v - u) >> 1);
        }
    }

}
