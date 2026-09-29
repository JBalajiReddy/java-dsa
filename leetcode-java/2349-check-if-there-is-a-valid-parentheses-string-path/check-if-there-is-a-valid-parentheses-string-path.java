class Solution {
    public boolean hasValidPath(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        int pathLen = n + m - 1;

        // Pruning impossible global conditions
        if (pathLen % 2 != 0 || grid[0][0] == ')' || grid[n - 1][m - 1] == '(') {
            return false;
        }

        int maxBalance = pathLen / 2;
        boolean[][][] dp = new boolean[n][m][maxBalance + 1];

        // Base Case: start cell (0, 0)
        dp[0][0][1] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    continue;

                int val = (grid[i][j] == '(') ? 1 : -1;

                for (int k = 0; k <= maxBalance; k++) {
                    int prevK = k - val;

                    // Skip if the required previous balance is out of bounds [0, maxBalance]
                    if (prevK < 0 || prevK > maxBalance)
                        continue;

                    // Transition from Top cell (i - 1, j)
                    if (i > 0 && dp[i - 1][j][prevK]) {
                        dp[i][j][k] = true;
                    }

                    // Transition from Left cell (i, j - 1)
                    if (j > 0 && dp[i][j - 1][prevK]) {
                        dp[i][j][k] = true;
                    }
                }
            }
        }

        // Target state: cell (n - 1, m - 1) with an exact balance of 0
        return dp[n - 1][m - 1][0];
    }
}

class Solution_TopDown {
    public boolean hasValidPath(char[][] grid) {
        int n = grid.length, m = grid[0].length;

        // Path length is (n + m - 1). If odd, a balanced parentheses path is impossible.
        if ((n + m - 1) % 2 != 0) {
            return false;
        }

        // Must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[n - 1][m - 1] == '(') {
            return false;
        }

        // Maximum balance can never exceed (n + m) / 2
        int maxBalance = (n + m) / 2;
        Boolean[][][] vis = new Boolean[n][m][maxBalance + 1];

        return dfs(grid, 0, 0, 0, vis, maxBalance);
    }

    private boolean dfs(char[][] grid, int i, int j, int balanced, Boolean[][][] vis, int maxBalance) {
        int n = grid.length, m = grid[0].length;

        // Boundary checks
        if (i >= n || j >= m) {
            return false;
        }

        // Update balance for current cell
        int currentBal = balanced + (grid[i][j] == '(' ? 1 : -1);

        // Prune if balance drops below 0 or exceeds max possible balance
        if (currentBal < 0 || currentBal > maxBalance) {
            return false;
        }

        // Target cell reached
        if (i == n - 1 && j == m - 1) {
            return vis[i][j][balanced] = (currentBal == 0);
        }

        // Return memoized result if available
        if (vis[i][j][currentBal] != null) {
            return vis[i][j][currentBal];
        }

        // Explore down and right
        boolean isValid = dfs(grid, i + 1, j, currentBal, vis, maxBalance)
                || dfs(grid, i, j + 1, currentBal, vis, maxBalance);

        return vis[i][j][currentBal] = isValid;
    }
}