class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid[0].length;
        int m = obstacleGrid.length;
        int[][] dp = new int[m][n];
        for (int[] r : dp)
            Arrays.fill(r, -1);
        return solve(obstacleGrid, m - 1, n - 1, dp);
    }

    private int solve(int[][] grid, int m, int n, int[][] dp) {
        if (m < 0 || n < 0 || grid[m][n] == 1)
            return 0;

        if (m == 0 && n == 0) {
            dp[m][n] = 1;
            return 1;
        }

        if (dp[m][n] != -1)
            return dp[m][n];

        dp[m][n] = solve(grid, m - 1, n, dp) + solve(grid, m, n - 1, dp);

        return dp[m][n];
    }
}