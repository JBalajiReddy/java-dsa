class Solution {
    public int singleNumber(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int pf = nums[0];
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            res = pf ^ nums[i];
            pf = res;
        }
        return res;
    }
}