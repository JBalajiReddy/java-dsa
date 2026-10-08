class Solution {
    public int maxProductPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int MOD = 1_000_000_007;

        // Tracks maximum and minimum path products to reach cell (i, j)
        long[][] maxDp = new long[n][m];
        long[][] minDp = new long[n][m];

        // Base Case: Origin cell (0, 0)
        maxDp[0][0] = grid[0][0];
        minDp[0][0] = grid[0][0];

        // Base Case: First Column (can only move Down from cell above)
        for (int i = 1; i < n; i++) {
            maxDp[i][0] = maxDp[i - 1][0] * grid[i][0];
            minDp[i][0] = minDp[i - 1][0] * grid[i][0];
        }

        // Base Case: First Row (can only move Right from cell to left)
        for (int j = 1; j < m; j++) {
            maxDp[0][j] = maxDp[0][j - 1] * grid[0][j];
            minDp[0][j] = minDp[0][j - 1] * grid[0][j];
        }

        // Fill remaining inner cells
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                long curr = grid[i][j];

                // Potential products coming from the TOP neighbor (i-1, j)
                long top1 = maxDp[i - 1][j] * curr;
                long top2 = minDp[i - 1][j] * curr;

                // Potential products coming from the LEFT neighbor (i, j-1)
                long left1 = maxDp[i][j - 1] * curr;
                long left2 = minDp[i][j - 1] * curr;

                // Find the new max and min among all 4 candidate products
                maxDp[i][j] = Math.max(Math.max(top1, top2), Math.max(left1, left2));
                minDp[i][j] = Math.min(Math.min(top1, top2), Math.min(left1, left2));
            }
        }

        long maxResult = maxDp[n - 1][m - 1];

        // Return maxResult % MOD if non-negative, else -1
        return maxResult >= 0 ? (int) (maxResult % MOD) : -1;
    }
}

class Solution_UsingPairObject {

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

        return res >= 0 ? (int) res % MOD : -1; //Perform modulo on `long` first, then cast to `int`
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