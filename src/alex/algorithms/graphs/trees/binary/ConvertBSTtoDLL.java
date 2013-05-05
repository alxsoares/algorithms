package alex.algorithms.graphs.trees.binary;

public class ConvertBSTtoDLL {
	public static Node<Integer> convert(Node<Integer> root) {
		Node<Integer> lastNode = convertNode(root, null);
		Node<Integer> head = lastNode;
		while (head != null && head.getLeft() != null) {
			head = head.getLeft();
		}
		return head;
	}

	private static Node<Integer> convertNode(Node<Integer> root,
			Node<Integer> lastNode) {
		if (root == null)
			return null;
		Node<Integer> current = root;
		if (current.getLeft() != null) {
			lastNode = convertNode(root.getLeft(), lastNode);
		}
		current.setLeft(lastNode);
		if (lastNode != null) {
			lastNode.setRight(current);
		}
		lastNode = current;
		if (current.getRight() != null) {
			lastNode = convertNode(root.getRight(), lastNode);
		}
		return lastNode;
	}

	public static Node<Integer> rotateToDLL(Node<Integer> root) {
		Node<Integer> node = root;
		Node<Integer> head = null;
		Node<Integer> parent = null;
		while (node != null) {
			// right rotation
			while (node.getLeft() != null) {
				Node<Integer> left = node.getLeft();
				node.setLeft(left.getRight());
				left.setRight(node);
				node = left;
				if (parent != null) {
					parent.setRight(node);
				}
			}
			if (head == null) {
				head = node;
			}
			parent = node;
			node = node.getRight();
		}
		Node<Integer> n1 = head;
		if (n1 != null) {
			Node<Integer> n2 = n1.getRight();
			while (n2 != null) {
				n2.setLeft(n1);
				n1 = n2;
				n2 = n2.getRight();
			}
		}
		return head;
	}

	public static void main(String[] args) {

	}

}
