class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long ans = Long.MIN_VALUE;

        // Iterate through the array to find potential starting points of the pattern
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            long res = 0;

            // ---------------------------------------------------------
            // 1. Identify First Segment: Increasing (The Rise)
            // ---------------------------------------------------------
            // Move 'j' forward as long as the sequence is increasing
            while (j < n && nums[j - 1] < nums[j]) {
                j++;
            }
            int p = j - 1; // 'p' is the index of the PEAK

            // If p == i, it means there was no increasing segment (flat or decreasing start)
            if (p == i) {
                continue;
            }

            // ---------------------------------------------------------
            // 2. Identify Second Segment: Decreasing (The Fall)
            // ---------------------------------------------------------
            // Initialize the core sum with the peak and the element immediately preceding it.
            // These are considered the mandatory "turning point" of the structure.
            res += nums[p] + nums[p - 1];

            // Move 'j' forward as long as the sequence is decreasing
            while (j < n && nums[j - 1] > nums[j]) {
                res += nums[j]; // Add elements of the descent to the core sum
                j++;
            }
            int q = j - 1; // 'q' is the index of the VALLEY

            // ---------------------------------------------------------
            // 3. Validation Checks
            // ---------------------------------------------------------
            // Check if:
            // - q == p: No decreasing segment existed.
            // - q == n - 1: Reached end of array (cannot have a 3rd segment).
            // - nums[j] <= nums[q]: Next element doesn't rise (not a Trionic pattern).
            if (q == p || q == n - 1 || (j < n && nums[j] <= nums[q])) {
                // Set i to q to skip this invalid segment and continue
                i = q;
                continue;
            }

            // ---------------------------------------------------------
            // 4. Identify Third Segment: Increasing (The Second Rise)
            // ---------------------------------------------------------
            // Add the first element of the upward slope (mandatory start of right wing)
            res += nums[q + 1];

            // Optimize Right Wing: Find the max prefix sum of the increasing sequence starting at q+2
            long maxSum = 0;
            long sum = 0;
            for (int k = q + 2; k < n && nums[k] > nums[k - 1]; k++) {
                sum += nums[k];
                // We greedily take the max sum encountered (dropping negative tails)
                maxSum = Math.max(maxSum, sum);
            }
            res += maxSum;

            // ---------------------------------------------------------
            // 5. Optimize First Segment (The First Rise)
            // ---------------------------------------------------------
            // Optimize Left Wing: Find the max suffix sum of the increasing sequence ending at p-2
            maxSum = 0;
            sum = 0;
            for (int k = p - 2; k >= i; k--) {
                sum += nums[k];
                // We greedily take the max sum encountered (dropping negative starts)
                maxSum = Math.max(maxSum, sum);
            }
            res += maxSum;

            // ---------------------------------------------------------
            // 6. Update Result & Iterator
            // ---------------------------------------------------------
            ans = Math.max(ans, res);

            // Important: Move 'i' to 'q - 1'.
            // The valley 'q' of the current pattern can serve as the start/peak of the *next* pattern.
            // We backtrack slightly to ensure we don't miss overlapping Trionic sequences.
            i = q - 1;
        }

        return ans;
    }
}