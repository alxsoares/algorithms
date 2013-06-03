package alex.algorithms.graphs.trees.binary;

import java.util.Random;

public class CheckTreeIsBST {
	public static boolean isBST(Node<Integer> root, int min, int max) {
		if (root == null)
			return true;
		if (root.getValue() < min || root.getValue() > max)
			return false;
		return isBST(root.getLeft(), min, root.getValue())
				&& isBST(root.getRight(), root.getValue(), max);
	}

	public static boolean isBSTInOrder(Node<Integer> root, NumPointer prev) {
		if (root == null)
			return true;
		return isBSTInOrder(root.getLeft(), prev)
				&& (root.getValue() >= prev.num)
				&& isBSTInOrder(root.getRight(), prev.update(root.getValue()));
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Node<Integer> root = null;
		for (int i = 0; i < 20; i++) {
			root = TreeAlgorithms.insertNodeBST(root,
					Math.abs(rand.nextInt()) % 200);
		}
		System.out.println(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out
				.println(isBSTInOrder(root, new NumPointer(Integer.MIN_VALUE)));

	}

}

class NumPointer {
	int num;

	public NumPointer(int num) {
		this.num = num;
	}

	public NumPointer update(int num) {
		this.num = num;
		return this;
	}
}