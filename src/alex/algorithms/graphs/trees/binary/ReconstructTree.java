package alex.algorithms.graphs.trees.binary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ReconstructTree {

	public static Node<Integer> reconstruct(int[] inorder, int[] preorder) {
		if (inorder == null || preorder == null)
			return null;
		if (inorder.length != preorder.length)
			throw new RuntimeException("jdksjd");
		if (inorder == null || inorder.length == 0)
			return null;
		if (inorder.length == 1)
			return new Node<Integer>(inorder[0]);

		Node<Integer> root = new Node<Integer>(preorder[0]);

		int i = 0;
		for (; i < inorder.length; i++) {
			if (inorder[i] == preorder[0]) {
				break;
			}
		}

		int[] inorderLeft = new int[i];
		int[] preorderLeft = new int[i];
		System.arraycopy(inorder, 0, inorderLeft, 0, i);
		System.arraycopy(preorder, 1, preorderLeft, 0, i);
		root.setLeft(reconstruct(inorderLeft, preorderLeft));
		int[] inorderRight = new int[inorder.length - i - 1];
		int[] preorderRight = new int[inorder.length - i - 1];
		System.arraycopy(inorder, i + 1, inorderRight, 0, inorderRight.length);
		System.arraycopy(preorder, i + 1, preorderRight, 0,
				preorderRight.length);
		root.setRight(reconstruct(inorderRight, preorderRight));
		return root;
	}
	
	public static Node<Integer> reconstruct(Integer[] inorder, Integer[] preorder) {
		if (inorder == null || preorder == null)
			return null;
		if (inorder.length != preorder.length)
			throw new RuntimeException("jdksjd");
		if (inorder == null || inorder.length == 0)
			return null;
		if (inorder.length == 1)
			return new Node<Integer>(inorder[0]);

		Node<Integer> root = new Node<Integer>(preorder[0]);

		int i = 0;
		for (; i < inorder.length; i++) {
			if (inorder[i] == preorder[0]) {
				break;
			}
		}

		Integer[] inorderLeft = new Integer[i];
		Integer[] preorderLeft = new Integer[i];
		System.arraycopy(inorder, 0, inorderLeft, 0, i);
		System.arraycopy(preorder, 1, preorderLeft, 0, i);
		root.setLeft(reconstruct(inorderLeft, preorderLeft));
		Integer[] inorderRight = new Integer[inorder.length - i - 1];
		Integer[] preorderRight = new Integer[inorder.length - i - 1];
		System.arraycopy(inorder, i + 1, inorderRight, 0, inorderRight.length);
		System.arraycopy(preorder, i + 1, preorderRight, 0,
				preorderRight.length);
		root.setRight(reconstruct(inorderRight, preorderRight));
		return root;
	}

	public static void byLevels(Node<Integer> root) {
		ArrayList<LinkedList<Node<Integer>>> r = new ArrayList<>();
		LinkedList<Node<Integer>> l = new LinkedList<>();
		l.add(0, root);
		r.add(0, l);
		int level = 0;
		while (true) {
			LinkedList<Node<Integer>> list = new LinkedList<>();
			for (int i = 0; i < r.get(level).size(); i++) {
				Node<Integer> curr = r.get(level).get(i);
				if (curr.getLeft() != null) {
					list.add(curr.getLeft());
				}
				if (curr.getRight() != null) {
					list.add(curr.getRight());
				}
			}
			if (list.isEmpty()) {
				break;
			} else {
				r.add(++level, list);
			}
		}
		for (int i = 0; i < r.size(); i++) {
			LinkedList<Node<Integer>> ll = r.get(i);
			for (Iterator<Node<Integer>> iterator = ll.iterator(); iterator
					.hasNext();) {
				Node<Integer> node = iterator.next();
				System.out.printf("%d ", node.getValue());
			}
			System.out.println();
		}
	}

	public static void inOrder(Node<Integer> root, ArrayList<Integer> arr) {
		if (root == null)
			return;
		inOrder(root.getLeft(), arr);
		arr.add(root.getValue());
		inOrder(root.getRight(), arr);
	}
	public static void preOrder(Node<Integer> root, ArrayList<Integer> arr){
		if(root== null) return;
		arr.add(root.getValue());
		preOrder(root.getLeft(), arr);
		preOrder(root.getRight(), arr);
	}

	public static void main(String[] args) {
//		int[] preorder = { 7, 10, 4, 3, 1, 2, 8, 11 };
//		int[] inorder = { 4, 10, 3, 1, 7, 11, 8, 2 };
//		Node<Integer> root = reconstruct(inorder, preorder);
//		byLevels(root);
		Node<Integer> r = TreeAlgorithms.createRandomTree(3);
		byLevels(r);
		ArrayList<Integer> inOrder = new ArrayList<>();
		ArrayList<Integer> preOrder = new ArrayList<>();
		inOrder(r,inOrder);
		preOrder(r,preOrder);
		Integer i[]=	inOrder.toArray(new Integer[0]);
		Integer p[] = preOrder.toArray(new Integer[0]);
		Node<Integer> reconstruct = reconstruct(i, p);
		byLevels(reconstruct);
	}

}
