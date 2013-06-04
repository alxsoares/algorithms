package alex.algorithms.graphs.trees.binary;

import java.util.Random;

public class LowestCommonAntecessor {
	public static Node<Integer> lca(Node<Integer> root, int v1, int v2) {
		int min = Math.min(v1, v2);
		int max = Math.max(v1, v2);
		while (root != null) {
			int value = root.getValue();
			if (value < min && value < max) {
				root = root.getRight();
			} else if (value > min && value > max) {
				root = root.getLeft();
			} else if (value >= min && value <= max) {
				return root;
			}
		}

		return null;
	}

	public static Node<Integer> lcaR(Node<Integer> root, int v1, int v2) {
		if (root == null)
			return null;
//		if (root.getLeft() == null && root.getRight() == null)
//			return null;
		int min = Math.min(v1, v2);
		int max = Math.max(v1, v2);
		if (root.getRight() != null && root.getValue() == min)
			return root;
		if (root.getLeft() != null && root.getValue() == max)
			return root;
		if (root.getValue() > min && root.getValue() > max)
			return lcaR(root.getLeft(), v1, v2);
		if (root.getValue() < min && root.getValue() < max)
			return lcaR(root.getRight(), v1, v2);
		if (root.getValue() >= min && root.getValue() <= max)
			return root;
		return null;
	}

	public static void main(String[] args) {
		Random rand = new Random();
		for (int j = 0; j < 100; j++) {
			Node<Integer> root = null;
			for (int i = 0; i < 20; i++) {
				root = TreeAlgorithms.insertNodeBST(root,
						Math.abs(rand.nextInt()) % 200);
			}
			int v1 = Math.abs(rand.nextInt()) % 20;
			int v2 = v1+ Math.abs(rand.nextInt()) % 20;
			root = TreeAlgorithms.insertNodeBST(root, v1);
			root = TreeAlgorithms.insertNodeBST(root, v2);
			System.out.printf("%d %d %d\n", v1, v2, lca(root, v1, v2)
					.getValue());
			System.out.printf("%d %d %d\n", v1, v2, lcaR(root, v1, v2)
					.getValue());
		}
	}

}
