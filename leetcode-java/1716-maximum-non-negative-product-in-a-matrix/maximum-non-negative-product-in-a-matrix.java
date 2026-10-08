class Solution {

    class Pair {
        long maxVal;
        long minVal;

        Pair(long maxVal, long minVal) {
            this.maxVal = maxVal;
            this.minVal = minVal;
        }
    }

    private int n, m;
    private int MOD = 1_000_000_007;

    public int maxProductPath(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        Pair[][] memo = new Pair[n][m];

        Pair res = recur(grid, 0, 0, memo);
        
        // Return maxVal % MOD if maxVal is non-negative, else -1
        return (res.maxVal >= 0) ? (int) (res.maxVal % MOD) : -1;
    }

    private Pair recur(int[][] grid, int i, int j, Pair[][] memo) {
        // Base Case: Bottom-Right Target Cell
        if (i == n - 1 && j == m - 1) {
            return memo[i][j] = new Pair(grid[i][j], grid[i][j]);
        }

        // Memoization Check
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        long maxVal = Long.MIN_VALUE;
        long minVal = Long.MAX_VALUE;

        // Choice 1: Move Down
        if (i + 1 < n) {
            Pair pathDown = recur(grid, i + 1, j, memo);
            long v1 = grid[i][j] * pathDown.maxVal;
            long v2 = grid[i][j] * pathDown.minVal;
            
            maxVal = Math.max(maxVal, Math.max(v1, v2));
            minVal = Math.min(minVal, Math.min(v1, v2));
        }

        // Choice 2: Move Right
        if (j + 1 < m) {
            Pair pathRight = recur(grid, i, j + 1, memo);
            long v1 = grid[i][j] * pathRight.maxVal;
            long v2 = grid[i][j] * pathRight.minVal;
            
            maxVal = Math.max(maxVal, Math.max(v1, v2));
            minVal = Math.min(minVal, Math.min(v1, v2));
        }

        return memo[i][j] = new Pair(maxVal, minVal);
    }
}