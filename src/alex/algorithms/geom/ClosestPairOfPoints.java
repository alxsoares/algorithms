package alex.algorithms.geom;

import java.util.AbstractList;
import java.util.List;
import java.util.Random;

public class ClosestPairOfPoints {

    /**
     * minDist = infinity for i = 1 to length(P) - 1 for j = i + 1 to length(P)
     * let p = P[i], q = P[j] if dist(p, q) < minDist: minDist = dist(p, q)
     * closestPair = (p, q) return closestPair
     * 
     * @param points
     */
    public static double closestPairOfPointsBruteForce(final Ponto[] points) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            Ponto p1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Ponto p2 = points[j];
                double dist = dist(p1, p2);
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return Math.sqrt(minDist);
    }

       public List<Integer> getList(final int a[]) {
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

    public static double dist(final Ponto p1, final Ponto p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }

    public static void main(final String[] args) {
        Ponto pontos[] = new Ponto[14];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < 14; i++) {
            Ponto p = new Ponto(Math.abs(rand.nextInt()) % 20, Math.abs(rand.nextInt()) % 20);
            pontos[i] = p;
        }
        System.out.printf("%f\n", closestPairOfPointsBruteForce(pontos));
    }

}
