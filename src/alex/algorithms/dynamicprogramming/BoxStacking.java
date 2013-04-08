package alex.algorithms.dynamicprogramming;

import java.util.Arrays;

public class BoxStacking {
	/**
	 * You are given a set of n types of rectangular 3-D boxes, where the i^th
	 * box has height h(i), width w(i) and depth d(i) (all real numbers). You
	 * want to create a stack of boxes which is as tall as possible, but you can
	 * only stack a box on top of another box if the dimensions of the 2-D base
	 * of the lower box are each strictly larger than those of the 2-D base of
	 * the higher box. Of course, you can rotate a box so that any side
	 * functions as its base. It is also allowable to use multiple instances of
	 * the same type of box.
	 */
	public static int maxBoxStack(Box[] array) {
		Box[] boxes = new Box[array.length * 3];
		int index = 0;

		for (int i = 0; i < array.length; i++) {
			// Copy the original box
			boxes[index++] = array[i];

			// First rotation of box
			boxes[index++] = new Box(array[i].width, Math.min(array[i].height,
					array[i].depth), Math.max(array[i].height, array[i].depth));
			// Second rotation of box
			boxes[index++] = new Box(array[i].depth, Math.min(array[i].height,
					array[i].width), Math.max(array[i].height, array[i].width));
		}
		Arrays.sort(boxes);
		int maxHeight = Integer.MIN_VALUE;
		int[] maxStack = new int[boxes.length];
		maxStack[0] = boxes[0].height;
		for (int i = 1; i < boxes.length; i++) {
			maxStack[i] = boxes[i].height;
			for (int j = 0; j < i; j++) {
				if (boxes[j].depth > boxes[i].depth
						&& boxes[j].width > boxes[i].width
						&& maxStack[i] < maxStack[j] + boxes[i].height) {
					maxStack[i] = maxStack[j] + boxes[i].height;
				}
			}
			if (maxStack[i] > maxHeight) {
				maxHeight = maxStack[i];
			}
		}
		return maxHeight;
	}

	public static void main(String[] args) {
		Box array[] = { new Box(4, 6, 7), new Box(1, 2, 3), new Box(4, 5, 6),
				new Box(10, 12, 32) };
		System.out.println(maxBoxStack(array));
	}

}

class Box implements Comparable<Box> {
	int height, width, depth;

	Box(int height, int width, int depth) {
		this.height = height;
		this.width = width;
		this.depth = depth;
	}

	@Override
	public int compareTo(Box o) {
		return (o.depth * o.width) - (this.depth * this.width);
	}
}