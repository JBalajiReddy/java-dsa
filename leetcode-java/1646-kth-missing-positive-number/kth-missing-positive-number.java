class Solution {
    public int findKthPositive(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - (mid + 1);
            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // At the end of the loop, 'low' is the index of the first element
        // where the number of missing integers is >= k.
        // The k-th missing number is simply k positions after the numbers we
        // would have if there were no gaps up to this point. 'low' represents this count.
        // Therefore, the k-th missing number is low + k.
        // This is also equivalent to (high + 1 + k), as low = high + 1 when the loop terminates.

        return low + k; //high + 1 + k
    }
}