package alex.algorithms.greedy;

/**
 * http://en.wikipedia.org/wiki/Activity_selection_problem
 * 
 */
public class ActivitySelection {

	// Prints a maximum set of activities that can be done by a single
	// person, one at a time.
	// n --> Total number of activities
	// s[] --> An array that contains start time of all activities
	// f[] --> An array that contains finish time of all activities
	public static void printMaxActivities(int s[], int f[], int n) {
		int i, j;

		System.out.printf("Following activities are selected \n");

		// The first activity always gets selected
		i = 0;
		System.out.printf("%d ", i);

		// Consider rest of the activities
		for (j = 1; j < n; j++) {
			// If this activity has start time greater than or equal to the
			// finish
			// time of previously selected activity, then select it
			if (s[j] >= f[i]) {
				System.out.printf("%d ", j);
				i = j;
			}
		}
	}

	public static void main(String[] args) {
		int s[] = { 1, 3, 0, 5, 8, 5 };
		int f[] = { 2, 4, 6, 7, 9, 9 };
		int n = s.length;
		printMaxActivities(s, f, n);
	}

}
