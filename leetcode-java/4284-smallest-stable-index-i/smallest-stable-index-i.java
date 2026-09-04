class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // sufMin[i] stores the minimum value in the subarray nums[i ... n-1]
        int[] sufMin = new int[n];

        // Base case: the suffix min for the last element is the element itself
        sufMin[n - 1] = nums[n - 1];

        // Step 1: Precompute suffix minimums from right to left in O(n) time.
        // This lets us look up min(nums[i ... n-1]) in O(1) time during the main check.
        for (int i = n - 2; i >= 0; i--) {
            sufMin[i] = Math.min(sufMin[i + 1], nums[i]);
        }

        // Variable to maintain the running maximum for prefix nums[0 ... i]
        int max = nums[0];

        // Step 2: Iterate left-to-right to find the FIRST valid stable index.
        for (int i = 0; i < n; i++) {
            // Update the prefix maximum including current element nums[i]
            max = Math.max(max, nums[i]);

            // Check condition: max(nums[0..i]) - min(nums[i..n-1]) <= k
            if (max - sufMin[i] <= k) {
                // Return immediately since left-to-right order guarantees the smallest index i
                return i;
            }
        }

        // Return -1 if no index satisfies the stability condition
        return -1;
    }
}