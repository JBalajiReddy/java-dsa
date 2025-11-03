class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int[] r : dp)
            Arrays.fill(r, -101);
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++)
            min = Math.min(min, solve(0, j, n, matrix, dp));
        return min;
    }

    private int solve(int i, int j, int n, int[][] m, int[][] dp) {
        if (j < 0 || j >= n) {
            return (int) Math.pow(10, 9);
        }
        if (i == n - 1)
            return m[i][j];

        if (dp[i][j] != -101)
            return dp[i][j];
            
        int d = m[i][j] + solve(i + 1, j, n, m, dp);
        int dr = m[i][j] + solve(i + 1, j + 1, n, m, dp);
        int dl = m[i][j] + solve(i + 1, j - 1, n, m, dp);

        return dp[i][j] = Math.min(d, Math.min(dr, dl));
    }
}