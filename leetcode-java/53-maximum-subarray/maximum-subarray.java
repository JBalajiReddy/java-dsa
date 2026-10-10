class Solution1 {
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

class Solution {
    public int maxSubArray(int[] nums) {
        int sum = nums[0], mxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            mxSum = Math.max(mxSum, sum);
        }
        return mxSum;
    }
}

