package topcoder.alex;

import java.util.AbstractList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PartialSeries {

    private int countExtrema(final int[] series) {
        if (series == null || series.length <= 2)
            return 0;
        int count = 0;
        for (int i = 1; i < series.length - 1; i++) {
            int a = series[i - 1];
            int b = series[i];
            int c = series[i + 1];
            if (a * b * c < 0)
                continue;
            if (b < a && b < c)
                count++;
            else if (b > a && b > c)
                count++;
        }
        return count;
    }

    public List<Integer> asList(final int[] is) {
        return new AbstractList<Integer>() {
            @Override
            public Integer get(final int i) {
                return is[i];
            }

            @Override
            public int size() {
                return is.length;
            }
        };
    }

    public int[] getFirst(final int[] series, final int[] available) {
        LinkedList<Integer> av = new LinkedList<Integer>(asList(available));
        Collections.sort(av);
        for (int i = 0; i < series.length; i++) {
            if (series[i] == -1) {
                int min = Integer.MAX_VALUE;
                int auxj = -1;
                for (int j = 0; j < av.size(); j++) {
                    int at = av.get(j);
                    series[i] = at;
                    int tempC = countExtrema(series);
                    if (tempC < min) {
                        min = tempC;
                        auxj = j;
                    }

                }
                series[i] = av.remove(auxj);
            }
        }
        return series;
    }

    public static void printArray(final int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }

    public static void main(final String[] args) {
        PartialSeries s = new PartialSeries();
        int[] series = { -1, -1, -1, -1, -1 };
        int[] available = { 1, 2, 3, 4, 5 };
        printArray(s.getFirst(series, available));

        int[] series2 = { 8, -1, 6, 4, -1, -1, 6 };
        int[] available2 = { 2, 3, 6 };
        printArray(s.getFirst(series2, available2));

        int[] series3 = { 1, -1, 6, 2, 4, -1, 2, 7, -1, -1 };
        int[] available3 = { 1, 2, 4, 7, 8, 8 };
        printArray(s.getFirst(series3, available3));// {1, 1, 6, 2, 4, 2, 2, 7,
                                                    // 7, 4 }
    }

}
