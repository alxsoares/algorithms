package alex.datastructures;

import java.util.Stack;

public class Tower {

	private Stack<Integer> disks;
	private int index;

	public Tower(int i) {
		this.index = i;
		this.disks = new Stack<Integer>();
	}

	public int index() {
		return index;
	}

	public void add(int d) {
		if (!disks.isEmpty() && disks.peek() <= d) {
			System.out.printf("Error placing disk %d\n", d);
		} else {
			disks.push(d);
		}
	}

	public void moveToTop(Tower t) {
		int top = disks.pop();
		t.add(top);
		System.out.printf("Moving disk % d from %d to %d\n ", top, index(),
				t.index());
	}

	public void print() {
		System.out.println("Contents of Tower " + index());
		for (int i = disks.size() - 1; i >= 0; i--) {
			System.out.println("" + disks.get(i));
		}
	}

	public void moveDisks(int n, Tower destination, Tower buffer) {
		if (n > 0) {
			moveDisks(n - 1, buffer, destination);
			moveToTop(destination);
			buffer.moveDisks(n - 1, destination, this);
		}
	}

	public static void main(String[] args) {
		int n = 5;
		Tower[] towers = new Tower[n];
		for (int i = 0; i < 3; i++) towers[i] = new Tower(i);
		for (int i = n - 1; i >= 0; i--) towers[0].add(i);
		towers[0].moveDisks(n, towers[2], towers[1]);

	}

}
