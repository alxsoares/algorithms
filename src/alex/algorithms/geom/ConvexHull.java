package alex.algorithms.geom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class ConvexHull {
    public static void getPivot(final Ponto pontos[]) {
        int menor = 0;
        for (int i = 1; i < pontos.length; i++) {
            if (pontos[i].y < pontos[menor].y) {
                menor = i;
            }
        }
        Ponto aux = pontos[0];
        pontos[0] = pontos[menor];
        pontos[menor] = aux;
    }

    public static int CrossProduct(final Ponto a, final Ponto b, final Ponto c) {
        return ((c.xt - a.xt) * (b.yt - a.yt) - (b.xt - a.xt) * (c.yt - a.yt));
    }

    public static void translate(final Ponto[] pontos, final Ponto pivot) {

        for (int i = 0; i < pontos.length; i++) {
            Ponto p = pontos[i];
            p.Translada(pivot.x, pivot.y);
        }
    }

    public static void GrahamScan(final Ponto pontos[]) {
        getPivot(pontos);
        Stack<Ponto> stack = new Stack<Ponto>();
        Ponto pivot = pontos[0];
        translate(pontos, pivot);
        List<Ponto> list = new ArrayList<Ponto>();
        for (int i = 1; i < pontos.length; i++) {
            list.add(pontos[i]);
        }
        Collections.<Ponto> sort(list);
        System.out.println("Ordernado");
        for (Iterator<Ponto> iter = list.iterator(); iter.hasNext();) {
            Ponto item = iter.next();
            System.out.println(item);
        }
        System.out.println("FIM");
        Ponto a = pivot;
        Ponto b = list.get(0);
        stack.push(a);
        stack.push(b);
        for (int i = 1; i < list.size(); i++) {
            Ponto c = list.get(i);
            b = stack.pop();
            a = stack.pop();
            stack.push(a);
            stack.push(b);
            while (CrossProduct(a, b, c) < 0) {
                stack.pop();
                if (stack.size() >= 2) {
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a);
                    stack.push(b);
                } else {
                    break;
                }
            }
            stack.push(c);
        }
        while (!stack.empty()) {
            Ponto p = stack.pop();
            System.out.println(p);
        }

    }

    public static void main(final String[] args) {
        Ponto pontos[] = new Ponto[14];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            Ponto p = new Ponto(Math.abs(rand.nextInt() % 5) + 1, Math.abs(rand.nextInt() % 5) + 1);
            pontos[i] = p;
        }
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(0, 10);
        Ponto c = new Ponto(10, 10);
        Ponto d = new Ponto(10, 0);
        pontos[10] = a;
        pontos[11] = b;
        pontos[12] = c;
        pontos[13] = d;
        List<Ponto> list = Arrays.<Ponto> asList(pontos);
        Collections.sort(list);
        /*
         * for (Iterator iter = list.iterator(); iter.hasNext(); ) { Ponto item
         * = (Ponto) iter.next(); //System.out.println("("+item+")"); //
         * System.out.println("("+item.xt+" "+item.yt+")"); } for (Iterator iter
         * = list.iterator(); iter.hasNext(); ) { Ponto item = (Ponto)
         * iter.next(); System.out.println("("+item+")");
         * //System.out.println("("+item.xt+" "+item.yt+")"); }
         */

        GrahamScan(pontos);

    }

}
