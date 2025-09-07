class Solution {
    class Pair {
        int r, c, tm;
        Pair(int r, int c, int tm) {
            this.r = r;
            this.c = c;
            this.tm = tm;
        }
    }

    public int orangesRotting(int[][] grid) {
        Queue<Pair> q = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];

        int cntFresh= 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new Pair(i, j, 0));
                    vis[i][j] = 1;
                } else if (grid[i][j] == 1) {
                    cntFresh++;
                    vis[i][j] = 0;
                } else {
                    vis[i][j] = 0;
                }
            }
        }

        int time = 0, cnt = 0;
        int[] dRow = {-1, 0, +1, 0};
        int[] dCol = {0, +1, 0, -1};
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int row = pair.r;
            int col = pair.c;
            int t = pair.tm;
            time = Math.max(time, t);

            for (int i = 0; i < 4; i++) {
                int nRow = row + dRow[i];
                int nCol = col + dCol[i];
                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && vis[nRow][nCol] == 0 && grid[nRow][nCol] == 1) {
                    q.offer(new Pair(nRow, nCol, t + 1));
                    vis[nRow][nCol] = 1;
                    cnt++;
                }
            }
        }

        return cnt == cntFresh ? time : -1;
    }
}