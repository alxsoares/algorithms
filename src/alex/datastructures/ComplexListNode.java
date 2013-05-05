package alex.datastructures;

public class ComplexListNode {
	private Integer value;
	private ComplexListNode next;
	private ComplexListNode sibling;

	public static ComplexListNode clone(ComplexListNode head) {
		cloneNodes(head);
		connectSiblings(head);
		return reconnect(head);
	}

	private static ComplexListNode reconnect(ComplexListNode head) {
		ComplexListNode node = head;
		ComplexListNode clonedHead = null;
		ComplexListNode clonedNode = null;
		if (node != null) {
			clonedNode = clonedHead = node.next;
			node.next = clonedNode.next;
			node = node.next;
		}
		while (node != null) {
			clonedNode.next = node.next;
			clonedNode = clonedNode.next;
			node.next = clonedNode.next;
			node = node.next;
		}
		return clonedHead;
	}

	private static void connectSiblings(ComplexListNode head) {
		ComplexListNode node = head;
		while (node != null) {
			ComplexListNode cloned = node.next;
			if (node.sibling != null) {
				cloned.sibling = node.sibling.next;
			}
			node = cloned.next;
		}
	}

	private static void cloneNodes(ComplexListNode head) {
		ComplexListNode node = head;
		while (node != null) {
			ComplexListNode cloned = new ComplexListNode();
			cloned.value = node.value;
			cloned.next = node.next;
			cloned.sibling = null;
			node.next = cloned;
			node = cloned.next;
		}
	}

	public static void main(String[] args) {

	}

}
