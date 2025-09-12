class Triple {
    int r, c, dis;

    Triple(int r, int c, int dis) {
        this.r = r;
        this.c = c;
        this.dis = dis;
    }
}

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;

        if (n == 1 && grid[0][0] == 0) return 1;

        //no need of pq to avoid logN computation, here distances are
        //increased sequntially in an order 
        Queue<Triple> q = new ArrayDeque<>();

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = (int) (1e9);
            }
        }

        dist[0][0] = 1;
        int[] dR = { -1, -1, 0, 1, 1, 1, 0, -1 };
        int[] dC = { 0, 1, 1, 1, 0, -1, -1, -1 };
        q.offer(new Triple(0, 0, 1));

        while (!q.isEmpty()) {
            Triple t = q.poll();
            int row = t.r;
            int col = t.c;
            int dis = t.dis;

            for (int i = 0; i < 8; i++) {
                int nR = row + dR[i];
                int nC = col + dC[i];

                if (nR >= 0 && nR < n && nC >= 0 && nC < n && grid[nR][nC] == 0 && dis + 1 < dist[nR][nC]) {
                    if (nR == n - 1 && nC == n - 1 && grid[nR][nC] == 0) {
                        return dis + 1;
                    }
                    dist[nR][nC] = dis + 1;
                    q.offer(new Triple(nR, nC, dist[nR][nC]));
                }
            }
        }

        return -1;
    }
}