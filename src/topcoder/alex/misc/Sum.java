package topcoder.alex.misc;

import java.util.Arrays;

public class Sum {

    public static void printSum(final int[] numbers, final int sum) {
        Arrays.sort(numbers);
        int begin = 0;
        int end = numbers.length - 1;
        while (begin < end) {
            int nb = numbers[begin];
            int ne = numbers[end];
            if (ne - sum > nb) {
                end--;
                continue;
            }
            if (sum - nb > ne) {
                begin++;
                continue;
            }
            if (nb + ne == sum) {
                System.out.printf("%d %d\n", nb, ne);
                end--;
                begin++;
            }

        }
    }

    public static void printSum4(final int[] numbers) {
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 3; i++) {
            int begin = i + 1;
            int end = numbers.length - 1;
            int first = numbers[i];
            while (begin <= end) {
                int nb = numbers[begin];
                int ne = numbers[end];
                int sum = first + ne + nb;
                if (sum == 0) {
                    System.out.printf("%d %d %d\n", first, nb, ne);
                    break;
                }
                if (sum > 0) {
                    end--;
                } else if (sum < 0) {
                    begin++;
                }

            }
        }
    }

    public static void sumZero(final int[] A) {
        Arrays.sort(A);

        int n = A.length;
        for (int i = 0; i < n - 3; i++) {
            int j = i + 1;
            int k = n - 1;
            while (k >= j) {
                if (A[i] + A[j] + A[k] == 0) {
                    System.out.printf("%d %d %d\n", i, j, k);
                    break;
                }
                int s = A[i] + A[j] + A[k];
                if (s > 0) {
                    k--;
                } else {
                    j++;
                }

            }
        }
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        int arr[] = { 1, 2, 3, 4, 5 };
        int arr2[] = { -4, -2, -1, 1, 2, 3, 4, 5 };
        printSum4(arr2);
    }

}
