// bottom up
// class Solution {
//     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//         int m = obstacleGrid.length;
//         int n = obstacleGrid[0].length;
//         int dp[][] = new int[m + 1][n + 1];
//         for (int i = 0; i < m + 1; i++) {
//             for (int j = 0; j < n + 1; j++) {
//                 if (i == 0 || j == 0 || obstacleGrid[i - 1][j - 1] == 1)
//                     dp[i][j] = 0;
//                 else if (i == 1 && j == 1)
//                     dp[i][j] = 1;
//                 else
//                     dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
//             }
//         }
//         return dp[m][n];
//     }
// }

// top down
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int dp[][] = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return topDownDP(obstacleGrid, m, n, dp);
    }
    private int topDownDP(int[][] obstacleGrid, int m, int n, int[][] dp) {
        if (m == 0 || n == 0 || obstacleGrid[m - 1][n - 1] == 1) {
            dp[m][n] = 0;
            return 0;
        } else if (m == 1 && n == 1) {
            dp[m][n] = 1;
            return 1;
        } else if (dp[m][n] != -1) {
            return dp[m][n];
        } else {
            dp[m][n] = topDownDP(obstacleGrid, m - 1, n, dp) + topDownDP(obstacleGrid, m, n - 1, dp);;
        }
        return dp[m][n];
    }
}