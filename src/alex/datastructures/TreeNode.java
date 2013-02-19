package alex.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;

public class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;

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
}
