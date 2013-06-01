package alex.algorithms.graphs.trees.binary;

import java.util.Random;
import java.util.Stack;

public class NodesWithSumInBST {
	public static void printSum(Node<Integer> root, int sum) {
		Stack<Node<Integer>> prev = new Stack<>();
		Stack<Node<Integer>> next = new Stack<>();
		buildNext(root, next);
		buildPrev(root, prev);
		Node<Integer> greater = getPrev(prev);
		Node<Integer> smaller = getNext(next);
		while (greater != null && smaller != null && smaller != greater) {
			int currentSum = smaller.getValue() + greater.getValue();
			if (currentSum == sum) {
				System.out.printf("%d %d\n", smaller.getValue(),
						greater.getValue());
				return;
			}
			if(currentSum < sum){
				smaller = getNext(next);
			}else{
				greater = getPrev(prev);
			}
		}
	}

	public static Node<Integer> getNext(Stack<Node<Integer>> nodes) {
		Node<Integer> next = null;
		if (!nodes.isEmpty()) {
			next = nodes.pop();
			Node<Integer> right = next.getRight();
			while (right != null) {
				nodes.push(right);
				right = right.getLeft();
			}
		}
		return next;
	}

	public static Node<Integer> getPrev(Stack<Node<Integer>> nodes) {
		Node<Integer> prev = null;
		if (!nodes.isEmpty()) {
			prev = nodes.pop();
			Node<Integer> left = prev.getLeft();
			while (left != null) {
				nodes.push(left);
				left = left.getRight();
			}
		}
		return prev;
	}

	public static void buildNext(Node<Integer> root, Stack<Node<Integer>> next) {
		Node<Integer> node = root;
		while (node != null) {
			next.push(node);
			node = node.getLeft();
		}
	}

	public static void buildPrev(Node<Integer> root, Stack<Node<Integer>> next) {
		Node<Integer> node = root;
		while (node != null) {
			next.push(node);
			node = node.getRight();
		}
	}

	public static void main(String[] args) { 
		Random rand = new Random();
		Node<Integer> root = null;
		
		for (int i = 0; i < 20; i++) {
			int value = Math.abs(rand.nextInt()) % 200;
			root = TreeAlgorithms.insertNodeBST(root, value);
		}
		int v1 = Math.abs(rand.nextInt()) % 200;
		int v2 = Math.abs(rand.nextInt()) % 200;
		TreeAlgorithms.insertNodeBST(root, v1);
		TreeAlgorithms.insertNodeBST(root, v2);
		System.out.printf("%d %d\n",v1,v2);
		printSum(root, v1+v2);
		
		
	}

}
