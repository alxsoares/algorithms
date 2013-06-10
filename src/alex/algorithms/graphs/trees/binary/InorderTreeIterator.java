package alex.algorithms.graphs.trees.binary;

import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

public class InorderTreeIterator implements Iterator<Node<Integer>> {
	Stack<Node<Integer>> s = new Stack<>();
	final Node<Integer> root;

	public InorderTreeIterator(Node<Integer> root) {
		this.root = root;
		buildNext();
	}

	private void buildNext() {
		Node<Integer> n = root;
		while (n != null) {
			s.add(n);
			n = n.getLeft();
		}
	}

	@Override
	public boolean hasNext() {
		return !s.isEmpty();
	}

	@Override
	public Node<Integer> next() {
		Node<Integer> next = s.pop();
		Node<Integer> right = next.getRight();
		while (right != null) {
			s.add(right);
			right = right.getLeft();
		}
		return next;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		Random rand = new Random(System.nanoTime());
		Node<Integer> root = null;
		for (int i = 0; i < 20; i++) {
			root = TreeAlgorithms.insertNodeBST(root, Math.abs(rand.nextInt()) % 200);
		}
		InorderTreeIterator iter = new InorderTreeIterator(root);
		while(iter.hasNext()){
			Node<Integer> n = iter.next();
			System.out.printf("%d ", n.getValue());
		}
	}

}
