package alex.datastructures;

import java.util.HashMap;

public class TrieWithHashMap {

	private TrieNodeH root;

	public TrieWithHashMap() {
		this.root = new TrieNodeH((char) 0);
	}

	public void insert(String word) {
		int length = word.length();
		TrieNodeH crawl = root;
		for (int level = 0; level < length; level++) {
			HashMap<Character, TrieNodeH> children = crawl.getChildren();
			char c = word.charAt(level);
			if (children.containsKey(c)) {
				crawl = children.get(c);
			} else {
				TrieNodeH newNode = new TrieNodeH(c);
				children.put(c, newNode);
				crawl = newNode;
			}
		}
		crawl.setEnd(true);
	}

	public String getMatchingPrefix(String input) {
		String res = "";
		int length = input.length();
		int prevMatch = 0;
		TrieNodeH crawl = root;
		for (int level = 0; level < length; level++) {
			char c = input.charAt(level);
			HashMap<Character, TrieNodeH> children = crawl.getChildren();
			if (children.containsKey(c)) {
				res += c;
				crawl = children.get(c);
				if (crawl.isEnd()) {
					prevMatch = level + 1;
				}
			} else
				break;
		}
		if (!crawl.isEnd()) {
			return res.substring(0, prevMatch);
		}
		return res;
	}

	public static void main(String[] args) {
		TrieWithHashMap dict = new TrieWithHashMap();       
        dict.insert("are");
        dict.insert("area");
        dict.insert("base");
        dict.insert("cat");
        dict.insert("cater");       
        dict.insert("basement");
        dict.insert("xy");
         
        String input = "caterer";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));             
 
        input = "basement";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));                     
         
        input = "are";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));             
 
        input = "arex";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));             
 
        input = "basemexz";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));                     
         
        input = "xyz";
        System.out.print(input + ":   ");
        System.out.println(dict.getMatchingPrefix(input));  

	}

}

class TrieNodeH {
	private char value;
	private HashMap<Character, TrieNodeH> children;
	private boolean isEnd;
	
	public TrieNodeH(char value) {
		super();
		this.value = value;
		this.children = new HashMap<>();
		this.isEnd = false;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public HashMap<Character, TrieNodeH> getChildren() {
		return children;
	}

	public void setChildren(HashMap<Character, TrieNodeH> children) {
		this.children = children;
	}



}
