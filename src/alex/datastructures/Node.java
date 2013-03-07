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

	public Integer nthToEnd(Node node, int n) {
		int begin = 0;
		Node b = node;
		while (b != null && begin < n) {
			begin++;
			b = b.next;
		}
		while (b != null) {
			node = node.next;
			b = b.next;
		}
		if (node != null) {
			return node.data;
		}
		return null;
	}

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Node head = new Node(a[0]);
		Node n = head;
		for (int i = 1; i < a.length; i++) {
			n.next = new Node(a[i]);
			n = n.next;
		}
		System.out.printf("%d \n", head.nthToEnd(head, 4));
	}
}
