class Solution_2D {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = 1;
                } else if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}


class Solution {
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