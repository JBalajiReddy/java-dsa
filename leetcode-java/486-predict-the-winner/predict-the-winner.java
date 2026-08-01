class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        if ((n & 1) == 0) {
            return true;
        }

        int[][] dp = new int[n][n];
        for (int[] r : dp) {
            Arrays.fill(r, -1);
        }

        return maxDiff(0, n - 1, nums, dp) >= 0;
    }

    private int maxDiff(int i, int j, int[] nums, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (i == j) {
            return dp[i][i] = nums[i];
        }

        return dp[i][j] = Math.max(
            nums[i] - maxDiff(i + 1, j, nums, dp),
            nums[j] - maxDiff(i, j - 1, nums, dp)
        );
    }
}