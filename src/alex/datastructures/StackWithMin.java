package alex.datastructures;

import java.util.Stack;

public class StackWithMin extends Stack<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3221615713264524287L;
	Stack<Integer> s2;

	public StackWithMin() {
		s2 = new Stack<Integer>();
	}

	public void push(int value) {
		super.push(value);
		if (value <= min()) {
			s2.push(value);
		}
	}

	public Integer pop() {
		int value = super.pop();
		if (value == min()) {
			s2.pop();
		}
		return value;
	}

	public Integer min() {
		if (s2.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return s2.peek();
		}
	}

	public static void main(String[] args) {

	}

}
