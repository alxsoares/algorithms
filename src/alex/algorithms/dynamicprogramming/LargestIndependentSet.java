package alex.algorithms.dynamicprogramming;

import alex.datastructures.TreeNode;

public class LargestIndependentSet {

	public static int LISS(TreeNode root) {
		if (root == null)
			return 0;
		if (root.liss > 0)
			return root.liss;
		if (root.left == null && root.right == null) {
			return root.liss = 1;
		}
		int lissChildren = LISS(root.left) + LISS(root.right);

		int lissGrandChildren = 1;
		if (root.left != null) {
			lissGrandChildren += LISS(root.left.right) + LISS(root.left.left);
		}
		if (root.right != null) {
			lissGrandChildren += LISS(root.right.left) + LISS(root.right.right);
		}
		root.liss = Math.max(lissChildren, lissGrandChildren);
		return root.liss;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(20);
		root.left = new TreeNode(8);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(12);
		root.left.right.left = new TreeNode(10);
		root.left.right.right = new TreeNode(14);
		root.right = new TreeNode(22);
		root.right.right = new TreeNode(25);

		System.out.printf("%d \n", LISS(root));

	}

}
