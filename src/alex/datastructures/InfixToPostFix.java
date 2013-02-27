package alex.datastructures;

import java.util.Stack;
import java.util.StringTokenizer;

public class InfixToPostFix {

	private static Stack<String> stack;

	public static String Converting_infix_expressions_to_postfix_expressions(
			String infix) throws Exception {
		StringTokenizer st = new StringTokenizer(infix);
		int numOF_tokens = st.countTokens();
		String postfix = "";
		for (int i = 1; i <= numOF_tokens; i++) {
			String term = st.nextToken();
			try { // if it is an Float there is no problem will happen
				float x = Float.parseFloat(term);
				postfix += x + " ";
				System.out.println("term is number " + term);
			} catch (Exception e) {
				System.out.println("term is symbol " + term);
				if (stack.isEmpty())
					stack.push(term);
				else if (term.equals("("))
					stack.push(term);
				else if (term == ")") {
					while (!stack.peek().equals("("))
						postfix += stack.pop() + " ";
					stack.pop();
				}

				else {
					int x = 0, y = 0;
					switch (term) {
					case "+":
						x = 1;
						break;
					case "-":
						x = 1;
						break;
					case "*":
						x = 2;
						break;
					case "/":
						x = 2;
						break;
					}
					switch ((String) stack.peek()) {
					case "+":
						y = 1;
						break;
					case "-":
						y = 1;
						break;
					case "*":
						y = 2;
						break;
					case "/":
						y = 2;
						break;
					}
					if (x > y)
						stack.push(term);
					else {
						int x1 = x, y1 = y;
						boolean puchedBefore = false;
						while (x1 <= y1) {
							postfix += stack.pop() + " ";
							if (stack.isEmpty() || stack.peek().equals("(")) {
								stack.push(term);
								puchedBefore = true;
								break;
							} else {
								switch (term) {
								case "+":
									x1 = 1;
									break;
								case "-":
									x1 = 1;
									break;
								case "*":
									x1 = 2;
									break;
								case "/":
									x1 = 2;
									break;
								}
								switch ((String) stack.peek()) {
								case "+":
									y1 = 1;
									break;
								case "-":
									y1 = 1;
									break;
								case "*":
									y1 = 2;
									break;
								case "/":
									y1 = 2;
									break;
								}
							}
						}
						if (!puchedBefore)
							stack.push(term);
					}
				}
			}
		}
		while (!stack.isEmpty()) {
			postfix += stack.pop() + " ";
		}
		System.out.println("The postfix expression is : " + postfix);
		return postfix;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
