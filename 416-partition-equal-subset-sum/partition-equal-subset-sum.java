class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int s : nums)
            sum += s;

        if (sum % 2 != 0)
            return false;

        sum = sum / 2;
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];
        for (int[] r : dp)
            Arrays.fill(r, -1);
        return solve(nums, n - 1, sum, dp);
    }

    private boolean solve(int[] nums, int i, int t, int[][] dp) {
        if (t == 0)
            return true;
        if (i == 0)
            return nums[i] == t;
        if (dp[i][t] != -1)
            return dp[i][t] == 1;

        //dp[i][t] == 1 means subset is possible (i.e., return true)
        //dp[i][t] == 0 means subset is not possible (i.e., return false)

        boolean pick = false;
        boolean noPick = solve(nums, i - 1, t, dp);
        if (t >= nums[i])
            pick = solve(nums, i - 1, t - nums[i], dp);

        dp[i][t] = (pick || noPick) ? 1 : 0;
        return pick || noPick;
    }
}