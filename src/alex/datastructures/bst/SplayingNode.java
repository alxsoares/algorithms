package alex.datastructures.bst;

public class SplayingNode<T> {
	private T element;
	private SplayingNode<T> left;
	private SplayingNode<T> right;
	private SplayingNode<T> parent;

	public SplayingNode() {

	}

	public SplayingNode(T el, SplayingNode<T> left, SplayingNode<T> right, SplayingNode<T> parent) {
		this.element = el;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public SplayingNode<T> getLeft() {
		return left;
	}

	public void setLeft(SplayingNode<T> left) {
		this.left = left;
	}

	public SplayingNode<T> getRight() {
		return right;
	}

	public void setRight(SplayingNode<T> right) {
		this.right = right;
	}

	public SplayingNode<T> getParent() {
		return parent;
	}

	public void setParent(SplayingNode<T> parent) {
		this.parent = parent;
	}
	
	

}
