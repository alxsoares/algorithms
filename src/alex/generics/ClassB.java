package alex.generics;

public class ClassB<E extends Number, D extends Number> {
    E a;
    D b;

    public ClassB(final E a, final D b) {
        this.a = a;
        this.b = b;
    }

    public void print() {
        System.out.println(a + " " + b);
    }

    public static void main(final String[] args) {
        ClassB<Integer, Integer> a = new ClassB<Integer, Integer>(1, 2);
        ClassB<Double, Integer> b = new ClassB<Double, Integer>(1.0, 2);
        a.print();
        b.print();
    }
}
