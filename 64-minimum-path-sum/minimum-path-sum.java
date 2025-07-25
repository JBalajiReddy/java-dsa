//BOTTOM UP
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = grid[i][j];
                else {
                    int up = grid[i][j], left = grid[i][j];
                    if (i > 0)
                        up += dp[i - 1][j];
                    else up+= (int) Math.pow(10, 9);
                    if (j > 0)
                        left += dp[i][j - 1];
                    else
                        left += (int) Math.pow(10, 9);
                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}

//top-down
// class Solution {
//     public int minPathSum(int[][] grid) {
//         int m = grid.length;
//         int n = grid[0].length;
//         int[][] dp = new int[m][n];
//         for (int[] r : dp) Arrays.fill(r, -1);
//         return solve(grid, m - 1, n - 1, dp);
//     }

//     private int solve(int[][] grid, int m, int n, int[][] dp) {
//         if (m == 0 && n == 0) return grid[m][n];

//         if (m < 0 || n < 0) return Integer.MAX_VALUE;

//         if (dp[m][n] != -1) return dp[m][n];

//         int up = solve(grid, m - 1, n, dp);
//         int left = solve(grid, m, n - 1, dp);

//         dp[m][n] = grid[m][n] + Math.min(up, left);
//         return dp[m][n];
//     }
// }