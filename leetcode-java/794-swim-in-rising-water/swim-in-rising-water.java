class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;

        // Track visited cells to prevent re-processing and infinite loops
        int[][] vis = new int[n][n];

        // 4-directional movement offsets: Right, Down, Left, Up
        int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        // Min-Priority Queue stores elements as [current_max_time, row, col]
        // Ordered by current_max_time ascending so we always explore the lowest bottleneck first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Start at top-left corner (0,0) at time = grid[0][0]
        pq.offer(new int[] { grid[0][0], 0, 0 });
        vis[0][0] = 1; // Mark starting cell as visited

        while (!pq.isEmpty()) {
            // Extract the reachable cell with the minimum peak elevation so far
            int[] p = pq.poll();
            int time = p[0], r = p[1], c = p[2];

            // Reached destination (bottom-right corner)
            // Due to the Min-Heap property, the first time we pop (n-1, n-1), 'time' is optimal
            if (r == n - 1 && c == n - 1) {
                return time;
            }

            // Explore all 4 adjacent neighbors
            for (int[] d : dirs) {
                int nR = r + d[0];
                int nC = c + d[1];

                // Check grid boundaries and unvisited status
                if (nR >= 0 && nR < n && nC >= 0 && nC < n && vis[nR][nC] == 0) {
                    // Path time to neighbor is the maximum elevation encountered along this path
                    int nextTime = Math.max(time, grid[nR][nC]);

                    pq.offer(new int[] { nextTime, nR, nC });
                    vis[nR][nC] = 1; // Mark visited immediately upon queuing
                }
            }
        }

        return n * n; // Fallback return (unreachable under normal problem constraints)
    }
}
