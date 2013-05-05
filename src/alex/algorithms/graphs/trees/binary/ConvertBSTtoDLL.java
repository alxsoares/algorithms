package alex.algorithms.graphs.trees.binary;

public class ConvertBSTtoDLL {
	public static Node<Integer> convert(Node<Integer> root){
		Node<Integer> lastNode = convertNode(root, null);
		Node<Integer> head = lastNode;
		while(head!= null && head.getLeft()!= null){
			head = head.getLeft();
		}
		return head;
	}
	
	private static Node<Integer> convertNode(Node<Integer> root, Node<Integer> lastNode) {
		if(root==null) return null;
		Node<Integer> current = root;
		if(current.getLeft()!= null){
			lastNode = convertNode(root.getLeft(), lastNode);
		}
		current.setLeft(lastNode);
		if(lastNode!= null){
			lastNode.setRight(current);
		}
		lastNode = current;
		if(current.getRight()!= null){
			lastNode = convertNode(root.getRight(), lastNode);
		}
		return lastNode;
	}

	public static void main(String[] args) {

	}

}
