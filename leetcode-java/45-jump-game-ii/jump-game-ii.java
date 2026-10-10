class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0; // Already at the end, 0 jumps needed

        int jumps = 0;
        int currentRangeEnd = 0;
        int maxReach = 0;

        // Loop up to n - 1 (we don't need to jump from the last index)
        for (int i = 0; i < n - 1; i++) {
            // Continuously update the furthest reach possible
            maxReach = Math.max(maxReach, i + nums[i]);

            // When we reach the boundary of our current jump choice:
            if (i == currentRangeEnd) {
                jumps++;                   // We MUST take a jump
                currentRangeEnd = maxReach; // Expand the boundary to the new max reach

                // Early exit: if current range already reaches or exceeds the last index
                if (currentRangeEnd >= n - 1) {
                    break;
                }
            }
        }

        return jumps;
    }
}