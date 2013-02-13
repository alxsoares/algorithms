package topcoder.alex.misc;

public class NodeDistance {

	class Node {
		public int val;
		public Node l, r;
	}

	public static int distance(Node root, int v1, int v2) {
		if (v1 == v2)
			return 0;
		while (	///
				(root.val < v1 && root.val < v2)
				||// 
				(root.val > v1 && root.val > v2)) {
			
			if (root.val < v1 && root.val < v2) {
				root = root.r;
			} else {
				root = root.l;
			}
			return depth(root, v1) + depth(root, v2);
		}

		return 0;
	}

	private static int depth(Node root, int v) {
		int d=0;
		while(root.val!= v){
			if(root.val > v) root = root.r;
			else root = root.l;
			d++;
		}
		return d;
	}

	public static void main(String[] args) {

	}

}
