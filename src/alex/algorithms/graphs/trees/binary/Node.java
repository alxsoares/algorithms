package alex.algorithms.graphs.trees.binary;

import java.util.LinkedList;

public class Node<T extends Comparable<T>> {
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;
    private T value;
    private LinkedList<Node<T>> adj = new LinkedList<>();
    int baconNumber = -1;

    public Node(final T v) {
        this.value = v;
        this.left = null;
        this.right = null;
    }

    public Node<T> rightRotate() {
        Node<T> root = this.left;
        this.left = root.right;
        root.right = this;
        return root;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(final Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(final Node<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    public int getBaconNumber() {
        return baconNumber;
    }

    public void setBaconNumber(final int baconNumber) {
        this.baconNumber = baconNumber;
    }

    public LinkedList<Node<T>> getAdj() {
        return adj;
    }

    public void setAdj(final LinkedList<Node<T>> adj) {
        this.adj = adj;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(final Node<T> parent) {
        this.parent = parent;
    }

}
