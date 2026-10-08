class Solution {
    public int uniquePaths(int n, int m) {
        int[][] dp = new int[n][m]; //tracks no of unique paths to reach (i, j) from (0, 0)
        for (int row = 0; row < n; row++) {
            dp[row][0] = 1;
        }

        for (int col = 0; col < m; col++) {
            dp[0][col] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }
}

class Solution1 {
    /**
     * Space-Optimized Solution for Unique Paths (LeetCode 62)
     * 
     * Time Complexity:  O(m * n) - Iterates through all m * n grid cells.
     * Space Complexity: O(n) - Uses a single 1D array of size n (columns).
     */
    public int uniquePaths(int m, int n) {
        // dp[j] stores the number of unique paths to reach column j in the current row
        int[] dp = new int[n];

        // Base Case: First row has only 1 unique path for each column (moving purely right)
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }

        // Process remaining rows (from row 1 to m - 1)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j] (new) = dp[j] (old value from row above) + dp[j - 1] (value from left cell)
                dp[j] = dp[j] + dp[j - 1];
            }
        }

        // Final answer: paths to bottom-right cell (column n - 1 in last row)
        return dp[n - 1];
    }
}