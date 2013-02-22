package alex.algorithms.strings;

import java.util.ArrayList;
import java.util.Iterator;

public class Permutations {
	public static ArrayList<String> permutations(String s){
		ArrayList<String> r = new ArrayList<String>();
		if(s==null || "".equals(s)){
			r.add("");
			return r;
		}
		char first = s.charAt(0);
		ArrayList<String> p = permutations(s.substring(1));
		for (Iterator<String> iterator = p.iterator(); iterator.hasNext();) {
			String string =  iterator.next();
			for(int i=0; i <=string.length();i++ ){
				r.add(insertAt(string, first, i));
			}
		}
		
		return r;
	}
	public static String insertAt(String s, char c, int j){
		String s1 = s.substring(0, j);
		String s2 = s.substring(j);
		return s1+c+s2;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> p = permutations("abcdefghijklmn");
		for (String string : p) {
			System.out.println(string);
		}
	}

}
