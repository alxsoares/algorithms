package alex.datastructures;

public class ThreeStacks {
	private final static int stackSize = 100;
	private int[] stackPointer = { -1, -1, -1 };
	private StackNode[] buffer = new StackNode[3 * stackSize];
	private int indexUsed = 0;

	public void push(int stackNum, int value) {
		int lastIndex = stackPointer[stackNum];
		StackNode newVal = new StackNode(lastIndex, value);
		stackPointer[stackNum] = indexUsed;
		buffer[stackPointer[stackNum]] = newVal;
		indexUsed++;
	}

	public int pop(int stackNum) {
		int lastUsed = stackPointer[stackNum];
		StackNode node = buffer[stackPointer[stackNum]];
		stackPointer[stackNum] = node.previous;
		buffer[lastUsed] = null;
		indexUsed--;
		return node.value;
	}

	public int peek(int stackNum) {
		StackNode node = buffer[stackPointer[stackNum]];
		return node.value;
	}

	public boolean isEmpty(int stackNum) {
		if (stackPointer[stackNum] == -1)
			return true;
		return false;
	}

	class StackNode {
		public int previous;
		public int value;

		public StackNode(int p, int v) {
			value = v;
			previous = p;
		}

	}
}
