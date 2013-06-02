package alex.datastructures;

import alex.datastructures.list.ListNode;

public class LoopInList {

	public static ListNode meetingNode(ListNode head) {
		if (head == null)
			return null;
		ListNode slow = head.next;
		if (slow == null)
			return null;
		ListNode fast = slow.next;
		if (fast == null)
			return null;
		while (slow != null && fast != null) {
			if (slow == fast)
				return slow;
			slow = slow.next;
			fast = fast.next;
			if (fast != null) {
				fast = fast.next;
			}

		}
		return null;
	}

	public static ListNode entryNodeOfLoop(ListNode head) {
		if (head == null)
			return null;
		ListNode meetingNode = meetingNode(head);
		if (meetingNode == null)
			return null;
		int loopSize = 1;
		ListNode loop = meetingNode;
		while (loop.next != meetingNode) {
			loop = loop.next;
			loopSize++;
		}
		ListNode node1 = head;
		for (int i = 0; i < loopSize; i++) {
			node1 = node1.next;
		}
		ListNode node2 = head;
		while (node1 != node2) {
			node1 = node1.next;
			node2 = node2.next;
		}
		return node1;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode();
		head.data = 1;
		ListNode prev = head;
		ListNode entryOfLoop = null;
		for(int i=2;i<10;i++){
			ListNode node = new ListNode(i, null);
			prev.next = node;
			prev = node;
			if(i==5){
				entryOfLoop = node;
			}
		}
		prev.next = entryOfLoop;
		ListNode entryNodeOfLoop = entryNodeOfLoop(head);
		System.out.printf("%d = %d \n", entryNodeOfLoop.data, entryNodeOfLoop.data);
	}

}
