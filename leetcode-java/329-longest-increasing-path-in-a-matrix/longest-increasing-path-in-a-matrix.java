class Solution {
    int m, n;
    int[] dX = { -1, 0, 1, 0 }, dY = { 0, 1, 0, -1 };
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        int res = 0;
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(i, j, matrix, cache));
            }
        }
        return res;
    }

    private int dfs(int i, int j, int[][] matrix, int[][] cache) {
        if (cache[i][j] != 0)
            return cache[i][j];
        for (int k = 0; k < 4; k++) {
            int x = i + dX[k], y = j + dY[k];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {
                cache[i][j] = Math.max(cache[i][j], dfs(x, y, matrix, cache));
            }
        }
        return ++cache[i][j];
    }
}