class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries, int k) {
        int n = nums.length, sum = 0;
        int[] diff = new int[n + 1];

        for (int i = 0; i < k; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            int val = queries[i][2];

            diff[left] -= val;
            if (right + 1 < n)
                diff[right + 1] += val;
        }
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (nums[i] + sum > 0)
                return false;
        }
        return true;
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int left = 0, right = queries.length;
        // Check if it's possible to make the array zero with all queries
        if (!isZeroArray(nums, queries, right))
            return -1;

        // Binary search to find the minimum number of queries needed
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isZeroArray(nums, queries, mid))
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }
}