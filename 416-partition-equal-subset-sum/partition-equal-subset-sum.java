class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int s : nums)
            sum += s;

        if (sum % 2 != 0)
            return false;

        sum /= 2;
        boolean dp[][] = new boolean[n + 1][sum + 1];
        //sum == 0
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (nums[i - 1] > j) {
                    //curr number is bigger than sum
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //pick, no-pick
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }
}