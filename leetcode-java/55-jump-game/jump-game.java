class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxReach = 0;

        for (int i = 0; i < n; i++) {
            // If current index is beyond the furthest reachable point, we are stuck
            if (i > maxReach) {
                return false;
            }

            // Update the furthest index reachable from here
            maxReach = Math.max(maxReach, i + nums[i]);

            // Early exit: if we can reach or overshoot the last index
            if (maxReach >= n - 1) {
                return true;
            }
        }

        return false;
    }
}