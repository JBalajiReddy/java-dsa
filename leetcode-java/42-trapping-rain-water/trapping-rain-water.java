class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0)
            return 0;

        // sufMax[i] stores the maximum height from index i to n-1 (right boundary)
        int[] sufMax = new int[n];

        // Base case: the suffix max for the last element is the height itself
        sufMax[n - 1] = height[n - 1];

        // Step 1: Precompute suffix maximums from right to left in O(n) time.
        for (int i = n - 2; i >= 0; i--) {
            sufMax[i] = Math.max(sufMax[i + 1], height[i]);
        }

        int totalWater = 0;
        // Variable to maintain the running maximum height from index 0 to i (left boundary)
        int prefMax = height[0];

        // Step 2: Iterate left-to-right to calculate trapped water at each index.
        for (int i = 0; i < n; i++) {
            // Update the running prefix maximum
            prefMax = Math.max(prefMax, height[i]);

            // The water level above height[i] is bounded by the shorter of the two walls
            int waterLevel = Math.min(prefMax, sufMax[i]);

            // Add trapped water at current position (waterLevel - current height)
            totalWater += waterLevel - height[i];
        }

        return totalWater;
    }
}