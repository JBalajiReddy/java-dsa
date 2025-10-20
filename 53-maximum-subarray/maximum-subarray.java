class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0, mxSum = nums[0];
        for (int n : nums) {
            sum += n;
            mxSum = (mxSum < sum) ? sum : mxSum;
            if (sum < 0) sum = 0;
        }
        return mxSum;
    }
}