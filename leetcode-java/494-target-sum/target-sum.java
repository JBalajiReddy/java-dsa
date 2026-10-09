class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        target = Math.abs(target);
        int sum = Arrays.stream(nums).sum();
        if (target > sum || (sum + target) % 2 != 0) {
            return 0;
        }

        int W = (sum + target) / 2;
        int[][] dp = new int[n + 1][W + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                int skip = dp[i - 1][j];
                int take = 0;
                if (nums[i - 1] <= j) {
                    take = dp[i - 1][j - nums[i - 1]];
                }
                dp[i][j] = skip + take;
            }
        }
        return dp[n][W];
    }
}