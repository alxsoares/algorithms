package alex.algorithms.graphs.trees.binary;

import java.util.Stack;

public class PrintAncestorNonRecursive {
	/**
	 * Print Path with ancestors of a given key
	 * 
	 * @param root
	 * @param key
	 */
	public static void printAncestors(Node<Integer> root, Integer key) {
		if (root == null)
			return;
		Stack<Node<Integer>> stack = new Stack<>();
		while (true) {

			while (root != null && root.getValue() != key) {
				stack.push(root);
				root = root.getLeft();
			}
			if (root != null && root.getValue() == key) {
				break;
			}
			if (stack.peek().getRight() == null) {
				root = stack.pop();
				//I am in a right side of a subtree
				//I have to climb the tree and go to another righty subtree
				while (!stack.isEmpty() && stack.peek().getRight() == root) {
					root = stack.pop();
				}
			}
			if (stack.isEmpty()) {
				root = null;
			} else {
				root = stack.peek().getRight();
			}
		}
		while (!stack.isEmpty()) {
			Node<Integer> n = stack.pop();
			System.out.printf("%d ", n.getValue());
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node<Integer> root = new Node<Integer>(1);
		root.setLeft(new Node<Integer>(2));
		root.setRight(new Node<Integer>(3));
		root.getLeft().setLeft(new Node<Integer>(4));
		root.getLeft().setRight(new Node<Integer>(5));
		root.getRight().setLeft(new Node<Integer>(6));
		root.getRight().setRight(new Node<Integer>(7));
		root.getLeft().getLeft().setLeft(new Node<Integer>(8));
		root.getLeft().getRight().setRight(new Node<Integer>(9));
		root.getRight().getRight().setLeft(new Node<Integer>(10));

		System.out.printf("Following are all keys and their ancestors\n");
		for (int key = 1; key <= 10; key++) {
			System.out.printf("%d: ", key);
			printAncestors(root, key);
			System.out.printf("\n");
		}
	}
}
