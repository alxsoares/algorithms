package alex.algorithms.geom;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class NearestPairLogn {
    static final int PONTOS = 200;
    static int X[] = new int[PONTOS];
    static int Y[] = new int[PONTOS];
    static int fY[] = new int[PONTOS];
    static boolean pontosIguais = false;

    static void divide(final int px[], final int py[], final int px_esq[], final int py_esq[], final int n_esq[],
            final int px_dir[], final int py_dir[], final int n_dir[], final int n) {
        n_esq[0] = n / 2;
        n_dir[0] = n - n_esq[0];
        int i;
        for (i = 0; i < n_esq[0]; i++) {
            px_esq[i] = px[i];
        }
        for (int j = 0; j < n_dir[0]; j++, i++) {
            px_dir[j] = px[i];
        }
        double XC = X[px[n_esq[0]]];
        double YC = Y[px[n_esq[0]]];
        int j = 0, k = 0;

        for (i = 0; i < n; i++)
            if (X[py[i]] < XC) {
                py_esq[j] = py[i];
                j++;
            } else if (X[py[i]] > XC) {
                py_dir[k] = py[i];
                k++;
            } else {
                if (Y[py[i]] < YC) {
                    py_esq[j] = py[i];
                    j++;
                } else {
                    py_dir[k] = py[i];
                    k++;
                }
            }

    }

    static double distQuadratica(final int a, final int b) {
        double deltaX = X[a] - X[b];
        double deltaY = Y[a] - Y[b];

        return (deltaX * deltaX + deltaY * deltaY);
    }

    static void combina(final int px[], final int py[], final int i_esq, final int j_esq, final int i_dir,
            final int j_dir, final int n, final int ponto1[], final int ponto2[]) {
        double distEsquerda = distQuadratica(i_esq, j_esq);
        double distDireita = distQuadratica(i_dir, j_dir);
        double delta;
        if (distEsquerda <= distDireita) {
            delta = distEsquerda;
            ponto1[0] = i_esq;
            ponto2[0] = j_esq;
        } else {
            delta = distDireita;
            ponto1[0] = i_dir;
            ponto2[0] = j_dir;
        }

        double XC = X[px[n / 2]];
        int t = 0;
        for (int i = 0; i < n; i++) {
            double dX = X[py[i]] - XC;
            if (dX * dX <= delta) {
                fY[t] = py[i];
                t++;
            }
        }

        for (int i = 0; i < t - 1; i++) {
            for (int j = i + 1; j <= i + 7 && j < t; j++) {
                double d = distQuadratica(fY[i], fY[j]);
                if (d < delta) {
                    delta = d;
                    ponto1[0] = fY[i];
                    ponto2[0] = fY[j];
                }
            }
        }
    }

    static void parMaisProxRec(final int px[], final int py[], final int n, final int ponto1[], final int ponto2[]) {
        if (n == 2) {
            ponto1[0] = px[0];
            ponto2[0] = px[1];
        } else if (n == 3) {
            double p0p1 = distQuadratica(px[0], px[1]);
            double p1p2 = distQuadratica(px[1], px[2]);
            double p0p2 = distQuadratica(px[0], px[2]);
            double minimo = Math.min(p0p1, Math.min(p1p2, p0p2));
            if (minimo == p0p1) {
                ponto1[0] = px[0];
                ponto2[0] = px[1];

            } else if (minimo == p1p2) {
                ponto1[0] = px[1];
                ponto2[0] = px[2];
            } else {
                ponto1[0] = px[0];
                ponto2[0] = px[2];
            }
        } else {
            int px_esq[] = new int[PONTOS], py_esq[] = new int[PONTOS], n_esq[] = new int[1];
            int px_dir[] = new int[PONTOS], py_dir[] = new int[PONTOS], n_dir[] = new int[1];
            int i_esq[] = new int[1], j_esq[] = new int[1], i_dir[] = new int[1], j_dir[] = new int[1];

            divide(px, py, px_esq, py_esq, n_esq, px_dir, py_dir, n_dir, n);

            parMaisProxRec(px_esq, py_esq, n_esq[0], i_esq, j_esq);

            parMaisProxRec(px_dir, py_dir, n_dir[0], i_dir, j_dir);

            combina(px, py, i_esq[0], j_esq[0], i_dir[0], j_dir[0], n, ponto1, ponto2);

        }

    }

    static int ordenaX(final int pa, final int pb) {
        if (X[pa] > X[pb]) {
            return 1;
        }
        if (X[pa] < X[pb]) {
            return -1;
        }
        if (Y[pa] > Y[pb]) {
            return 1;
        }
        if (Y[pa] < Y[pb]) {
            return -1;
        }
        if (pa != pb) {
            System.out.printf("%d %d\n", pa, pb);
            pontosIguais = true;
        }
        return 0;

    }

    static int ordenaY(final int pa, final int pb) {
        if (Y[pa] > Y[pb]) {
            return 1;
        }
        if (Y[pa] < Y[pb]) {
            return -1;
        }
        return 0;

    }

    public static List<Integer> getList(final int a[]) {
        return new AbstractList<Integer>() {

            @Override
            public Integer get(final int index) {
                return a[index];
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }

    public static int[] getArray(final List<Integer> l) {
        int[] a = new int[(l.size())];
        int i = 0;
        for (Iterator<Integer> iterator = l.iterator(); iterator.hasNext();) {
            a[i++] = iterator.next();

        }
        return a;
    }

    public static double closestPairOfPointsBruteForce(final Ponto[] points) {
        double minDist = Double.MAX_VALUE;
        Ponto a = null;
        Ponto b = null;
        for (int i = 0; i < points.length; i++) {
            Ponto p1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Ponto p2 = points[j];
                double dist = dist(p1, p2);
                if (dist < minDist) {
                    a = p1;
                    b = p2;
                    minDist = dist;
                }
            }
        }
        System.out.printf("Brute Pontos => %d %d %d %d\nDistancia =>%f\n", a.x, a.y, b.x, b.y, Math.sqrt(minDist));
        return Math.sqrt(minDist);
    }

    public static double dist(final Ponto p1, final Ponto p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        int px[] = new int[PONTOS];
        int py[] = new int[PONTOS];
        int ponto1[] = new int[1], ponto2[] = new int[1];
        Ponto pontos[] = new Ponto[PONTOS];
        int numPoints = 0;
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < PONTOS; i++) {
            X[i] = rand.nextInt() % 400;
            Y[i] = rand.nextInt() % 400;
            Ponto p = new Ponto(X[i], Y[i]);
            pontos[i] = p;
            px[i] = i;
            py[i] = i;
            numPoints++;
        }
        for (int i = 0; i < X.length; i++) {
            System.out.printf("%d %d\n", X[i], Y[i]);
        }
        List<Integer> lx = new ArrayList<Integer>(getList(px));
        Collections.sort(lx, new Comparator<Integer>() {

            @Override
            public int compare(final Integer o1, final Integer o2) {
                return ordenaX(o1, o2);
            }
        });

        List<Integer> ly = new ArrayList<Integer>(getList(py));
        Collections.sort(ly, new Comparator<Integer>() {

            @Override
            public int compare(final Integer o1, final Integer o2) {
                return ordenaY(o1, o2);
            }
        });
        if (pontosIguais) {
            System.out.printf("%d\n", 0);
            pontosIguais = false;
            return;
        }
        px = getArray(lx);
        py = getArray(ly);
        parMaisProxRec(px, py, numPoints, ponto1, ponto2);
        double dist = Math.sqrt(distQuadratica(ponto1[0], ponto2[0]));
        System.out.printf("Pontos => %d %d %d %d\nDistancia =>%f\n", X[ponto1[0]], Y[ponto1[0]], X[ponto2[0]],
                Y[ponto2[0]], dist);

        System.out.printf("Distancia=>%f\n", closestPairOfPointsBruteForce(pontos));

    }
}
