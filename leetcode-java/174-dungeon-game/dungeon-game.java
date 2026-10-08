class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];

        // Iterate backwards from the destination cell (n-1, m-1) to the starting cell (0, 0)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                
                // Base Case: Destination cell containing the princess
                if (i == n - 1 && j == m - 1) {
                    // If cell gives health (> 0), knight only needs 1 HP to stay alive
                    // If cell takes health (<= 0), knight needs |damage| + 1 HP to survive
                    dp[i][j] = (dungeon[i][j] > 0) ? 1 : Math.abs(dungeon[i][j]) + 1;
                } else {
                    // Fetch required health for moving right or down.
                    // Out-of-bound moves are set to Integer.MAX_VALUE so Math.min ignores them.
                    int right = (j + 1 < m) ? dp[i][j + 1] : Integer.MAX_VALUE;
                    int down = (i + 1 < n) ? dp[i + 1][j] : Integer.MAX_VALUE;

                    // Calculate required HP before entering cell (i, j)
                    int res = Math.min(right, down) - dungeon[i][j];
                    
                    // Knight must enter every cell with at least 1 HP, even if dungeon[i][j] gives huge health
                    dp[i][j] = (res > 0) ? res : 1;
                }
            }
        }

        // Return minimum initial health needed at starting point (0, 0)
        return dp[0][0];
    }
}

class Solution_TopDown {
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        
        // Memoization table initialized to -1
        int[][] memo = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        // Start recursive traversal from the top-left cell (0, 0)
        return recur(0, 0, memo, dungeon);
    }

    private int recur(int i, int j, int[][] memo, int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        
        // Out-of-bounds Base Case: Return MAX_VALUE so Math.min() ignores invalid paths
        if (i >= n || j >= m) {
            return memo[i][j] = Integer.MAX_VALUE;
        }

        // Target Base Case: Reached the princess at cell (n-1, m-1)
        if (i == n - 1 && j == m - 1) {
            if (dungeon[i][j] <= 0) {
                // Takes health: Need enough HP to survive damage and stay at 1 HP
                memo[i][j] = Math.abs(dungeon[i][j]) + 1;
            } else {
                // Gives health: Need only 1 HP to enter this cell safely
                memo[i][j] = 1;
            }
            return memo[i][j];
        }

        // Return cached result if already computed for cell (i, j)
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Recursively compute minimum HP needed after moving down or right
        int down = recur(i + 1, j, memo, dungeon);
        int right = recur(i, j + 1, memo, dungeon);

        // Required HP to enter cell (i, j) to survive the optimal future path
        int res = Math.min(down, right) - dungeon[i][j];

        if (res <= 0) {
            // Cell (i, j) gives more health than required for future steps.
            // Knight must still enter with at least 1 HP to stay alive.
            return memo[i][j] = 1;
        } else {
            // Health needed to absorb cell (i, j)'s damage or cover future deficits
            return memo[i][j] = res;
        }
    }
}