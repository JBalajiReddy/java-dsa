class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int res = 1000_000_000;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                res = Math.min(res, Math.abs(i - start));
            }
        }
        return res;
    }
}