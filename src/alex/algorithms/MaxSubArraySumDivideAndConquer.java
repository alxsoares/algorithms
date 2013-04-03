package alex.algorithms;

public class MaxSubArraySumDivideAndConquer {

    static int max(final int a, final int b, final int c) {
        return Math.max(Math.max(a, b), c);
    }

    static int maxSumInSubArray(final int array[], final int left, final int middle, final int right) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = middle; i >= left; i--) {
            sum = sum + array[i];
            if (sum > leftSum)
                leftSum = sum;
        }

        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = middle + 1; i <= right; i++) {
            sum = sum + array[i];
            if (sum > rightSum)
                rightSum = sum;
        }

        return leftSum + rightSum;
    }

    static int maxSubArraySum(final int array[], final int left, final int right) {
        // Base Case: Only one element
        if (left == right)
            return array[left];

        // Find middle point
        int middle = (left + right) / 2;

        //@formatter:off
        /*
         * @formatter:off
         * Return maximum of following three possible cases 
         * a) Maximum subarray sum in left half 
         * b) Maximum subarray sum in right half 
         * c) Maximum subarray sum such that the subarray crosses the midpoint
         * 
         */
        //@formatter:on
        return max(maxSubArraySum(array, left, middle), maxSubArraySum(array, middle + 1, right),
                maxSumInSubArray(array, left, middle, right));
    }

    public static void main(final String[] args) {
        int arr[] = { -2, 1, -3, 4, -1, 2, 1, -5, 8 };
        int maxSum = maxSubArraySum(arr, 0, arr.length - 1);
        System.out.printf("Maximum contiguous sum is %d\n", maxSum);
    }
}
