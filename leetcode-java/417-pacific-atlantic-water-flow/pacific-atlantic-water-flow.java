/**
 * Approach 1: Reverse Multi-Source DFS (Optimal)
 * Strategy: Work backwards from the ocean borders, moving uphill (heights[nr][nc] >= heights[r][c]).
 * Time Complexity: O(M * N) - Each cell is visited at most twice (once per ocean).
 * Space Complexity: O(M * N) - For visited matrices and implicit call stack.
 */
class Solution {
    // Directions for moving up, down, left, right
    private final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int m = heights.length;
        int n = heights[0].length;

        // Track reachable cells for each ocean separately
        boolean[][] pacificVisited = new boolean[m][n];
        boolean[][] atlanticVisited = new boolean[m][n];

        // Step 1: Start DFS from vertical borders (Left = Pacific, Right = Atlantic)
        for (int i = 0; i < m; i++) {
            dfs(heights, pacificVisited, i, 0, Integer.MIN_VALUE); // Left column
            dfs(heights, atlanticVisited, i, n - 1, Integer.MIN_VALUE); // Right column
        }

        // Step 2: Start DFS from horizontal borders (Top = Pacific, Bottom = Atlantic)
        for (int j = 0; j < n; j++) {
            dfs(heights, pacificVisited, 0, j, Integer.MIN_VALUE); // Top row
            dfs(heights, atlanticVisited, m - 1, j, Integer.MIN_VALUE); // Bottom row
        }

        // Step 3: Intersection - find cells reachable by both oceans
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] visited, int i, int j, int prevHeight) {
        int m = heights.length;
        int n = heights[0].length;

        // Base cases: Out of bounds, already processed, or invalid uphill move (cell must be >= previous)
        if (i < 0 || i >= m || j < 0 || j >= n
                || visited[i][j] || heights[i][j] < prevHeight) {
            return;
        }

        // Mark cell as visited for the current ocean stream
        visited[i][j] = true;

        // Explore all 4 adjacent directional neighbors recursively
        for (int[] dir : directions) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            dfs(heights, visited, ni, nj, heights[i][j]);
        }
    }
}

/**
 * Approach 2: Reverse Multi-Source BFS (Optimal)
 * Strategy: Seed queues with all ocean border coordinates and expand level-by-level uphill.
 * Time Complexity: O(M * N)
 * Space Complexity: O(M * N) - Explicit BFS queues can store up to O(M * N) elements.
 */
class Solution_BFS {
    private int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int ROWS = heights.length, COLS = heights[0].length;
        boolean[][] pac = new boolean[ROWS][COLS];
        boolean[][] atl = new boolean[ROWS][COLS];

        // Multi-source BFS queues to seed initial ocean border points
        Queue<int[]> pacQueue = new LinkedList<>();
        Queue<int[]> atlQueue = new LinkedList<>();

        // Add top/bottom horizontal border cells to corresponding queues
        for (int c = 0; c < COLS; c++) {
            pacQueue.add(new int[] { 0, c }); // Top border (Pacific)
            atlQueue.add(new int[] { ROWS - 1, c }); // Bottom border (Atlantic)
        }

        // Add left/right vertical border cells to corresponding queues
        for (int r = 0; r < ROWS; r++) {
            pacQueue.add(new int[] { r, 0 }); // Left border (Pacific)
            atlQueue.add(new int[] { r, COLS - 1 }); // Right border (Atlantic)
        }

        // Run multi-source BFS independently for both ocean seeds
        bfs(pacQueue, pac, heights);
        bfs(atlQueue, atl, heights);

        // Collect cells reachable from both queues
        List<List<Integer>> res = new ArrayList<>();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (pac[r][c] && atl[r][c]) {
                    res.add(Arrays.asList(r, c));
                }
            }
        }
        return res;
    }

    private void bfs(Queue<int[]> q, boolean[][] ocean, int[][] heights) {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            // Mark visited when dequeued/processed
            ocean[r][c] = true;

            for (int[] d : directions) {
                int nr = r + d[0], nc = c + d[1];

                // Check bounds, unvisited status, and uphill condition (heights[nr][nc] >= heights[r][c])
                if (nr >= 0 && nr < heights.length && nc >= 0 &&
                        nc < heights[0].length && !ocean[nr][nc] &&
                        heights[nr][nc] >= heights[r][c]) {

                    // Mark visit early to prevent duplicate enqueuing
                    ocean[nr][nc] = true;
                    q.add(new int[] { nr, nc });
                }
            }
        }
    }
}

/**
 * Approach 3: Naive Forward Per-Cell DFS (Brute Force)
 * Strategy: Start downhill simulation from EVERY individual cell.
 * Time Complexity: O((M * N)^2) - Triggers a fresh traversal per cell (Causes TLE on LeetCode).
 * Space Complexity: O(M * N) - Allocates a new visited matrix every time.
 */
class Solution_BruteForce {
    private int[] dX = { -1, 0, 1, 0 }, dY = { 0, 1, 0, -1 };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;

        // Check reachability for EVERY individual cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Fresh matrix allocated every cell (causes high GC overhead + redundant state)
                boolean vis[][] = new boolean[m][n];

                // If bitmask reachability status equals 3 (1 | 2 = Pacific | Atlantic)
                if (getReach(heights, i, j, vis) == 3) {
                    List<Integer> rc = new ArrayList<>();
                    rc.add(i);
                    rc.add(j);
                    res.add(rc);
                }
            }
        }
        return res;
    }

    private int getReach(int[][] h, int i, int j, boolean[][] visited) {
        int m = h.length, n = h[0].length;

        // Prevent infinite loops in flat/equal elevation terrains
        if (visited[i][j])
            return 0;
        visited[i][j] = true;

        // Bitmask encoding: Bit 0 (1) = Pacific reached, Bit 1 (2) = Atlantic reached
        int status = 0;

        for (int idx = 0; idx < 4; idx++) {
            int r = i + dX[idx];
            int c = j + dY[idx];

            // Boundary check: Out of top/left bounds -> Pacific reached
            if (r < 0 || c < 0) {
                status |= 1;
            }
            // Boundary check: Out of bottom/right bounds -> Atlantic reached
            else if (r >= m || c >= n) {
                status |= 2;
            }
            // In-bounds: Move downhill (h[i][j] >= h[r][c]) and combine reachability flags
            else if (h[i][j] >= h[r][c]) {
                status |= getReach(h, r, c, visited);
            }
        }

        return status;
    }
}