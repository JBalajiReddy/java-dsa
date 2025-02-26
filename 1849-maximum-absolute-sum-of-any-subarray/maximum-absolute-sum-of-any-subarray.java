//Kadane's Algorithm
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        return Math.max(maxSum(nums), Math.abs(minSum(nums)));
    }

    public int maxSum(int[] nums) {
        int mxsm = nums[0];
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > mxsm) {
                mxsm = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return mxsm;
    }

    public int minSum(int[] nums) {
        int minSum = nums[0];
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum < minSum) {
                minSum = sum;
            }
            if (sum > 0) {
                sum = 0;
            }
        }
        return minSum;
    }
}