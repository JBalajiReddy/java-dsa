class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];

        for (int[] g : guards) {
            int r = g[0], c = g[1];
            grid[r][c] = 1;
        }
        for (int[] w : walls) {
            int r = w[0], c = w[1];
            grid[r][c] = 2;
        }

        int[] dr = { -1, 0, 1, 0 };
        int[] dc = { 0, 1, 0, -1 };

        for (int[] g : guards) {
            int r = g[0], c = g[1];
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];

                while (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    if (grid[nr][nc] == 2 || grid[nr][nc] == 1)
                        break;

                    if (grid[nr][nc] == 0)
                        grid[nr][nc] = 3;

                    nr = nr + dr[i];
                    nc = nc + dc[i];
                }
            }
        }

        int unGuarded = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0)
                    unGuarded++;
            }
        }
        return unGuarded;
    }

}