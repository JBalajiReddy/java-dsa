class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int minD = Integer.MAX_VALUE;
        for (int i = 0; i <= n - k; i++) {
            minD = Math.min(minD, nums[i + k - 1] - nums[i]);
        }
        return minD;
    }
}