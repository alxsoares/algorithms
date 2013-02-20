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
}
