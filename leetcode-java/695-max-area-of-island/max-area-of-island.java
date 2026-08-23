class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int area = 0;
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && grid[i][j] == 1) {
                    area = Math.max(area, dfs(i, j, grid, vis));
                }
            }
        }
        return area;
    }

    private int dfs(int i, int j, int[][] g, boolean[][] vis) {
        int n = g.length, m = g[0].length;
        if (i < 0 || i >= n || j < 0 || j >= m || g[i][j] == 0 || vis[i][j]) {
            return 0;
        }

        vis[i][j] = true;

        return 1 + dfs(i, j + 1, g, vis) + dfs(i + 1, j, g, vis) + dfs(i, j - 1, g, vis) + dfs(i - 1, j, g, vis);
    }
}