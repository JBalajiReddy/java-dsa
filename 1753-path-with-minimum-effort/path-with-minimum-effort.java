class Tuple {
    int diff, r, c;

    Tuple(int diff, int r, int c) {
        this.diff = diff;
        this.r = r;
        this.c = c;
    }
}

class Solution {
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = (int) (1e9);
            }
        }

        dist[0][0] = 0;
        PriorityQueue<Tuple> pq = new PriorityQueue<>((a, b) -> a.diff - b.diff);
        pq.offer(new Tuple(0, 0, 0));

        int[] dR = { -1, 0, 1, 0 };
        int[] dC = { 0, 1, 0, -1 };

        while (!pq.isEmpty()) {
            Tuple t = pq.poll();
            int diff = t.diff;
            int r = t.r;
            int c = t.c;

            if (r == n - 1 && c == m - 1)
                return diff; //pq stores min diff of max diff so this is min of all efforts

            for (int i = 0; i < 4; i++) {
                int nR = r + dR[i];
                int nC = c + dC[i];
                if (nR >= 0 && nR < n && nC >= 0 && nC < m) {
                    int maxEffort = Math.max(
                            Math.abs(heights[r][c] - heights[nR][nC]), diff);
                    if (maxEffort < dist[nR][nC]) {
                        dist[nR][nC] = maxEffort;
                        pq.offer(new Tuple(maxEffort, nR, nC));
                    }
                }
            }
        }
        return 0;
    }
}