package alex.algorithms.graphs.trees.binary;

import java.util.LinkedList;

public class Node<T extends Comparable<T>> {
	private Node<T> left;
	private Node<T> right;
	private T value;
	private LinkedList<Node<T>> adj = new LinkedList<>();
	int baconNumber =-1;

	public Node(T v) {
		this.value = v;
		this.left=null;
		this.right = null;
	}
	
	public Node<T> rightRotate(){
		Node<T> root = this.left;
		this.left = root.right;
		root.right = this;
		return root;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public int getBaconNumber() {
		return baconNumber;
	}

	public void setBaconNumber(int baconNumber) {
		this.baconNumber = baconNumber;
	}

	public LinkedList<Node<T>> getAdj() {
		return adj;
	}

	public void setAdj(LinkedList<Node<T>> adj) {
		this.adj = adj;
	}

}
