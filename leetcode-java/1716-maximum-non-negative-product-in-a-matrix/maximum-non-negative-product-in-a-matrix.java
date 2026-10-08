class Solution {

    class Pair {
        long maxVal;
        long minVal;

        Pair(long maxVal, long minVal) {
            this.maxVal = maxVal;
            this.minVal = minVal;
        }
    }

    public int maxProductPath(int[][] grid) {
        int MOD = 1_000_000_007;
        int n = grid.length, m = grid[0].length;

        Pair[][] dp = new Pair[n][m]; //tracks {maxProd, minProd} from (0, 0) to (i, j)
        dp[0][0] = new Pair(grid[0][0], grid[0][0]);
        for (int row = 1; row < n; row++) {
            Pair prevRow = dp[row - 1][0];
            long maxVal = prevRow.maxVal;
            long minVal = prevRow.minVal;
            dp[row][0] = new Pair(grid[row][0] * maxVal, grid[row][0] * minVal);
        }

        for (int col = 1; col < m; col++) {
            Pair prevCol = dp[0][col - 1];
            long maxVal = prevCol.maxVal;
            long minVal = prevCol.minVal;
            dp[0][col] = new Pair(grid[0][col] * maxVal, grid[0][col] * minVal);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                Pair prevRow = dp[i - 1][j];
                Pair prevCol = dp[i][j - 1];
                long maxInPrevRow = prevRow.maxVal;
                long minInPrevRow = prevRow.minVal;
                long maxInPrevCol = prevCol.maxVal;
                long minInPrevCol = prevCol.minVal;

                long newMax = Math.max(grid[i][j] * maxInPrevRow, Math.max(grid[i][j] * minInPrevRow,
                        Math.max(grid[i][j] * maxInPrevCol, grid[i][j] * minInPrevCol)));

                long newMin = Math.min(grid[i][j] * maxInPrevRow, Math.min(grid[i][j] * minInPrevRow,
                        Math.min(grid[i][j] * maxInPrevCol, grid[i][j] * minInPrevCol)));

                dp[i][j] = new Pair(newMax, newMin);
            }
        }

        long res = dp[n - 1][m - 1].maxVal % MOD;

        return res >= 0 ? (int) res % MOD : -1;
    }
}

class Solution_TOPDOWN {

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