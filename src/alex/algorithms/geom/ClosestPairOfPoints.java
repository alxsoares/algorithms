package alex.algorithms.geom;

import java.util.Random;

public class ClosestPairOfPoints {

	/**
	 * minDist = infinity for i = 1 to length(P) - 1 for j = i + 1 to length(P)
	 * let p = P[i], q = P[j] if dist(p, q) < minDist: minDist = dist(p, q)
	 * closestPair = (p, q) return closestPair
	 * 
	 * @param points
	 */
	public static double closestPairOfPointsBruteForce(Ponto[] points) {
		double minDist = Double.MAX_VALUE;
		for (int i = 0; i < points.length; i++) {
			Ponto p1 = points[i];
			for (int j = i + 1; j < points.length; j++) {
				Ponto p2 = points[j];
				double dist = dist(p1, p2);
				if (dist < minDist) {
					minDist = dist;
				}
			}
		}
		return Math.sqrt(minDist);
	}

//	private ParDePontos closestPoints(PointSet xOrder, PointSet yOrder,
//			int left, int right) {
//		System.out.println("Right is: " + right + "Left is:" + left);
//		Point[] closestPair = new Point[2];
//		if (yOrder.list.size() <= 3) {
//			System.out.print("USED BRUTE FORCE");
//			// then do brute force by comparing them
//			return closestPointBF();
//		}
//		// set is larger than 3, using divide and conquer
//		else {
//			System.out.println("IN ELSE!");
//			int imaginaryLine = yOrder.list.size() / 2;// separating X logically
//			// PointPair closestSoFar;//closest pair found so far
//			double distanceBetweenClosest = Integer.MAX_VALUE;
//			// physically splitting up y
//			PointSet yLeft = new PointSet();
//			PointSet yRight = new PointSet();
//			for (int i = 0; i < imaginaryLine; i++)
//				yLeft.add(yOrder.get(i));
//			for (int j = imaginaryLine; j < yOrder.list.size(); j++)
//				yRight.add(yOrder.get(j));
//			// make two recursive calls
//			PointPairSet leftRecursive = closestPoints(xOrder, yLeft, 0,
//					imaginaryLine);// left side examined
//			PointPairSet rightRecursive = closestPoints(xOrder, yRight,
//					imaginaryLine, right);// right side examined
//			double closestLeft = Integer.MAX_VALUE;
//			double closestRight = Integer.MAX_VALUE;
//			for (int k = 0; k < leftRecursive.numElements(); k++) {
//				if (leftRecursive.get(k).distance() < closestLeft)
//					closestLeft = leftRecursive.get(k).distance();
//			}
//			for (int k = 0; k < rightRecursive.numElements(); k++) {
//				if (rightRecursive.get(k).distance() < closestRight)
//					closestRight = rightRecursive.get(k).distance();
//			}
//			for (int i = 0; i < yOrder.list.size(); i++) {
//				// if outside range delete
//				if (yOrder.list.get(i).getX() < left
//						|| yOrder.list.get(i).getX() > right) {
//					// discard all points from ylist that are not in the strip
//					System.out.print("Removed from y:" + yOrder.list.get(i));
//					yOrder.remove(yOrder.list.get(i));
//				}
//			}
//			// These points are now examined in linear time to determine the
//			// closest pair
//			return closestPointBF();
//		}
//	}

	public static double dist(Ponto p1, Ponto p2) {
		double dx = p1.x = p2.x;
		double dy = p1.y - p2.y;
		return dx * dx + dy * dy;
	}

	public static void main(String[] args) {
		Ponto pontos[] = new Ponto[14];
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < 14; i++) {
			Ponto p = new Ponto(Math.abs(rand.nextInt()) % 20, Math.abs(rand
					.nextInt()) % 20);
			pontos[i] = p;
		}
		System.out.printf("%f\n", closestPairOfPointsBruteForce(pontos));
	}

}
