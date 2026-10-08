class Solution_2D {
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m]; // Stores min path cost to reach (i, j) from (0, 0)

        // Base case: starting cell cost
        dp[0][0] = grid[0][0];

        // Fill 1st column: can only reach (i, 0) from (i-1, 0)
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Fill 1st row: can only reach (0, j) from (0, j-1)
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Fill rest of the grid using transition formula
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[n - 1][m - 1];
    }
}

class Solution_1D {
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] dp = new int[m];

        dp[0] = grid[0][0];
        for (int j = 1; j < m; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        for (int i = 1; i < n; i++) {
            dp[0] += grid[i][0]; // Update column 0 for new row
            for (int j = 1; j < m; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]); // dp[j] is top, dp[j-1] is left
            }
        }

        return dp[m - 1];
    }
}

class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue; // Start cell remains grid[0][0]
                else if (i == 0) grid[0][j] += grid[0][j - 1]; // Top edge
                else if (j == 0) grid[i][0] += grid[i - 1][0]; // Left edge
                else grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]); // Inner cells
            }
        }
        return grid[n - 1][m - 1];
    }
}