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
		if (first.charAt(0) == '*' && first.length() == 1 && "".equals(second))
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

	public static boolean matchRegexp(String string, String pattern) {
		if (string == null || pattern == null)
			return false;
		return matchCore(string, pattern);
	}

	private static boolean matchCore(String string, String pattern) {
		if (string.length()==0 && pattern.length()==0)
			return true;
		if (string.length()!=0 && pattern.length()==0)
			return true;

		if (pattern.length()>=2 && pattern.charAt(1) == '*') {
			if (pattern.charAt(0) == string.charAt(0)
					|| (pattern.charAt(0) == '.' && string.length()!=0))
				// move on the next state
				return matchCore(string.substring(1), pattern.substring(2))
				// stay on the current state
						|| matchCore(string.substring(1), pattern)
						// ignore a '*'
						|| matchCore(string, pattern.substring(2));
			else
				// ignore a '*'
				return matchCore(string, pattern.substring(2));
		}
		if (string.charAt(0) == pattern.charAt(0)
				|| (pattern.charAt(0) == '.' && string.length()!=0))
			return matchCore(string.substring(1), pattern.substring(1));
		return false;
	}

	public static void main(String[] args) {
		System.out.println(matchRegexp("aaa", "a*a"));
		System.out.println(matchRegexp("ab", "a*a*c"));
		System.out.println(matchRegexp("aba", "a*a*c"));
	}

}
