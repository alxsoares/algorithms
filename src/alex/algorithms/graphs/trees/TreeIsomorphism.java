package alex.algorithms.graphs.trees;

import alex.datastructures.TreeNode;

public class TreeIsomorphism {

	boolean isIsomorphic(TreeNode n1, TreeNode n2) {
		if (n1 == null && n2 == null)
			return true;

		if (n1 == null || n2 == null)
			return false;

		if (n1.value != n2.value)
			return false;

		return (isIsomorphic(n1.left, n2.left) && isIsomorphic(n1.right,
				n2.right))
				|| (isIsomorphic(n1.left, n2.right) && isIsomorphic(n1.right,
						n2.left));
	}

}
