package alex.algorithms.graphs.trees.binary;

import java.util.LinkedList;
import java.util.Random;

public class TreeWidth {

	public static int heightR(Node<Integer> root) {
		if (root == null)
			return 0;
		return Math.max(heightR(root.getLeft()), heightR(root.getRight())) + 1;
	}

	public static int width(Node<Integer> root, int level) {
		if (root == null)
			return 0;
		if (level == 1)
			return 1;
		return width(root.getLeft(), level - 1)
				+ width(root.getRight(), level - 1);
	}

	public static int width(Node<Integer> root) {
		int height = heightR(root);
		int maxWidth = Integer.MIN_VALUE;
		for (int i = 1; i <= height; i++) {
			maxWidth = Math.max(maxWidth, width(root, i));
		}
		return maxWidth;
	}
	public static int widthIter(Node<Integer> root) {
		if (root == null)
			return 0;
		LinkedList<Node<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int width = Integer.MIN_VALUE;
		while (queue.size()>0) {
			int nodeCount = queue.size();
			width = Math.max(nodeCount, width);
			
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
		return width;
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Node<Integer> root = null;
		for (int i = 0; i < 20; i++) {
			root = TreeAlgorithms.insertNodeBST(root,
					Math.abs(rand.nextInt()) % 200);
		}
		System.out.println(width(root));
		System.out.println(widthIter(root));
	}

}
