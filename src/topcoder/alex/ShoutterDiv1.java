package topcoder.alex;

public class ShoutterDiv1 {
	void swap(int[] a, int i, int j) {
		int aux = a[i];
		a[i] = a[j];
		a[j] = aux;
	}

	// Assume arrays s[] and t[] were parsed from the original
	// combined string input.
	int count(int[] s, int[] t) {
		int n = t.length;
		// Sort in non-decreasing order of s[i],
		int lleft = t[0]; // we need the left-most t[i] as a starting point.
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((s[j] < s[i]) || ((s[j] == s[i]) && (t[j] > t[i]))) {
					swap(s, i, j);
					swap(t, i, j);
				}
			}

			lleft = Math.min(lleft, t[i]);
		}
		int cost = 0;
		// For each rabbit i:
		for (int i = 0; i < n; i++) {
			int left = lleft;
			int j = 0;
			// We need to repeat until even the right-most s[j] was checked:
			while (j < n) {
				// If we can use Rabbit i, use it for free:
				if (s[i] <= left) {
					left = Math.max(left, t[i]);
				}
				// Find the best new rabbit (interval) to add to the set)
				int best = -1;
				while ((j < n) && (s[j] <= left)) {
					if ((best == -1) || (t[best] < t[j])) {
						best = j;
					}
					j++;
				}
				if (best == -1) {
					return -1;
				}
				// The new rabbit is the left-most extreme, we can break:
				if (j == n) {
					break;
				}
				// Increase the cost, update the left variable
				cost++;
				left = t[best];
			}
		}
		return cost;

	}

}
