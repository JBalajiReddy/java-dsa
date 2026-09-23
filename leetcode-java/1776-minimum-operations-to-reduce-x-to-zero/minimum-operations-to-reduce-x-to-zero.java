class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        int target = sum - x;
        if (target < 0) {
            return -1;
        } else if (target == 0) {
            return n;
        }

        int l = 0, r = 0, mxLen = -1;
        int currSum = 0;
        while (r < n) {
            currSum += nums[r];
            while (l < r && currSum > target) {
                currSum -= nums[l++];
            }

            if (currSum == target) {
                mxLen = Math.max(mxLen, r - l + 1);
            }
            r++;
        }

        return n - mxLen > n ? -1 : n - mxLen;
    }
}