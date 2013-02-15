package alex.generics;

public class ClassA<E extends Number> {
    E a;
    E b;

    public ClassA(final E a, final E b) {
        this.a = a;
        this.b = b;
    }

    public void print() {
        System.out.println(a + " " + b);
    }

    public static void main(final String[] args) {
        ClassA<Integer> a = new ClassA<Integer>(1, 2);
        ClassA<Double> b = new ClassA<Double>(1.0, 2.0);
        a.print();
        b.print();
    }
}
