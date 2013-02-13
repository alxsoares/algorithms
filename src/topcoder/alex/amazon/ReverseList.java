package topcoder.alex.amazon;

public class ReverseList {
	class Node {
		int val;
		Node next;
	}

	public static Node reverseList(Node head) {
		if (head == null)
			return null;
		Node curr = head;
		Node prev = null, tmp = null, newHead = null;
		while (curr != null) {
			tmp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmp;
		}
		newHead = prev;
		return newHead;
	}

	public static Node mergeLastAndFirst(Node head) {
		if (head == null)
			return null;
		if (head.next == null)
			return head;
		Node curr = null, slow = head, fast = head;
		Node newHead = null, tail = null, tmp = null, prev = null;
		while (fast != null && fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		tail = reverseList(slow.next); // slow will always have next
		// printList(tail);
		slow.next = null;
		newHead = curr = tail;
		while (head != null && tail != null) {
			tmp = tail.next;
			curr.next = head;
			tail = tmp;
			head = head.next;
			prev = curr.next;
			curr.next.next = tail;// ->next;
			curr = curr.next.next;
		}
		if (curr == null)
			curr = prev;
		if (head != null)
			curr.next = head;
		if (tail != null)
			curr.next = tail;
		return newHead;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
