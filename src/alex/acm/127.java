package alex.acm;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

class Main {

    Vector pilhas = new Vector();

    public void doMove() {
        boolean move = true;
        while (pilhas.size() > 1 && move) {
            for (int i = 1; i < pilhas.size(); i++) {
                Stack s1 = (Stack) pilhas.elementAt(i);
                Stack s2 = (Stack) pilhas.elementAt(i - 1);
                Stack s3 = null;
                Card c1 = (Card) s1.peek();
                Card c2 = (Card) s2.peek();
                Card c3 = null;
                if (i - 3 >= 0) {
                    s3 = (Stack) pilhas.elementAt(i - 3);
                    c3 = (Card) s3.peek();
                    if (c3.Match(c1)) {
                        s3.push(c1);
                        s1.pop();
                        if (s1.size() == 0) {
                            pilhas.removeElement(s1);
                        }
                        move = true;
                        break;
                    }
                }
                if (c2.Match(c1)) {
                    s2.push(c1);
                    s1.pop();
                    if (s1.size() == 0) {
                        pilhas.removeElement(s1);
                    }
                    move = true;
                    break;
                }

                move = false;
            }
        }
        System.out.print(pilhas.size() + " piles remaining:");
        for (int i = 0; i < pilhas.size(); i++) {
            Stack st = (Stack) pilhas.elementAt(i);
            System.out.print(" " + st.size());
        }
        System.out.println("");
    }

    public void buildPilhas(final String linha) {
        StringTokenizer tk = new StringTokenizer(linha);
        while (tk.hasMoreTokens()) {
            String carta = tk.nextToken();
            Card c = new Card(carta.charAt(0), carta.charAt(1));
            Stack st = new Stack();
            st.push(c);
            pilhas.addElement(st);
        }
    }

    static String ReadLn(final int maxLg) { // utility function to read from
                                            // stdin
        byte lin[] = new byte[maxLg];
        int lg = 0, car = -1;
        String line = "";

        try {
            while (lg < maxLg) {
                car = System.in.read();
                if ((car < 0) || (car == '\n'))
                    break;
                lin[lg++] += car;
            }
        } catch (IOException e) {
            return (null);
        }

        if ((car < 0) && (lg == 0))
            return (null); // eof
        return (new String(lin, 0, lg));
    }

    public static void main(final String[] args) {
        Main main = new Main();
        Vector linhas = new Vector(100);
        String linha = ReadLn(255);
        while (linha != null && !linha.trim().equals("#")) {
            String linha2 = ReadLn(255);
            linhas.addElement(linha + " " + linha2);
            linha = ReadLn(255);
        }
        for (int i = 0; i < linhas.size(); i++) {
            String seq = (String) linhas.elementAt(i);
            main.pilhas = new Vector(52);
            main.buildPilhas(seq);
            main.doMove();

        }
    }
}

class Card {
    public char suit;
    public char type;

    Card(final char c, final char s) {
        this.suit = s;
        this.type = c;
    }

    public boolean Match(final Card c) {
        if (c == null)
            return false;
        if (this.suit == c.suit || this.type == c.type) {
            return true;
        }
        return false;
    }
}

class Stack {
    Vector v = new Vector(52);

    Object pop() {
        Object o = v.lastElement();
        v.removeElement(o);
        return o;
    }

    Object peek() {
        if (v.size() == 0)
            return null;
        Object o = v.lastElement();
        return o;
    }

    void push(final Object o) {
        v.addElement(o);
    }

    int size() {
        return v.size();
    }
}
