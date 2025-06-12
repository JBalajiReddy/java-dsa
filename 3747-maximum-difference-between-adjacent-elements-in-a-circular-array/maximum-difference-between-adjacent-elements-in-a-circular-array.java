class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        int edgeDiff = Math.abs(nums[0] - nums[n - 1]);
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums[i] - nums[i + 1]));
        }
        return Math.max(maxDiff, edgeDiff);
    }
}