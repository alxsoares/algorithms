package alex.algorithms.geom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Ponto implements Comparable {
    public int x;
    public int y;
    public int xt;
    public int yt;
    public int quadrante;

    public Ponto() {

    }

    public Ponto(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getQuadrante() {
        if (xt >= 0 && yt >= 0)
            return 1;
        if (xt < 0 && yt >= 0)
            return 2;
        if (xt < 0 && yt < 0)
            return 3;
        return 4;
    }

    public void Translada(final int x, final int y) {
        this.xt = this.x - x;
        this.yt = this.y - y;
    }

    @Override
    public int compareTo(final Object o) {
        Ponto p = (Ponto) o;
        if (this.getQuadrante() < p.getQuadrante())
            return -1;
        if (this.getQuadrante() > p.getQuadrante())
            return 1;
        if (this.xt == p.xt && this.yt == p.yt)
            return 0;
        if (this.yt == 0 && p.yt == 0) {
            if (this.xt > p.xt)
                return 1;
            return -1;
        }
        if (this.yt * p.xt > this.xt * p.yt)
            return 1;
        return -1;
    }

    @Override
    public String toString() {
        return "X = " + this.x + " Y = " + this.y;
    }

    public static void main(final String[] args) {
        List<Ponto> list = new ArrayList<Ponto>();
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < 20; i++) {
            Ponto p = new Ponto(rand.nextInt() % 10, rand.nextInt() % 10);
            list.add(p);
        }
        for (Iterator<Ponto> iter = list.iterator(); iter.hasNext();) {
            Ponto item = iter.next();
            System.out.println(item);
        }
        Collections.<Ponto> sort(list);
        System.out.println("Depois de Ordenar");
        for (Iterator<Ponto> iter = list.iterator(); iter.hasNext();) {
            Ponto item = iter.next();
            System.out.println(item);
        }

    }

}
