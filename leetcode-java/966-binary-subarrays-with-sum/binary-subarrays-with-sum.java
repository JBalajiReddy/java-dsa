class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return solve(nums, goal) - solve(nums, goal - 1); //(sum ≤ goal) - (sum ≤ goal - 1) = sum == goal
    }

    private int solve(int[] nums, int goal) {
        if (goal < 0)
            return 0;

        int n = nums.length;
        int left = 0, right = 0;
        int res = 0, sum = 0;

        while (right < n) {
            sum += nums[right];

            while (sum > goal) {
                sum -= nums[left];
                left++;
            }

            res += (right - left + 1); //no of subarrays -> length of that window
            //Every subarray that ends at index right and starts anywhere from left to right will be included.

            right++;
        }
        return res;
    }
}