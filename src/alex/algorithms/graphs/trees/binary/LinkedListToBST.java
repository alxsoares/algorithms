package alex.algorithms.graphs.trees.binary;

import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListToBST {

	public static Node<Integer> buildTree(LinkedList<Integer> list, int n) {
		if (n <= 0)
			return null;
		Node<Integer> left = buildTree(list, n / 2);
		Node<Integer> root = new Node<Integer>(list.pollFirst());
		Node<Integer> right = buildTree(list, n - 1 - n / 2);
		root.setLeft(left);
		root.setRight(right);
		return root;
	}

	public static void printTreeInOrder(Node<Integer> root) {
		if (root == null)
			return;
		printTreeInOrder(root.getLeft());
		System.out.printf("%d ", root.getValue());
		printTreeInOrder(root.getRight());
	}

	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.addAll(Arrays.asList(new Integer[] { 2, 3, 4, 5, 6, 7, 8, 10, 11 }));
		Node<Integer> tree = buildTree(l, l.size());
		printTreeInOrder(tree);
		Node<Integer> lca = TreeAlgorithms.leastCommonAntecessor(tree, 10, 11);
		System.out.printf("\n%d\n", lca.getValue());
		System.out.println(TreeAlgorithms.isBST(tree, Integer.MIN_VALUE,
				Integer.MAX_VALUE));
	}

}
