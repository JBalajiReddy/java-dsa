//BOTTOM UP
class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length; //rows
        int n = grid[0].length; //cols
        int[] prev = new int[n];

        if (grid[0][0] == 1)
            return 0;

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    curr[j] = 0;
                else if (i == 0 && j == 0)
                    curr[j] = 1;
                else {
                    int up = 0, left = 0;
                    if (i > 0)
                        up = prev[j];
                    if (j > 0)
                        left = curr[j - 1];
                    curr[j] = up + left;
                }
            }
            prev = curr;
        }

        return prev[n - 1];
    }
}

//BOTTOM UP
// class Solution {
//     public int uniquePathsWithObstacles(int[][] grid) {
//         int m = grid.length;
//         int n = grid[0].length;
//         int[][] dp = new int[m][n];

//         if (grid[0][0] == 1)
//             return 0;

//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (grid[i][j] == 1)
//                     dp[i][j] = 0;
//                 else if (i == 0 && j == 0)
//                     dp[i][j] = 1;
//                 else {
//                     int up = 0, left = 0;
//                     if (i > 0)
//                         up = dp[i - 1][j];
//                     if (j > 0)
//                         left = dp[i][j - 1];
//                     dp[i][j] = up + left;
//                 }
//             }
//         }

//         return dp[m - 1][n - 1];
//     }
// }

//TOP DOWN
// class Solution {
//     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//         int n = obstacleGrid[0].length;
//         int m = obstacleGrid.length;
//         int[][] dp = new int[m][n];
//         for (int[] r : dp)
//             Arrays.fill(r, -1);
//         return solve(obstacleGrid, m - 1, n - 1, dp);
//     }

//     private int solve(int[][] grid, int m, int n, int[][] dp) {
//         if (m < 0 || n < 0 || grid[m][n] == 1)
//             return 0;

//         if (m == 0 && n == 0) {
//             dp[m][n] = 1;
//             return 1;
//         }

//         if (dp[m][n] != -1)
//             return dp[m][n];

//         dp[m][n] = solve(grid, m - 1, n, dp) + solve(grid, m, n - 1, dp);

//         return dp[m][n];
//     }
// }