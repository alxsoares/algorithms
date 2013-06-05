package alex.algorithms.graphs.trees.binary;

import java.util.LinkedList;
import java.util.Random;

public class IterativeTreeHeight {

	public static int height(Node<Integer> root) {
		if (root == null)
			return 0;
		LinkedList<Node<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int height = 0;
		while (queue.size()>0) {
			int nodeCount = queue.size();

			height++;
			while (nodeCount > 0) {
				Node<Integer> node = queue.remove();
				nodeCount--;
				if (node.getLeft() != null) {
					queue.add(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.add(node.getRight());
				}
			}
		}
		return height;
	}

	public static int heightR(Node<Integer> root) {
		if (root == null)
			return 0;
		return Math.max(heightR(root.getLeft()), heightR(root.getRight())) + 1;
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Node<Integer> root = null;
		for (int i = 0; i < 20; i++) {
			root = TreeAlgorithms.insertNodeBST(root,
					Math.abs(rand.nextInt()) % 200);
		}
		System.out.println(height(root));
		System.out.println(heightR(root));
	}

}
