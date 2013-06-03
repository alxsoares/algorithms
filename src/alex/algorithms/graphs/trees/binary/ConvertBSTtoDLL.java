package alex.algorithms.graphs.trees.binary;

import java.util.Random;
import java.util.Stack;

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

	public static Node<Integer> bstToDLL(Node<Integer> root) {
		Stack<Node<Integer>> stack = new Stack<>();
		Node<Integer> head = null;
		Node<Integer> left = root;
		while (left != null) {
			stack.push(left);
			left = left.getLeft();
		}
		Node<Integer> prev = null;
		while (!stack.isEmpty()) {
			Node<Integer> current = stack.pop();
			if(head==null) head = current;
			Node<Integer> right = current.getRight();
			while (right != null) {
				stack.push(right);
				right = right.getLeft();
			}
			current.setLeft(prev);
			if (prev != null) {
				prev.setRight(current);
			}
			prev = current;
		}
		if(prev!= null){
			prev.setRight(null);
		}
		return head;
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
		Random rand = new Random();
		Node<Integer> root = null;
		for (int i = 0; i < 20; i++) {
			root = TreeAlgorithms.insertNodeBST(root,
					Math.abs(rand.nextInt()) % 200);
		}
//		Node<Integer> list = convert(root);
//		while (list != null) {
//			System.out.printf("%d ", list.getValue());
//			list = list.getRight();
//		}
		System.out.println();
		Node<Integer> head = bstToDLL(root);
		while (head != null) {
			System.out.printf("%d ", head.getValue());
			head = head.getRight();
		}
	}

}
