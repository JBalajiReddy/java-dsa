public class Solution {
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public boolean containsCycle(char[][] grid) {
        if (grid == null || grid.length == 0) return false;
        
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visit = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    if (dfs(grid, visit, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, boolean[][] visit, int r, int c, int pr, int pc, char val) {
        visit[r][c] = true;

        for (int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // Explicit boundary checks
            if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid[0].length) {
                continue;
            }

            // Ignore the parent cell we just came from
            if (nr == pr && nc == pc) {
                continue;
            }

            // If the neighbor has the same value
            if (grid[nr][nc] == val) {
                // If it's already visited, a cycle is found
                if (visit[nr][nc]) {
                    return true;
                }
                // Continue DFS on the unvisited neighbor
                if (dfs(grid, visit, nr, nc, r, c, val)) {
                    return true;
                }
            }
        }

        return false;
    }
}