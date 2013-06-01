package alex.datastructures;

import java.util.Stack;

public class StackWithMinTrickier  {

	private Integer min;
	private Stack<Integer> s2;

	public StackWithMinTrickier() {
		s2 = new Stack<>();
		min = Integer.MAX_VALUE;
	}

	public Integer push(Integer num) {
		if (s2.isEmpty()) {
			s2.push(num);
			min = num;
		} else if (num < min) {
			int top = 2 * num - min;
			s2.push(top);
			min = num;
		} else {
			s2.push(num);
		}
		return num;
	}

	public Integer pop() {
		// if(super.isEmpty()) throw new EmptyStackException();
		int top = s2.pop();
		if (top < min) {
			int num = min;
			min = 2 * min - top;
			top = num;
		}
		return top;
	}

	public Integer peek() {
		// if(super.isEmpty()) throw new EmptyStackException();
		int top = s2.peek();
		if (top < min) {
			top = min;
		}
		return top;
	}

	public Integer min() {
		return min;
	}

	public static void main(String[] args) {
		StackWithMinTrickier s = new StackWithMinTrickier();
		s.push(10);
		s.push(9);
		System.out.println(s.peek() == s.min());
		s.push(20);
		System.out.println(s.peek() != s.min());
		System.out.println(s.min());
		s.push(8);
		System.out.println(s.min());
		System.out.println(s.peek());
		s.push(10);
		System.out.println(s.peek());
		System.out.println(s.min());
		System.out.println("Stack");
		while (!s.isEmpty()) {
			System.out.println(s.pop());
		}
	}

	private boolean isEmpty() {
		return s2.isEmpty();
	}

}
