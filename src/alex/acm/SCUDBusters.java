package alex.acm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import alex.algorithms.geom.Ponto;

/**
 * 
 * http://poj.org/problem?id=1264
 */
public class SCUDBusters {
	class Point implements Comparable {
		public int x;
		public int y;
		public int xt;
		public int yt;
		public int quadrante;

		public Point() {

		}

		public Point(final int x, final int y) {
			this.x = x;
			this.y = y;
		}

		public int getQuadrante() {
			if (xt >= 0 && yt >= 0)
				return 1;
			if (xt < 0 && yt >= 0)
				return 2;
			if (xt < 0 && yt < 0)
				return 3;
			return 4;
		}

		public void translate(final int x, final int y) {
			this.xt = this.x - x;
			this.yt = this.y - y;
		}

		@Override
		public int compareTo(final Object o) {
			Point p = (Point) o;
			if (this.getQuadrante() < p.getQuadrante())
				return -1;
			if (this.getQuadrante() > p.getQuadrante())
				return 1;
			if (this.xt == p.xt && this.yt == p.yt)
				return 0;
			if (this.yt == 0 && p.yt == 0) {
				if (this.xt > p.xt)
					return 1;
				return -1;
			}
			if (this.yt * p.xt > this.xt * p.yt)
				return 1;
			return -1;
		}
	}

	public static void getPivot(final Point pontos[]) {
		int menor = 0;
		for (int i = 1; i < pontos.length; i++) {
			if (pontos[i].y < pontos[menor].y) {
				menor = i;
			}
		}
		Point aux = pontos[0];
		pontos[0] = pontos[menor];
		pontos[menor] = aux;
	}

	public static int crossProduct(final Point a, final Point b, final Point c) {
		return ((c.xt - a.xt) * (b.yt - a.yt) - (b.xt - a.xt) * (c.yt - a.yt));
	}

	public static void translate(final Point[] pontos, final Point pivot) {

		for (int i = 0; i < pontos.length; i++) {
			Point p = pontos[i];
			p.translate(pivot.x, pivot.y);
		}
	}

	boolean collinear(Point a, Point b, Point c) {
		return Math.abs(crossProduct(a, b, c)) == 0;
	}

	boolean ccw(Point a, Point b, Point c) {
		return crossProduct(a, b, c) > 0;
	}

	boolean cw(Point a, Point b, Point c) {
		return crossProduct(a, b, c) < 0;
	}

	double distance(Point a, Point b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	Point[] convexHull(Point[] pontos) {
		  getPivot(pontos);
	        Stack<Point> stack = new Stack<Point>();
	        Point pivot = pontos[0];
	        translate(pontos, pivot);
	        List<Point> list = new ArrayList<Point>();
	        for (int i = 1; i < pontos.length; i++) {
	            list.add(pontos[i]);
	        }
	        Collections.<Point> sort(list);
	        System.out.println("Ordernado");
	        for (Iterator<Point> iter = list.iterator(); iter.hasNext();) {
	            Point item = iter.next();
	            System.out.println(item);
	        }
	        System.out.println("FIM");
	        Point a = pivot;
	        Point b = list.get(0);
	        stack.push(a);
	        stack.push(b);
	        for (int i = 1; i < list.size(); i++) {
	            Point c = list.get(i);
	            b = stack.pop();
	            a = stack.pop();
	            stack.push(a);
	            stack.push(b);
	            while (crossProduct(a, b, c) < 0) {
	                stack.pop();
	                if (stack.size() >= 2) {
	                    b = stack.pop();
	                    a = stack.pop();
	                    stack.push(a);
	                    stack.push(b);
	                } else {
	                    break;
	                }
	            }
	            stack.push(c);
	        }
	        Point[] res = stack.toArray(new Point[]{});
	        return res;
	}

	double polygonArea(Point[] p) {
		double res = 0.0;
		for (int i = 0; i + 1 < p.length; i++)
			res += (p[i].x * p[i + 1].y - p[i + 1].x * p[i].y);
		return res / 2.0;
	}

	boolean insideConvex(Point[] hull, Point p) {
		boolean oddNodes = false;
		for (int i = 0; i + 1 < hull.length; i++) {
			if ((hull[i].y < p.y && hull[i + 1].y >= p.y)
					|| (hull[i + 1].y < p.y && hull[i].y >= p.y)) {
				if (hull[i].x + (p.y - hull[i].y) / (hull[i + 1].y - hull[i].y)
						* (hull[i + 1].x - hull[i].x) < p.x) {
					oddNodes = !oddNodes;
				}
			}
		}
		return oddNodes;
	}

	public static void main(String[] args) {

	}

}
