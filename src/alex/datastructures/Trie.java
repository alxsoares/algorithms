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

	boolean deleteHelper(TrieNode node, String key, int level, int length) {
		if (node != null) {
			// Base case
			if (level == length) {
				if (node.value != 0) {
					// Unmark leaf node
					node.value = 0;

					// If empty, node to be deleted
					if (node.isItFreeNode()) {
						return true;
					}

					return false;
				}
			} else // Recursive case
			{
				int index = key.charAt(level) - 'a';

				if (deleteHelper(node.children[index], key, level + 1, length)) {
					// last node marked, delete it
					node.children[index] = null;

					// recursively climb up, and delete eligible nodes
					return (node.value == 0 && node.isItFreeNode());
				}
			}
		}

		return false;
	}

	void deleteKey(String key) {
		int len = key.length();

		if (len > 0) {
			deleteHelper(root, key, 0, len);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Input keys (use only 'a' through 'z' and lower case)
		String[] keys = { "the", "a", "there", "answer", "any", "by", "bye",
				"their" };
		Trie trie = new Trie();

		HashMap<Boolean, String> output = new HashMap<Boolean, String>();
		output.put(true, "Present in trie");
		output.put(false, "Not present in trie");

		// Construct trie
		for (int i = 0; i < keys.length; i++) {
			trie.insert(keys[i]);
		}

		// Search for different keys
		System.out.printf("%s --- %s\n", "the", output.get(trie.search("the")));
		System.out.printf("%s --- %s\n", "these",
				output.get(trie.search("these")));
		System.out.printf("%s --- %s\n", "their",
				output.get(trie.search("their")));
		System.out.printf("%s --- %s\n", "thaw",
				output.get(trie.search("thaw")));
		trie.deleteKey("their");
		System.out.printf("%s --- %s\n", "the", output.get(trie.search("the")));
		System.out.printf("%s --- %s\n", "these",
				output.get(trie.search("these")));
		System.out.printf("%s --- %s\n", "their",
				output.get(trie.search("their")));
		System.out.printf("%s --- %s\n", "thaw",
				output.get(trie.search("thaw")));
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

	boolean isItFreeNode() {
		int i;
		for (i = 0; i < ALPHABET_SIZE; i++) {
			if (children[i] != null)
				return false;
		}

		return true;
	}

}
