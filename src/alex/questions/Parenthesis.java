package alex.questions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Parenthesis {
	public static boolean isWellFormed(String line) {
		if (line == null || line.length() == 0 || "".equals(line.trim()))
			return true;
		String valid = "(){}[]";
		char[] tokens = line.toCharArray();
		Stack<String> exp = new Stack<String>();
		for (int i = 0; i < tokens.length; i++) {
			String token = String.valueOf(tokens[i]);
			if (valid.indexOf(token) < 0)
				return false;
			switch (tokens[i]) {
			case '(':
			case '[':
			case '{':
				exp.push(token);
				break;
			case ')':
				if (exp.peek().equals("(")) {
					exp.pop();
				} else {
					return false;
				}
				break;
			case ']':
				if (exp.peek().equals("[")) {
					exp.pop();
				} else {
					return false;
				}
				break;
			case '}':
				if (exp.peek().equals("{")) {
					exp.pop();
				} else {
					return false;
				}
				break;
			default:
				throw new IllegalArgumentException("Symbol not expected.");
			}
		}
		return (exp.isEmpty());
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println("Expected file name as argument.");
			return;
		}
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				if (isWellFormed(line.trim())) {
					System.out.println("True");
				} else {
					System.out.println("False");
				}
			}
		} finally {
			reader.close();
		}
	}

}
