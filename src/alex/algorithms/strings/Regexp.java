package alex.algorithms.strings;

public class Regexp {

	public static boolean match(String first, String second) {
		// If we reach at the end of both strings, we are done
		if ("".equals(first) && "".equals(second))
			return true;

		if ("".equals(first) && !"".equals(second))
			return false;
		if (!"".equals(first) && "".equals(second))
			return false;
		// Make sure that the characters after '*' are present in second string.
		// This function assumes that the first string will not contain two
		// consecutive '*'
		if (first.charAt(0) == '*' && first.length()==1
				&& "".equals(second))
			return false;

		// If the first string contains '?' or the current characters of
		// both strings match
		if (first.charAt(0) == '?' || first.charAt(0) == second.charAt(0))
				return match(first.substring(1), second.substring(1));

		// If there is *, then there are two possibilities
		// a) We consider current character of second string
		// b) We ignore current character of second string.
		if (first.charAt(0) == '*')
			return match(first.substring(1), second)
					|| match(first, second.substring(1));
		return false;
	}

	public static void test(String first, String second) {
		if (match(first, second)) {
			System.out.println("Yes");
		} else
			System.out.println("No");
	}

	public static void main(String[] args) {
		 test("g*ks", "geeks"); // Yes
		    test("ge?ks*", "geeksforgeeks"); // Yes
		    test("g*k", "gee");  // No because 'k' is not in second
		    test("*pqrs", "pqrst"); // No because 't' is not in first
		    test("abc*bcd", "abcdhghgbcd"); // Yes
		    test("abc*c?d", "abcd"); // No because second must have 2 instances of 'c'
		    test("*c*d", "abcd"); // Yes
		    test("*?c*d", "abcd"); // Yes
	}

}
