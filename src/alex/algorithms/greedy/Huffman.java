package alex.algorithms.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Huffman {

	HuffmanTreeNode buildHuffmanTree(final char data[], final int freq[], int size) {
		HuffmanTreeNode left, right, top;

		PriorityQueue<HuffmanTreeNode> heap = new PriorityQueue<>(size,
				new Comparator<HuffmanTreeNode>() {

					@Override
					public int compare(HuffmanTreeNode o1, HuffmanTreeNode o2) {
						return o1.freq.compareTo(o2.freq);
					}
				});
		for (int i = 0; i < size; i++) {
			heap.add(HuffmanTreeNode.newNode(data[i], freq[i]));
		}

		while (heap.size() != 1) {
			left = heap.poll();
			right = heap.poll();

			top = HuffmanTreeNode.newNode('$', left.freq + right.freq);
			top.left = left;
			top.right = right;
			heap.add(top);
		}

		return heap.poll();
	}

	void printCodes(HuffmanTreeNode root, int buffer[], int top) {
		if (root.left != null) {
			buffer[top] = 0;
			printCodes(root.left, buffer, top + 1);
		}

		if (root.right != null) {
			buffer[top] = 1;
			printCodes(root.right, buffer, top + 1);
		}

		if (root.left == null && root.right == null) {
			System.out.printf("%c: ", root.data);
			print(buffer, top);
		}
	}

	void print(int buffer[], int n) {
		int i;
		for (i = 0; i < n; ++i)
			System.out.printf("%d", buffer[i]);
		System.out.printf("\n");
	}

	void huffmanCodes(char data[], int freq[], int size) {
		HuffmanTreeNode root = buildHuffmanTree(data, freq, size);

		int arr[] = new int[100], top = 0;
		printCodes(root, arr, top);
	}

	public static void main(String[] args) {
		char arr[] = { 'a', 'b', 'c', 'd', 'e', 'f' };
		int freq[] = { 5, 9, 12, 13, 16, 45 };
		int size = arr.length;
		Huffman huffman = new Huffman();
		huffman.huffmanCodes(arr, freq, size);

	}

}

class HuffmanTreeNode {
	char data;
	Integer freq;
	HuffmanTreeNode left;
	HuffmanTreeNode right;

	static HuffmanTreeNode newNode(char data, Integer freq) {
		HuffmanTreeNode n = new HuffmanTreeNode();
		n.data = data;
		n.freq = freq;
		n.left = n.right = null;
		return n;
	}
}
