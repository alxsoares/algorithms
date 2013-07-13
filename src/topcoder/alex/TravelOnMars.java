package topcoder.alex;

/**
 * 
 * Problem Statement
 * 
 * 
 * Bob recently went to Mars.
 * 
 * There are N cities on Mars. The cities all lie on a circular railroad and
 * they are numbered 0 through N-1 along the railroad. More precisely, there is
 * a railroad segment that connects cities N-1 and 0, and for each i (0 <= i <=
 * N-2) there is a railroad segment that connects cities i and i+1. Trains
 * travel along the railroad in both directions.
 * 
 * You are given a int[] range with N elements. For each i: the set of cities
 * that are reachable from city i by a direct train is precisely the set of
 * cities that are within the distance range[i] of city i. (The distance between
 * two cities is the smallest number of railroad segments one needs to travel in
 * order to get from one city to the other. For example, if N=17 and range[2]=3,
 * the cities directly reachable from city 2 are the cities {16,0,1,2,3,4,5}.)
 * 
 * You are also given ints startCity and endCity. Bob starts his tour in the
 * city startCity and wants to end it in the city endCity. Calculate and return
 * the minimum number of succesive direct trains he needs to take.
 * 
 */
public class TravelOnMars {

	public int minTimes(int[] range, int startCity, int endCity) {
		int INF = 1000000000;
		int n = range.length;
		int dist[][] = new int[n][n];
		// Make initial adjacency matrix, distance between two unconnected
		// vertexes is INF:
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// a is the distance if we move to the left
				int a = (i - j + n) % n;
				// a is the distance if we move to the right
				int b = (j - i + n) % n;
				if (Math.min(a, b) <= range[i]) {
					// close enough:
					dist[i][j] = 1;
				} else {
					dist[i][j] = INF;
				}
			}
		}
		// Floyd-Warshall
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		return dist[startCity][endCity];
	}

	public static void main(String[] args) {
		System.out.println(new TravelOnMars().minTimes(new int[] { 3, 2, 1, 1,
				3, 1, 2, 2, 1, 1, 2, 2, 2, 2, 3 }, 6, 13));
	}

}
