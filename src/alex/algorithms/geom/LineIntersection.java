package alex.algorithms.geom;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class LineIntersection {

	// Given three colinear Pontos p, q, r, the function checks if
	// Ponto q lies on line segment 'pr'
	static boolean onSegment(Ponto p, Ponto q, Ponto r) {
		if (q.x <= max(p.x, r.x) && q.x >= min(p.x, r.x)
				&& q.y <= max(r.y, r.y) && q.y >= min(r.y, r.y))
			return true;

		return false;
	}

	// 0 --> p, q and r are colinear
	// 1 --> Clockwise
	// 2 --> Counterclockwise
	static int orientation(Ponto p, Ponto q, Ponto r) {
		int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

		if (val == 0)
			return 0; // colinear

		return (val > 0) ? 1 : 2; // clock or counterclock wise
	}

	// The main function that returns true if line segment 'p1q1'
	// and 'p2q2' intersect.
	static boolean doIntersect(Ponto p1, Ponto q1, Ponto p2, Ponto q2) {
		// Find the four orientations needed for general and
		// special cases
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);

		// General case
		if (o1 != o2 && o3 != o4)
			return true;

		// Special Cases
		// p1, q1 anf p2 are colinear and p2 lies in p1q1
		if (o1 == 0 && onSegment(p1, q1, p2))
			return true;

		// p1, q1 anf p2 are colinear and q2 lies in p1q1
		if (o2 == 0 && onSegment(p1, q1, q2))
			return true;

		// p2, q2 anf p1 are colinear and p1 lies in p2q2
		if (o3 == 0 && onSegment(p2, q2, p1))
			return true;

		// p2, q2 anf q1 are colinear and q1 lies in p2q2
		if (o4 == 0 && onSegment(p2, q2, q1))
			return true;

		return false; // Doesn't fall in any of the above cases
	}

	// Returns true if the point p lies inside the polygon[] with n vertices
	static boolean isInside(Ponto polygon[],  Ponto p) {
		if(polygon== null) return false;
		int n = polygon.length;
		// There must be at least 3 vertices in polygon[]
		if (n < 3)
			return false;

		// Create a point for line segment from p to infinite
		Ponto extreme = new Ponto(Integer.MAX_VALUE, p.y);

		// Count intersections of the above line with sides of polygon
		int count = 0;
		for (int i = 0; i < n - 1; i++) {
			// If p is same as one of the vertices
			if (p.x == polygon[i].x && p.y == polygon[i].y)
				return true;

			// Otherwise check for intersection
			if (doIntersect(polygon[i], polygon[i + 1], p, extreme))
				count++;
		}

		// If p is same as last vertex
		if (p.x == polygon[n - 1].x && p.y == polygon[n - 1].y)
			return true;

		// Last side (from last to first point) is missed in the
		// above loop, consider it now
		if (doIntersect(polygon[n - 1], polygon[0], p, extreme))
			count++;

		// Return true if count is odd, false otherwise
		return ((count & 1) > 0); // Same as (count%2 == 1)
	}

}
