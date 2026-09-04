class Solution {
    public int trap(int[] height) {
        // Initialize pointers at both ends of the array
        int left = 0;
        int right = height.length - 1;

        // Track the maximum wall height encountered so far from each side
        int leftMax = 0;
        int rightMax = 0;

        int totalWater = 0;

        // Move pointers towards each other until they meet
        while (left < right) {
            // Key Insight: The amount of water trapped is always limited by the SHORTER boundary.
            // If the left wall is shorter than the right wall, the left side determines the water level.
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    // Current bar is taller than any seen on the left; update left boundary (no water trapped here)
                    leftMax = height[left];
                } else {
                    // Current bar is shorter than leftMax. Because height[left] < height[right],
                    // we are guaranteed that a wall at least as tall as leftMax exists to the right.
                    totalWater += leftMax - height[left];
                }
                left++; // Move left pointer inward
            } else {
                // Conversely, if the right wall is shorter or equal, the right side determines the water level.
                if (height[right] >= rightMax) {
                    // Current bar is taller than any seen on the right; update right boundary
                    rightMax = height[right];
                } else {
                    // We are guaranteed that a wall at least as tall as rightMax exists to the left.
                    totalWater += rightMax - height[right];
                }
                right--; // Move right pointer inward
            }
        }

        return totalWater;
    }
}

class Solution_PfxSuffix {
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