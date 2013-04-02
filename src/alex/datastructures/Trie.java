package alex.datastructures;

import java.util.HashMap;

public class Trie {
	private int count;
	private TrieNode root;

	public Trie() {
		this.root = TrieNode.newNode();
		this.count = 0;
	}

	public void insert(String key) {

		count++;
		TrieNode p = root;

		for (int level = 0; level < key.length(); level++) {
			int index = key.charAt(level) - 'a';
			if (p.children[index] == null) {
				p.children[index] = TrieNode.newNode();
			}

			p = p.children[index];
		}

		// mark last node as leaf
		p.value = count;
	}

	public boolean search(String key) {
		TrieNode p = root;

		for (int level = 0; level < key.length(); level++) {
			int index = key.charAt(level) - 'a';

			if (p.children[index] == null) {
				return false;
			}

			p = p.children[index];
		}

		return (p != null && p.value > 0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Input keys (use only 'a' through 'z' and lower case)
	    String [] keys= {"the", "a", "there", "answer", "any", "by", "bye", "their"};
	    Trie trie = new Trie();
	 
	   HashMap<Boolean, String> output = new HashMap<>();
	   output.put(true, "Present in trie");
	   output.put(false, "Not present in trie");
	 
	    // Construct trie
	    for(int i = 0; i < keys.length; i++)
	    {
	        trie.insert(keys[i]);
	    }
	 
	    // Search for different keys
	    System.out.printf("%s --- %s\n", "the", output.get(trie.search("the") ));
	    System.out.printf("%s --- %s\n", "these", output.get(trie.search( "these")) );
	    System.out.printf("%s --- %s\n", "their", output.get(trie.search( "their")) );
	    System.out.printf("%s --- %s\n", "thaw", output.get(trie.search( "thaw")) );

	}

}

class TrieNode {
	private static final int ALPHABET_SIZE = 26;
	int value;
	TrieNode[] children = new TrieNode[ALPHABET_SIZE];

	public static TrieNode newNode() {
		TrieNode node = new TrieNode();
		node.value = 0;

		return node;
	}

}
