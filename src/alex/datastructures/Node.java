package alex.datastructures;

class Node {
	Node next = null;
	int data;

	public Node(int d) {
		data = d;
	}

	void appendToTail(int d) {
		Node end = new Node(d);
		Node n = this;
		while (n.next != null) {
			n = n.next;
		}
		n.next = end;
	}

	public static Node delete(Node head, int val) {
		if (head == null)
			return null;
		if (head.data == val)
			return head.next;
		Node n = head;
		while (n.next != null) {
			if (n.next.data == val) {
				n.next = n.next.next;
				return head;
			}
			n = n.next;
		}
		return head;
	}

	public static Node findBeginingLoop(final Node head) {
		Node n1 = head;
		Node n2 = head;
		while (n2 != null && n2.next != null) {
			n2 = n2.next.next;
			n1 = n1.next;
			if (n1 == n2) {
				break;
			}
		}
		if (n2 == null || n2.next == null)
			return null;
		n1 = head;
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}
}
