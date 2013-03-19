package alex.algorithms.graphs.trees.binary;

public class MorrisTraversal {

	public static void inOrder(Node<Integer> root) {
		Node<Integer> current, pre;
		if (root == null)
			return;
		current = root;
		while (current != null) {
			if (current.getLeft() == null) {
				System.out.printf(" %d ", current.getValue());
				current = current.getRight();
			} else {
				pre = current.getLeft();
				while (pre.getRight() != null && pre.getRight() != current)
					pre = pre.getRight();
				if (pre.getRight() == null) {
					pre.setRight(current);
					current = current.getLeft();
				} else {
					pre.setRight(null);
					System.out.printf(" %d ", current.getValue());
					current = current.getRight();
				}
			}
		}
	}

	public static void main(String[] args) {

	}

}
