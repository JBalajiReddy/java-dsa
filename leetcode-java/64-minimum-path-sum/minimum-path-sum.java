class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        if (n == 1 && m == 1) {
            return grid[0][0];
        }

        int[][] dp = new int[n][m]; //min path sum/cost till (i, j) from (0, 0)
        int runningSum = 0;
        for (int row = 0; row < n; row++) {
            runningSum += grid[row][0];
            dp[row][0] = runningSum;
        }

        runningSum = 0;
        for (int col = 0; col < m; col++) {
            runningSum += grid[0][col];
            dp[0][col] = runningSum;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n - 1][m - 1];
    }
}