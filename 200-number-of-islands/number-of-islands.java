class Solution {
    class Pair {
        int r, c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];
        int cnt = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (visited[row][col] == 0 && grid[row][col] == '1') {
                    cnt++;
                    bfs(row, col, visited, grid);
                }
            }
        }
        return cnt;
    }

    private void bfs(int row, int col, int[][] vis, char[][] grid) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(row, col));
        vis[row][col] = 1;
        int n = grid.length, m = grid[0].length;

        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int ro = pair.r;
            int co = pair.c;

            for (int i = 0; i < 4; i++) {
                int nRow = ro + dRow[i];
                int nCol = co + dCol[i];

                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && vis[nRow][nCol] == 0
                        && grid[nRow][nCol] == '1') {
                    vis[nRow][nCol] = 1;
                    q.offer(new Pair(nRow, nCol));
                }
            }
        }
    }
}