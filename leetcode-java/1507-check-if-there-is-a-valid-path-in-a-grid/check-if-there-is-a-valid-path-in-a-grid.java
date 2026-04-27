class Solution {

    // Grid dimensions
    int m, n;

    // Maps a pipe type (1-6) to its possible movement vectors: {row_change, col_change}
    Map<Integer, int[][]> directions = new HashMap<>();

    public Solution() {
        // Initialize the valid outgoing directions for each pipe shape
        // (0, -1) is Left, (0, 1) is Right, (-1, 0) is Up, (1, 0) is Down
        directions.put(1, new int[][]{{0, -1}, {0, 1}});   // Horizontal Pipe: Left, Right
        directions.put(2, new int[][]{{-1, 0}, {1, 0}});   // Vertical Pipe: Up, Down
        directions.put(3, new int[][]{{0, -1}, {1, 0}});   // Corner Pipe: Left, Down
        directions.put(4, new int[][]{{0, 1}, {1, 0}});    // Corner Pipe: Right, Down
        directions.put(5, new int[][]{{0, -1}, {-1, 0}});  // Corner Pipe: Left, Up
        directions.put(6, new int[][]{{-1, 0}, {0, 1}});   // Corner Pipe: Up, Right
    }

    public boolean dfs(int[][] grid, int i, int j, boolean[][] visited) {
        // Base Case: If we successfully reach the bottom-right cell, the path is valid
        if (i == m - 1 && j == n - 1) {
            return true;
        }

        // Mark the current cell as visited to prevent infinite loops (cycles)
        visited[i][j] = true;

        // Get the current pipe shape, and iterate through its valid outgoing directions
        for (int[] dir : directions.get(grid[i][j])) {
            int new_i = i + dir[0];
            int new_j = j + dir[1];

            // 1. Boundary Check: Ensure the new coordinates are within the grid
            // 2. Visited Check: Ensure we haven't already processed this cell
            if (new_i < 0 || new_i >= m || new_j < 0 || new_j >= n || visited[new_i][new_j]) {
                continue;
            }

            // The "Two-Way Handshake" Check:
            // It is not enough that our current pipe points to the new cell.
            // The pipe in the new cell MUST have an opening pointing exactly back to our current cell.
            for (int[] backDir : directions.get(grid[new_i][new_j])) {
                
                // If applying an outgoing direction from the NEXT cell brings us back to our current (i, j)...
                if (new_i + backDir[0] == i && new_j + backDir[1] == j) {
                    
                    // The pipes connect! Recursively continue the DFS from the new cell.
                    if (dfs(grid, new_i, new_j, visited)) {
                        return true; // If any branch reaches the end, propagate the success upward
                    }
                }
            }
        }

        // If all possible directions are explored and we haven't reached the end, this path fails
        return false;
    }

    public boolean hasValidPath(int[][] grid) {
        // Set grid dimensions globally for use in the DFS
        m = grid.length;
        n = grid[0].length;

        // Track visited cells to avoid getting stuck in loops
        boolean[][] visited = new boolean[m][n];

        // Start the Depth-First Search from the top-left corner (0, 0)
        return dfs(grid, 0, 0, visited);
    }
}