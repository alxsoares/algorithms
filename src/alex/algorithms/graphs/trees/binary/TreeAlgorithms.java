package alex.algorithms.graphs.trees.binary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class TreeAlgorithms {
	static final Random rand = new Random(System.currentTimeMillis());

	public static <T extends Comparable<T>> int height(Node<T> root) {
		if (root == null)
			return 0;
		return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
	}

	public static Node<Integer> createRandomTree(int height) {
		if (height == 0)
			return null;
		Node<Integer> root = new Node<>(rand.nextInt());
		root.setLeft(createRandomTree(height - 1));
		root.setRight(createRandomTree(height - 1));
		return root;
	}

	public static Node<Integer> insertNodeBST(Node<Integer> root, int value) {
		if (root == null) {
			Node<Integer> node = new Node<Integer>(value);
			return node;
		}
		if (value > root.getValue()) {
			root.setRight(insertNodeBST(root.getRight(), value));
		} else if (value < root.getValue()) {
			root.setLeft(insertNodeBST(root.getLeft(), value));
		}
		return root;
	}

	public static void printInOrder(Node<Integer> root) {
		if (root == null)
			return;
		printInOrder(root.getLeft());
		System.out.printf("%d ", root.getValue());
		printInOrder(root.getRight());
	}

	public static void printPreOrder(Node<Integer> root) {
		if (root == null)
			return;
		System.out.printf("%d ", root.getValue());
		printPreOrder(root.getLeft());
		printPreOrder(root.getRight());
	}

	public static void printPreOrderIterative(Node<Integer> root) {
		Stack<Node<Integer>> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node<Integer> node = stack.pop();
			System.out.printf("%d ", node.getValue());
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
		}
	}

	public static void lowestCommonAntecessor(Node<Integer> root, int i, int j) {
		int max = Math.max(i, j);
		int min = Math.min(i, j);
		while (root != null) {
			if (root.getValue() > min && root.getValue() > max) {
				root = root.getLeft();
				continue;
			}
			if (root.getValue() < min && root.getValue() < max) {
				root = root.getRight();
				continue;
			}
			if (root.getValue() <= max && root.getValue() >= min) {
				break;
			}
		}
		if (root != null) {
			System.out.printf("Common Ancestor de %d e %d = %d", min, max,
					root.getValue());
		}
	}

	public static Node<Integer> rightRotation(Node<Integer> oldRoot) {
		Node<Integer> root = oldRoot.getLeft();
		oldRoot.setLeft(root.getRight());
		root.setRight(oldRoot);
		return root;
	}

	public static void BFS(Node<Integer> root) {
		Queue<Node<Integer>> queue = new LinkedList<>();
		queue.add(root);
		root.setBaconNumber(0);
		while (!queue.isEmpty()) {
			Node<Integer> current = queue.poll();
			for (Iterator<Node<Integer>> iterator = current.getAdj().iterator(); iterator
					.hasNext();) {
				Node<Integer> c = iterator.next();
				if (c.getBaconNumber() != -1) {
					c.setBaconNumber(current.getBaconNumber() + 1);
					queue.add(c);
				}
			}
		}
	}

	public static Node<Integer> bstToDoubleLinky(Node<Integer> root) {
		if (root == null)
			return null;
		Node<Integer> head = bstToDoubleLinky(root.getLeft());
		Node<Integer> tail = bstToDoubleLinky(root.getRight());
		if(tail!= null){
			while(tail.getLeft()!= null){
				tail = tail.getLeft();
			}
		}
		if(head!= null){
			while(head.getRight()!= null){
				head= head.getRight();
			}
		}
		if(head!=null){
			head.setRight(root);
			root.setLeft(head);
			root.setRight(tail);
			if(tail!= null){
				tail.setLeft(root);
			}			
		}else{
			root.setLeft(null);
			root.setRight(tail);
			if(tail!= null){
				tail.setLeft(root);
			}
			head = root;
		}
		while(head.getLeft()!= null){
			head = head.getLeft();
		}
		return head;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int height = Math.abs(rand.nextInt()) % 20;
		if (height < 10)
			height += 10;
		Node<Integer> root = createRandomTree(height);
		System.out.printf("Height of tree=%d = %d\n", height(root), height);
		root = null;
		for (int i = 0; i < 200; i++) {
			root = insertNodeBST(root, Math.abs(rand.nextInt()) % 200);
		}
		int i = Math.abs(rand.nextInt()) % 200;
		int j = Math.abs(rand.nextInt()) % 200;
		insertNodeBST(root, i);
		insertNodeBST(root, j);
		System.out.printf("Height of tree=%d = %d\n", height(root), height);
		printPreOrder(root);
		System.out.println();
		printPreOrderIterative(root);
		System.out.println();
		printInOrder(root);
		System.out.println();
		lowestCommonAntecessor(root, i, j);
		System.out.println();
		Node<Integer> head = bstToDoubleLinky(root);
		while(head!= null){
			System.out.printf("%d ", head.getValue());
			head = head.getRight();
		}
	}

}
