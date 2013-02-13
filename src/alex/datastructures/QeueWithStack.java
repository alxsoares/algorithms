package alex.datastructures;

import java.util.Stack;

public class QeueWithStack<T> {
    private final Stack<T> s1, s2;

    public QeueWithStack() {
        s1 = new Stack<T>();
        s2 = new Stack<T>();
    }

    public int size() {
        return s1.size() + s2.size();
    }

    public void add(final T e) {
        s1.push(e);
    }

    public T peek() {
        if (!s2.isEmpty())
            return s2.peek();
        while (!s1.isEmpty())
            s2.push(s1.pop());
        return s2.peek();
    }

    public T pop() {
        if (!s2.isEmpty())
            return s2.pop();
        while (!s1.isEmpty())
            s2.push(s1.pop());
        return s2.pop();
    }

    public static void main(final String[] args) {
        // TODO Auto-generated method stub

    }

}
