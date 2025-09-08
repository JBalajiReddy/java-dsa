class Solution {

    private void dfs(int r, int c, int[][] g, int[][] vis, int[] delRow, int[] delCol) {
        vis[r][c] = 1;

        int n = g.length, m = g[0].length;
        for (int i = 0; i < 4; i++) {
            int nR = r + delRow[i];
            int nC = c + delCol[i];
            if (nR >= 0 && nR < n && nC >= 0 && nC < m && vis[nR][nC] == 0 && g[nR][nC] == 1) {
                dfs(nR, nC, g, vis, delRow, delCol);
            }
        }
    }

    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];

        int[] delRow = { -1, 0, +1, 0 };
        int[] delCol = { 0, +1, 0, -1 };
        for (int j = 0; j < m; j++) {
            if (vis[0][j] == 0 && grid[0][j] == 1)
                dfs(0, j, grid, vis, delRow, delCol);
            if (vis[n - 1][j] == 0 && grid[n - 1][j] == 1)
                dfs(n - 1, j, grid, vis, delRow, delCol);
        }

        for (int i = 0; i < n; i++) {
            if (vis[i][0] == 0 && grid[i][0] == 1)
                dfs(i, 0, grid, vis, delRow, delCol);
            if (vis[i][m - 1] == 0 && grid[i][m - 1] == 1)
                dfs(i, m - 1, grid, vis, delRow, delCol);
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0 && grid[i][j] == 1)
                    cnt++;
            }
        }
        return cnt;
    }
}