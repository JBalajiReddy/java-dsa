class Solution {

    // Direction vectors for moving Right, Down, Left, Up
    static final int[] dx = { 0, 1, 0, -1 };
    static final int[] dy = { 1, 0, -1, 0 };

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        // id[i][j] stores the bitmask flag for the 'L' target located at (i, j)
        int[][] id = new int[m][n];
        int sx = 0, sy = 0, cnt = 0;

        // ------------------------------------------------------------------
        // 1. Preprocessing: Identify Start ('S') and assign bitmasks to 'L'
        // ------------------------------------------------------------------
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'L') {
                    // Assign a unique bit (1 << cnt) to each 'L' target cell
                    id[i][j] = 1 << cnt;
                    cnt++;
                }
            }
        }

        // 'full' represents the state where all targets are collected.
        // E.g., if cnt = 3, full = 1000 in binary (8). Target mask is 111 (7 = full - 1).
        int full = 1 << cnt;

        // ------------------------------------------------------------------
        // 2. DP / State Memoization Table
        // bestEnergy[x][y][mask] stores the maximum remaining energy achieved
        // at cell (x, y) with a specific collected mask state.
        // ------------------------------------------------------------------
        int[][][] bestEnergy = new int[m][n][full];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        // Initialize start position state
        bestEnergy[sx][sy][0] = energy;

        // Helper class to encapsulate the current state in BFS queue
        class Info {
            int x, y, mask, e, steps;

            Info(int x, int y, int mask, int e, int steps) {
                this.x = x; // Row coordinate
                this.y = y; // Column coordinate
                this.mask = mask; // Bitmask of collected 'L' locations
                this.e = e; // Current remaining energy
                this.steps = steps; // Total steps taken from start
            }
        }

        // ------------------------------------------------------------------
        // 3. BFS Exploration
        // ------------------------------------------------------------------
        Deque<Info> q = new ArrayDeque<>();
        q.addLast(new Info(sx, sy, 0, energy, 0));

        while (!q.isEmpty()) {
            Info t = q.removeFirst();

            // Success Condition: All 'L' locations have been collected
            if (t.mask == full - 1) {
                return t.steps;
            }

            // Pruning: Out of energy, cannot move further
            if (t.e == 0) {
                continue;
            }

            // Explore 4 adjacent directions
            for (int d = 0; d < 4; d++) {
                int nx = t.x + dx[d];
                int ny = t.y + dy[d];

                // Boundary check & Obstacle collision check ('X')
                if (nx < 0 ||
                        nx >= m ||
                        ny < 0 ||
                        ny >= n ||
                        classroom[nx].charAt(ny) == 'X') {
                    continue;
                }

                // Energy Calculation: Reset to full capacity if 'R', else decrement by 1
                int ne = classroom[nx].charAt(ny) == 'R' ? energy : t.e - 1;

                // Bitmask Update: Combine current mask with target bit (if current cell is 'L')
                int nmask = t.mask | id[nx][ny];

                // Pruning / Optimization Check:
                // Only push to queue if this path reaches (nx, ny, nmask) with strictly GREATER energy
                // than any previously explored path.
                if (ne > bestEnergy[nx][ny][nmask]) {
                    bestEnergy[nx][ny][nmask] = ne;
                    q.addLast(new Info(nx, ny, nmask, ne, t.steps + 1));
                }
            }
        }

        // Return -1 if destination/full collection is unreachable
        return -1;
    }
}