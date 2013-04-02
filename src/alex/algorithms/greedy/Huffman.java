package alex.algorithms.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Huffman {

	HeapNode buildHuffmanTree(final char data[], final int freq[], int size) {
		HeapNode left, right, top;

		PriorityQueue<HeapNode> heap = new PriorityQueue<>(size,
				new Comparator<HeapNode>() {

					@Override
					public int compare(HeapNode o1, HeapNode o2) {
						return o1.freq.compareTo(o2.freq);
					}
				});
		for (int i = 0; i < size; i++) {
			heap.add(HeapNode.newNode(data[i], freq[i]));
		}

		while (heap.size() != 1) {
			left = heap.poll();
			right = heap.poll();

			top = HeapNode.newNode('$', left.freq + right.freq);
			top.left = left;
			top.right = right;
			heap.add(top);
		}

		return heap.poll();
	}

	void printCodes(HeapNode root, int arr[], int top) {
		if (root.left != null) {
			arr[top] = 0;
			printCodes(root.left, arr, top + 1);
		}

		if (root.right != null) {
			arr[top] = 1;
			printCodes(root.right, arr, top + 1);
		}

		if (root.left == null && root.right == null) {
			System.out.printf("%c: ", root.data);
			printArr(arr, top);
		}
	}

	void printArr(int arr[], int n) {
		int i;
		for (i = 0; i < n; ++i)
			System.out.printf("%d", arr[i]);
		System.out.printf("\n");
	}

	void HuffmanCodes(char data[], int freq[], int size) {
		HeapNode root = buildHuffmanTree(data, freq, size);

		int arr[] = new int[100], top = 0;
		printCodes(root, arr, top);
	}

	public static void main(String[] args) {
		char arr[] = { 'a', 'b', 'c', 'd', 'e', 'f' };
		int freq[] = { 5, 9, 12, 13, 16, 45 };
		int size = arr.length;
		Huffman huffman = new Huffman();
		huffman.HuffmanCodes(arr, freq, size);

	}

}

class HeapNode {
	char data;
	Integer freq;
	HeapNode left;
	HeapNode right;

	static HeapNode newNode(char data, Integer freq) {
		HeapNode n = new HeapNode();
		n.data = data;
		n.freq = freq;
		n.left = n.right = null;
		return n;
	}
}
