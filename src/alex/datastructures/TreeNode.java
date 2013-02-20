package alex.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;

public class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;

	public ArrayList<LinkedList<TreeNode>> findLevelLinkList(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		list.add(root);
		int level = 0;
		result.add(level, list);
		while (true) {
			list = new LinkedList<TreeNode>();
			for (int i = 0; i < result.get(level).size(); i++) {
				TreeNode node = result.get(level).get(i);
				if (node != null) {
					if (node.left != null)
						list.add(node.left);
					if (node.right != null)
						list.add(node.right);
				}
			}
			if (list.size() > 0) {
				result.add(level++, list);
			} else {
				break;
			}
		}
		return result;
	}

	public static TreeNode inorderSucc(TreeNode e) {
		if (e != null) {

			if (e.parent == null || e.right != null) {
				return leftMostSucc(e.right);
			} else {
				while (e.parent != null) {
					if (e == e.parent.left) {
						return e.parent;
					}
					e = e.parent;
				}
			}
		}
		return null;
	}

	private static TreeNode leftMostSucc(TreeNode e) {
		if (e == null)
			return null;
		while (e.left != null) {
			e = e.left;
		}
		return e;
	}

	public TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (p == q && (root.left == q || root.right == q))
			return root;
		int numLeft = covers(root.left, p, q);
		if (numLeft == 2) {
			if (root.left == q || root.left == p)
				return root.left;
			return commonAncestor(root.left, p, q);
		} else if (numLeft == 1) {
			if (root == q || root == p)
				return root;
		}
		int numRight = covers(root.right, p, q);
		if (numRight == 2) {
			if (root.right == q || root.right == p)
				return root.right;
		} else if (numRight == 1) {
			if (root == q || root == p)
				return root;
		}
		if (numRight == 1 && numLeft == 1)
			return root;
		return null;
	}

	private int covers(TreeNode root, TreeNode p, TreeNode q) {
		int ret = 0;
		if (root == null)
			return ret;
		if (root == p || root == q)
			ret++;
		ret += covers(root.left, p, q);
		ret += covers(root.right, p, q);
		return ret;
	}
}
