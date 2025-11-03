//BOTTOM - UP

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];

        int[] arrWithNo1 = Arrays.copyOfRange(nums, 1, n); //2nd house -> nth 
        int[] arrWith1 = Arrays.copyOfRange(nums, 0, n - 1); //1st house -> (n - 1)th house

        int max1 = solve(arrWithNo1);
        int max2 = solve(arrWith1);

        return Math.max(max1, max2);
    }

    private int solve(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            int pick = nums[i] + (i > 1 ? dp[i - 2] : 0);
            int noPick = dp[i - 1];

            dp[i] = Math.max(pick, noPick);
        }

        return dp[n - 1];
    }
}