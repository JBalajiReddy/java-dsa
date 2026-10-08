class Solution_2D {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1) {
            return 0;
        }

        int[][] dp = new int[n][m]; //tracks no of unique paths to reach (i, j) from (0, 0)
        for (int row = 0; row < n; row++) {
            if (obstacleGrid[row][0] != 1)
                dp[row][0] = 1;
            else
                break; // Blocked! All remaining cells in column 0 are unreachable.
        }

        for (int col = 0; col < m; col++) {
            if (obstacleGrid[0][col] != 1)
                dp[0][col] = 1;
            else
                break; // Blocked! All remaining cells in row 0 are unreachable.
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid[0].length;
        int[] dp = new int[m];
        
        // Base case for starting cell
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        for (int[] row : obstacleGrid) {
            for (int j = 0; j < m; j++) {
                if (row[j] == 1) {
                    dp[j] = 0; // Obstacle blocks path
                } else if (j > 0) {
                    dp[j] += dp[j - 1]; // dp[j] (from top) + dp[j-1] (from left)
                }
            }
        }

        return dp[m - 1];
    }
}