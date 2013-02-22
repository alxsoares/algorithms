package alex.algorithms.sorting;

public class InPlaceMergeSort {
    /**
     * In-Place Merge Sort.
     * 
     * Building on the algorithm core found in
     * http://www.cs.ubc.ca/~harrison/Java/MergeSortAlgorithm.java.html
     */
    public static void inPlaceSort(final Comparable[] x) {
        inPlaceSort(x, 0, x.length - 1);
    }

    private static void inPlaceSort(final Comparable[] x, final int first, final int last) {
        int mid, lt, rt;
        Comparable tmp;

        if (first >= last)
            return;

        mid = (first + last) / 2;

        inPlaceSort(x, first, mid);
        inPlaceSort(x, mid + 1, last);

        lt = first;
        rt = mid + 1;
        // One extra check: can we SKIP the merge?
        if (x[mid].compareTo(x[rt]) <= 0)
            return;

        while (lt <= mid && rt <= last) {
            // Select from left: no change, just advance lt
            if (x[lt].compareTo(x[rt]) <= 0)
                lt++;
            // Select from right: rotate [lt..rt] and correct
            else {
                tmp = x[rt]; // Will move to [lt]
                System.arraycopy(x, lt, x, lt + 1, rt - lt);
                x[lt] = tmp;
                // EVERYTHING has moved up by one
                lt++;
                mid++;
                rt++;
            }
        }
        // Whatever remains in [rt..last] is in place
    }

    public static void main(final String[] args) {
        Integer arr[] = { 100, 99, 76, 2, 23, 121, 54545 };
        inPlaceSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }
}
