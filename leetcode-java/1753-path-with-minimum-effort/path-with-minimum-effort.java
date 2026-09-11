class Solution {
    public int minimumEffortPath(int[][] heights) {
        int ROWS = heights.length, COLS = heights[0].length;
        
        // Flatten 2D matrix indices into a 1D distance array.
        // dist[u] stores the minimum path effort required to reach node u from source (0).
        int[] dist = new int[ROWS * COLS];
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize distances to infinity
        dist[0] = 0;                          // Starting cell (0, 0) requires 0 effort

        // SPFA (Shortest Path Faster Algorithm) tracking array.
        // inQueue[v] tracks whether node v is currently inside the BFS queue,
        // preventing redundant insertions and duplicate processing.
        boolean[] inQueue = new boolean[ROWS * COLS];
        
        // Standard FIFO queue used for level-by-level relaxation (modified Bellman-Ford/SPFA)
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);      // Push source node (0, 0)
        inQueue[0] = true;   // Mark source as present in queue

        // 4-directional movements: RIGHT, LEFT, DOWN, UP
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        while (!queue.isEmpty()) {
            // Pop the current node u from queue
            int u = queue.poll();
            inQueue[u] = false; // Mark u as no longer inside the queue

            // Decode 1D index u back into 2D grid coordinates (r, c)
            int r = u / COLS, c = u % COLS;

            // Explore all 4 adjacent neighbors
            for (int[] dir : directions) {
                int newR = r + dir[0], newC = c + dir[1];
                
                // Boundary check: ensure neighbor cell is inside grid limits
                if (newR >= 0 && newC >= 0 && newR < ROWS && newC < COLS) {
                    // Encode neighbor 2D coordinates (newR, newC) into 1D node ID v
                    int v = newR * COLS + newC;
                    
                    // Height difference between current cell and neighbor cell
                    int weight = Math.abs(heights[r][c] - heights[newR][newC]);
                    
                    // Path effort metric: bottleneck/maximum edge difference encountered so far
                    int newDist = Math.max(dist[u], weight);
                    
                    // Relaxation step: if passing through u yields a strictly lower path effort to v
                    if (newDist < dist[v]) {
                        dist[v] = newDist; // Update minimum effort to reach v
                        
                        // Push v into the queue only if it's not already queued for processing
                        if (!inQueue[v]) {
                            queue.offer(v);
                            inQueue[v] = true;
                        }
                    }
                }
            }
        }

        // Return the minimum effort calculated to reach bottom-right corner (ROWS-1, COLS-1)
        return dist[ROWS * COLS - 1];
    }
}

class Solution_Dijkastra {
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        // dist[r][c] stores the minimum effort required to reach cell (r, c) from (0, 0)
        int[][] dist = new int[n][m];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE); // Initialize all cell efforts to infinity
        }

        // Base case: starting cell requires 0 effort
        dist[0][0] = 0;

        // Min-Heap to extract the cell with the current minimum path effort
        // Element format: {row, col, current_effort_to_reach_this_cell}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[] { 0, 0, 0 }); // Start at top-left cell (0, 0) with effort 0

        // Direction vectors for moving UP, RIGHT, DOWN, LEFT
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        while (!pq.isEmpty()) {
            int[] currState = pq.poll();
            int r = currState[0];
            int c = currState[1];
            int diff = currState[2];

            // Target reached! Because nodes are processed in ascending order of effort via Min-Heap,
            // the first time we pop the destination cell (n - 1, m - 1), it is guaranteed to be optimal.
            if (r == n - 1 && c == m - 1) {
                return diff;
            }

            // Stale entry check: if we already found a path with lower effort to cell (r, c), skip this older entry
            if (diff > dist[r][c]) {
                continue;
            }

            // Explore all 4 adjacent neighboring cells
            for (int i = 0; i < 4; i++) {
                int nR = r + dir[i][0];
                int nC = c + dir[i][1];

                // Boundary check: skip out-of-bound grid cells
                if (nR < 0 || nR >= n || nC < 0 || nC >= m) {
                    continue;
                }

                // Path effort metric: maximum height difference encountered along the path so far
                int newDiff = Math.max(diff, Math.abs(heights[nR][nC] - heights[r][c]));

                // Relaxation step: if passing through cell (r, c) offers a lower overall effort to reach (nR, nC)
                if (newDiff < dist[nR][nC]) {
                    dist[nR][nC] = newDiff;
                    pq.offer(new int[] { nR, nC, newDiff });
                }
            }
        }

        return 0; // Default return for unreachable target (grid always has at least 1 cell)
    }
}