package alex.datastructures.bst;

public class SplayTree<T extends Comparable<T>> {
	private SplayingNode<T> root;

	private void continueRotation(SplayingNode<T> gr, SplayingNode<T> par,
			SplayingNode<T> ch, SplayingNode<T> desc) {
		if (gr != null) {
			if (gr.getRight() == ch.getParent()) {
				gr.setRight(ch);
			} else {
				gr.setLeft(ch);
			}
		} else
			this.root = ch;
		if (desc != null) {
			desc.setParent(par);
		}
		par.setParent(ch);
		ch.setParent(gr);
	}

	private void rotateR(SplayingNode<T> p) {
		p.getParent().setLeft(p.getRight());
		p.setRight(p.getParent());
		continueRotation(p.getParent().getParent(), p.getRight(), p, p
				.getRight().getLeft());
	}

	private void rotateL(SplayingNode<T> p) {
		p.getParent().setRight(p.getLeft());
		p.setLeft(p.getParent());
		continueRotation(p.getParent().getParent(), p.getLeft(), p, p.getLeft()
				.getRight());
	}

	private void semiSplay(SplayingNode<T> p) {
		while (p != root) {
			if (p.getParent().getParent() == null) {
				if (p.getParent().getLeft() == p) {
					rotateR(p);
				} else {
					rotateL(p);
				}
			} else if (p.getParent().getLeft() == p) {
				if (p.getParent().getParent().getLeft() == p.getParent()) {
					rotateR(p.getParent());
					p = p.getParent();
				} else {
					rotateR(p);
					rotateL(p);
				}
			} else if (p.getParent().getParent().getRight() == p.getParent()) {
				rotateL(p.getParent());
				p = p.getParent();
			} else {
				rotateL(p);
				rotateR(p);
			}
			if (root == null) {
				root = p;
			}
		}
	}

	public void insert(T el) {
		SplayingNode<T> p = root;
		SplayingNode<T> prev = null;
		while (p != null) {
			prev = p;
			if (el.compareTo(p.getElement()) < 0) {
				p = p.getLeft();
			} else {
				p = p.getRight();
			}
		}
		SplayingNode<T> newNode = new SplayingNode<T>(el, null, null, prev);
		if (root == null) {
			root = newNode;
		} else if (el.compareTo(prev.getElement()) < 0) {
			prev.setLeft(newNode);
		} else {
			prev.setRight(newNode);
		}
	}

	public void inOrder(SplayingNode<T> p) {
		if (p != null) {
			inOrder(p.getLeft());
			visit(p);
			inOrder(p.getRight());
		}
	}

	public T seach(T el) {
		SplayingNode<T> p = root;
		while (p != null) {
			if (p.getElement().compareTo(el) == 0) {
				semiSplay(p);
				return p.getElement();
			} else if (el.compareTo(p.getElement()) < 0) {
				p = p.getLeft();
			} else {
				p = p.getRight();
			}
		}
		return null;
	}

	private void visit(SplayingNode<T> p) {
		// doSomething

	}

	public static void main(String[] args) {

	}

}
