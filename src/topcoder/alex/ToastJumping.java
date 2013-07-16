package topcoder.alex;

/**
 * Problem Statement Toastman lives on a two-dimensional grid. His home has
 * coordinates (0, 0). Toastman wants to visit Toastwoman who lives on the same
 * grid at integer coordinates (x, y). Toastman only moves by jumping, and he
 * can only stand on points with integer coordinates.
 * 
 * 
 * 
 * The length of a jump is the Euclidean distance between its endpoints.
 * Toastman can only make jumps of length less than or equal to sqrt(d). In
 * other words, he can jump from (x1, y1) to (x2, y2) in a single jump if and
 * only if both points are lattice points and (x1-x2)^2 + (y1-y2)^2 <= d.
 * 
 * 
 * 
 * You are given three int[]s x, y, and d. These three int[]s will all have the
 * same number of elements. For each valid index i, the ints x[i], y[i], and
 * d[i] represent a single query:
 * "What is the smallest number of jumps Toastman needs to reach Toastwoman, given that she lives at (x[i], y[i]) and that the squared length of his jumps is limited by d[i]?"
 * 
 * 
 * 
 * Return a int[] with the same number of elements as the inputs. For each i,
 * element i of the return value should be the answer to query i.
 * 
 * 
 */
public class ToastJumping {

	public long area2(long x1, long y1, long x2, long y2, long x3, long y3) {
		// Returns 2 * area of the triangle using cross product.
		// If the result is negative, the points are in anti-clockwise order.
		return (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);
	}

	final int MAX_SQUARE_DISTANCE = 31623;
	int px[], py[];

	int makePolygon(int d) {
		// Returns the convex hull of the points in quadrant that are
		// reachable in one jump.
		int sz = 0;
		px = new int[MAX_SQUARE_DISTANCE + 1];
		py = new int[MAX_SQUARE_DISTANCE + 1];
		int y = MAX_SQUARE_DISTANCE;
		for (int x = 0;; x++) {
			// decrease y until it is inside the circle / is 0:
			while (y > 0 && x * x + y * y > d) {
				y--;
			}
			if (x * x + y * y > d) {
				// x is outside the circle:
				break;
			}
			// Direction of the segments in the polygon should be clockwise:
			// If the new point makes an anti-clockwise direction with the
			// previous
			// point (aread2 > 0), then we need to remove the previous point to
			// keep the polygon convex and so and so for each of the previous
			// points.
			// We can also remove the previous point if they are collinear
			// (area2 == 0), but it is not necessary for the solution.
			while (sz >= 2
					&& area2(px[sz - 2], py[sz - 2], px[sz - 1], py[sz - 1], x,
							y) >= 0) {
				// (If the triangle area is 0, the points are collinear)
				sz--; // remove last point
			}
			// [Basically a simplified version of Graham's scan, we already find
			// the
			// points in clockwise order.]

			// Add the point:
			px[sz] = x;
			py[sz] = y;
			sz++;
		}
		// Make sure to close the polygon...
		if (py[sz - 1] != 0) {
			px[sz] = px[sz - 1];
			py[sz] = 0;
			sz++;
		}

		return sz;
	}

	// Solve for each x[i],y[i],d[i]:
	public int minJumpsSingle(int x, int y, int d) {

		x = Math.abs(x);
		y = Math.abs(y);

		int sz = makePolygon(d);

		int k = 0;
		for (int i = 0; i < sz - 1; i++) {
			int dy = py[i] - py[i + 1]; 
			int dx = px[i + 1] - px[i];
			int p = x * dy + y * dx;
			int q = px[i] * dy + py[i] * dx;
			// ceil(p/q) is the minimum k such that the point (x,y) will
			// be below line that visits points:
			// (k*px[i], k*py[i]) , (k*px[i+1], k*py[i+1])
			k = Math.max(k, (p + q - 1) / q); // Do ceil(p/q) without floats.
		}

		return k;
	}

	public int[] minJumps(int[] x, int[] y, int[] d) {
		int[] r = new int[d.length];
		for (int i = 0; i < r.length; i++) {
			r[i] = minJumpsSingle(x[i], y[i], d[i]);
		}
		return r;
	}

	public static void main(String[] args) {
		int[] minJumps = new ToastJumping().minJumps(new int[] { 0, -2, -2, 2,
				3, 0, -1 },

		new int[] { 1, -2, 2, -2, 0, -3, 3 },

		new int[] { 9, 9, 9, 9, 9, 9, 9 });
		for (int i = 0; i < minJumps.length; i++) {
			System.out.printf("%d ", minJumps[i]);
		}
		System.out.println();
	}
}
