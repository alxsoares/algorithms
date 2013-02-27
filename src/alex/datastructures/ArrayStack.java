package alex.datastructures;

import java.util.Stack;

public class ArrayStack<E> extends Stack<E> {
	protected int capacity;
	protected static final int CAPACITY = 1000;
	protected E S[];
	protected int top = -1;

	public ArrayStack() {
		this(CAPACITY);
	}

	public ArrayStack(int cap) {
		capacity = cap;
		S = (E[]) new Object[capacity];
	}

	public int size() {
		return (top + 1);
	}

	public boolean isEmpty() {
		return (top < 0);
	}

	public E top() {
		if (isEmpty())
			throw new RuntimeException("Stack is Empty");
		return S[top];
	}

	public E push(E e) {
		if (size() == capacity)
			throw new RuntimeException("Stack is full");
		S[++top] = e;
		return e;
	}

	public E pop() {
		E o;
		if (isEmpty())
			throw new RuntimeException("Stack is Empty");
		o = S[top];
		S[top--] = null;
		return o;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i <= size() - 1; i++) {
			s += " " + S[i];
		}
		return s;
	}

	public String inFix(String s) {
		String output = "";
		ArrayStack<String> stackVar = new ArrayStack<String>();
		ArrayStack<String> stackOp = new ArrayStack<String>();
		for (int i = 0; i <= s.length() - 1; i++) {
			char c = s.charAt(i);
			if (c != '*' && c != '/' && c != '+' && c != '-' && c != '$') {
				String s1 = Character.toString(c);
				stackVar.push(s1);
			} else {
				String s2 = Character.toString(c);
				stackOp.push(s2);
				doOp(stackVar, stackOp);
			}
		}
		while (!stackVar.isEmpty())
			output += stackVar.pop();
		return output;
	}

	public void doOp(ArrayStack<String> a, ArrayStack<String> b) {
		String exp = "";
		String e1 = a.pop();
		String o = b.pop();
		String e2 = a.pop();
		exp += "(" + e1 + o + e2 + ")";
		a.push(exp);
	}

	public static void main(String[] args) {
		ArrayStack a = new ArrayStack();
		String st = a.inFix("ABXC*+-");
		System.out.println(st);
	}
}
