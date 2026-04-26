class Solution {
    private int n, m;
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private boolean[][] vis;
    public boolean containsCycle(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j]) {
                    if (dfs(grid, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] grid, int r, int c, int pr, int pc, char val) {
        vis[r][c] = true;
        for (int[] d : dirs) {
            int nr = d[0] + r;
            int nc = d[1] + c;
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                continue;
            }
            if (nr == pr && nc == pc) {
                continue;
            }
            if (grid[nr][nc] == val) {
                if (vis[nr][nc]) {
                    return true;
                }
                if (dfs(grid, nr, nc, r, c, val)) {
                    return true;
                }
            }
        }
        return false;
    }
}