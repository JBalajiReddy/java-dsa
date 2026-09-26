class Solution {
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length, m = heights[0].length;

        // Direction vectors for moving UP, RIGHT, DOWN, LEFT
        int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        // dist[r][c] stores the minimum effort required to reach cell (r, c)
        int[][] dist = new int[n][m];
        for (int[] d : dist) {
            Arrays.fill(d, (int) 1e9); // Initialize distances to infinity
        }

        // Min-Heap Priority Queue storing triples: {effort, row, col}
        // Min-heap ordering ensures we always expand the path with the smallest effort first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Starting at cell (0, 0) costs 0 effort
        pq.offer(new int[] { 0, 0, 0 });
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] s = pq.poll();
            int effort = s[0], r = s[1], c = s[2];

            // Reached destination: since Dijkstra guarantees minimum cost on first pop, return immediately
            if (r == n - 1 && c == m - 1) {
                return effort;
            }

            // Skip stale nodes (if we found a lower effort path to this cell already)
            if (effort > dist[r][c]) {
                continue;
            }

            // Explore all 4 adjacent neighbors
            for (int[] d : dirs) {
                int nR = d[0] + r, nC = d[1] + c;

                // Check if neighbor cell is within grid boundaries
                if (nR >= 0 && nR < n && nC >= 0 && nC < m) {
                    // Height difference between the current cell and the neighbor
                    int stepEffort = Math.abs(heights[r][c] - heights[nR][nC]);

                    // The path effort to reach neighbor is the max effort along this path so far
                    int newEffort = Math.max(effort, stepEffort);

                    // Relaxation step: update neighbor distance if this path offers a smaller max effort
                    if (newEffort < dist[nR][nC]) {
                        dist[nR][nC] = newEffort;
                        pq.offer(new int[] { newEffort, nR, nC });
                    }
                }
            }
        }

        return 0; // Fallback return (unreachable in valid grids)
    }
}

class Solution_BinarySearch {
    private int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public int minimumEffortPath(int[][] heights) {
        int left = 0;
        int right = 1000000; // Maximum possible height difference
        int ans = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if a path from (0,0) to (n-1, m-1) exists with effort <= mid
            if (canReachDestination(heights, mid)) {
                ans = mid;
                right = mid - 1; // Try finding a smaller effort
            } else {
                left = mid + 1; // Increase effort limit
            }
        }

        return ans;
    }

    // BFS feasibility check
    private boolean canReachDestination(int[][] heights, int maxEffort) {
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] vis = new boolean[n][m];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0 });
        vis[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];

            // Reached bottom-right corner
            if (r == n - 1 && c == m - 1) {
                return true;
            }

            for (int[] d : dirs) {
                int nR = r + d[0];
                int nC = c + d[1];

                // Check bounds and whether neighbor is unvisited
                if (nR >= 0 && nR < n && nC >= 0 && nC < m && !vis[nR][nC]) {
                    int currentStepEffort = Math.abs(heights[r][c] - heights[nR][nC]);

                    // Only traverse if the step effort is within our target threshold
                    if (currentStepEffort <= maxEffort) {
                        vis[nR][nC] = true;
                        queue.offer(new int[] { nR, nC });
                    }
                }
            }
        }

        return false;
    }
}