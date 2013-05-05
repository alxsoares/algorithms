package alex.datastructures.list;

import java.util.Stack;

public class LinkedListUtils {

	public static void printIter(ListNode l) {
		Stack<Integer> stack = new Stack<Integer>();
		ListNode list = l;
		while (list != null) {
			stack.push(list.data);
			list = list.next;
		}
		while (!stack.isEmpty()) {
			System.out.printf("%d\t", stack.pop());
		}
		System.out.println();
	}

	public static ListNode sort(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode lastSorted = head;
		ListNode toBeSorted = lastSorted.next;
		while (toBeSorted != null) {
			if (toBeSorted.data < head.data) {
				lastSorted.next = toBeSorted.next;
				toBeSorted.next = head;
				head = toBeSorted;
			} else {
				ListNode node = head;
				while (node != lastSorted && node.next.data < toBeSorted.data) {
					node = node.next;
				}
				if (node != lastSorted) {
					lastSorted.next = toBeSorted.next;
					toBeSorted.next = node.next;
					node.next = toBeSorted;
				} else {
					lastSorted = lastSorted.next;
				}
			}
			toBeSorted = lastSorted.next;
		}
		return head;
	}

	public static ListNode mergeSortedLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode merged;
		if (l1.data < l2.data) {
			merged = l1;
			merged.next = mergeSortedLists(l1.next, l2);
		} else {
			merged = l2;
			merged.next = mergeSortedLists(l1, l2.next);
		}
		return merged;
	}

	public static ListNode meetingNode(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode slow = head.next;
		if (slow == null)
			return null;
		ListNode fast = slow.next;
		while (fast != null && slow != null) {
			if (fast == slow)
				return fast;
			slow = slow.next;
			fast = fast.next;
			if (fast != null) {
				fast = fast.next;
			}
		}
		return null;
	}

	public static ListNode entryOfLoop(ListNode head) {
		ListNode meetingNode = meetingNode(head);
		if (meetingNode == null)
			return null;
		int nodesInloop = 1;
		ListNode node = meetingNode;
		while (node.next != meetingNode) {
			node = node.next;
			nodesInloop++;
		}
		node = head;
		for (int i = 0; i < nodesInloop; i++) {
			node = node.next;
		}
		ListNode node2 = head;
		while (node2 != node) {
			node2 = node2.next;
			node = node.next;
		}
		return node;
	}

	public static ListNode removeDuplicates(ListNode head) {
		if (head == null)
			return null;
		ListNode preNode = null;
		ListNode node = head;
		while (node != null) {
			ListNode next = node.next;
			boolean delete = false;
			if (next != null && next.data == node.data) {
				delete = true;
			}
			if (!delete) {
				preNode = node;
				node = next;
			} else {
				int value = node.data;
				ListNode toBeDeleted = node;
				while (toBeDeleted != null && toBeDeleted.data == value) {
					next = toBeDeleted.next;
					toBeDeleted = next;
				}
			}
			if (preNode == null) {
				head = next;
			} else {
				preNode.next = next;
			}
			node = next;
		}
		return head;
	}

	public static ListNode findKthToTail(ListNode head, int k) {
		if (head == null || k == 0)
			return null;
		ListNode ahead = head;
		for (int i = 0; i < k - 1; i++) {
			if (ahead.next != null) {
				ahead = ahead.next;
			} else {
				return null;
			}
		}
		ListNode kth = head;
		while (ahead.next != null) {
			kth = kth.next;
			ahead = ahead.next;
		}
		return kth;
	}

	public static void main(String[] args) {

	}

}
