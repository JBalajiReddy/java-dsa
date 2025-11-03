class Solution {
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int res = -1, preMin = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > preMin)
                res = Math.max(res, nums[i] - preMin);
            else
                preMin = nums[i];
        }
        return res;
    }
}