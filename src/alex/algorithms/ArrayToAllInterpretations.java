package alex.algorithms;

import java.util.Arrays;

public class ArrayToAllInterpretations {
	public static Node createTree(int data, String pString, int[] arr) {

		// Invalid input as alphabets maps from 1 to 26
		if (data > 26)
			return null;

		// Parent String + String for this node
		String dataToStr = pString + alphabet[data];

		Node root = new Node(dataToStr);

		// if arr.length is 0 means we are done
		if (arr.length != 0) {
			data = arr[0];

			// new array will be from index 1 to end as we are consuming
			// first index with this node
			int newArr[] = Arrays.copyOfRange(arr, 1, arr.length);

			// left child
			root.left = createTree(data, dataToStr, newArr);

			// right child will be null if size of array is 0 or 1
			if (arr.length > 1) {

				data = arr[0] * 10 + arr[1];

				// new array will be from index 2 to end as we
				// are consuming first two index with this node
				newArr = Arrays.copyOfRange(arr, 2, arr.length);

				root.right = createTree(data, dataToStr, newArr);
			}
		}
		return root;
	}

	public static void printleaf(Node root) {
		if (root == null)
			return;

		if (root.left == null && root.right == null)
			System.out.print(root.getDataString() + "  ");

		printleaf(root.left);
		printleaf(root.right);
	}

	// The main function that prints all interpretations of array
	static void printAllInterpretations(int[] arr) {

		// Step 1: Create Tree
		Node root = createTree(0, "", arr);

		// Step 2: Print Leaf nodes
		printleaf(root);

		System.out.println(); // Print new line
	}

	// For simplicity I am taking it as string array. Char Array will save space
	private static final String[] alphabet = { "", "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "v", "z" };

	public static void main(String[] args) {

		int[] arr = { 1, 1, 3, 4 };
		printAllInterpretations(arr);

		// aaa(1,1,1) ak(1,11) ka(11,1)
		int[] arr2 = { 1, 1, 1 };
		printAllInterpretations(arr2);

		// bf(2,6) z(26)
		int[] arr3 = { 2, 6 };
		printAllInterpretations(arr3);

		// ab(1,2), l(12)
		int[] arr4 = { 1, 2 };
		printAllInterpretations(arr4);

		// a(1,0} j(10)
		int[] arr5 = { 1, 0 };
		printAllInterpretations(arr5);

		// "" empty string output as array is empty
		int[] arr6 = {};
		printAllInterpretations(arr6);

		// abba abu ava lba lu
		int[] arr7 = { 1, 2, 2, 1 };
		printAllInterpretations(arr7);
	}

}

class Node {
	String dataString;
	Node left;
	Node right;

	Node(String dataString) {
		this.dataString = dataString;
	}

	public String getDataString() {
		return dataString;
	}
}