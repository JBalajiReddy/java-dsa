// ==========================================
// APPROACH 1: Iterative Matrix Traversal
// ==========================================
public class Solution {
    public int islandPerimeter(int[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;

        // Iterate through every cell in the m x n grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Process only land cells
                if (grid[i][j] == 1) {
                    
                    // Check DOWN neighbor:
                    // Add 1 if bottom edge is at grid boundary OR adjacent cell is water
                    res += (i + 1 >= m || grid[i + 1][j] == 0) ? 1 : 0;

                    // Check RIGHT neighbor:
                    // Add 1 if right edge is at grid boundary OR adjacent cell is water
                    res += (j + 1 >= n || grid[i][j + 1] == 0) ? 1 : 0;

                    // Check UP neighbor:
                    // Add 1 if top edge is at grid boundary OR adjacent cell is water
                    res += (i - 1 < 0 || grid[i - 1][j] == 0) ? 1 : 0;

                    // Check LEFT neighbor:
                    // Add 1 if left edge is at grid boundary OR adjacent cell is water
                    res += (j - 1 < 0 || grid[i][j - 1] == 0) ? 1 : 0;
                }
            }
        }
        
        return res;
    }
}

// ==========================================
// APPROACH 2: Depth-First Search (DFS)
// ==========================================
class Solution_DFS {
    private int r, c;

    public int islandPerimeter(int[][] grid) {
        r = grid.length;
        c = grid[0].length;
        
        // Track visited land cells to prevent infinite recursion
        boolean[][] vis = new boolean[r][c];

        // Locate the first land cell to trigger DFS traversal
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    // Problem guarantees exactly one connected island, 
                    // so a single DFS call computes the entire perimeter
                    return dfs(i, j, grid, vis);
                }
            }
        }

        return 0;
    }

    private int dfs(int i, int j, int[][] g, boolean[][] vis) {
        // BASE CASE 1: Boundary or Water
        // Stepping out of bounds OR onto water means we crossed a land edge -> contributes 1 to perimeter
        if (i >= r || j >= c || i < 0 || j < 0 || g[i][j] == 0) {
            return 1;
        }

        // BASE CASE 2: Already Visited Land
        // Returning to previously visited land means this is a shared inner boundary -> contributes 0 to perimeter
        if ((vis[i][j] == true)) {
            return 0;
        }

        // Mark current land cell as visited
        vis[i][j] = true;

        // Recursively explore 4 directions (DOWN, RIGHT, UP, LEFT) and aggregate perimeter values
        return dfs(i + 1, j, g, vis) + 
               dfs(i, j + 1, g, vis) + 
               dfs(i - 1, j, g, vis) + 
               dfs(i, j - 1, g, vis);
    }
}

// ==========================================
// APPROACH 3: Breadth-First Search (BFS)
// ==========================================
class Solution_BFS {
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // Direction vectors for moving: Right, Down, Left, Up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Scan matrix to find the starting land cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    Queue<int[]> queue = new LinkedList<>();
                    
                    // Initialize BFS queue with starting cell coordinates
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    int perimeter = 0;

                    // Traverse land cells layer-by-layer
                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        int x = cell[0], y = cell[1];

                        // Examine all 4 adjacent neighbors
                        for (int[] dir : directions) {
                            int nx = x + dir[0], ny = y + dir[1];

                            // Check if neighbor is Out-of-Bounds OR Water
                            if (nx < 0 || ny < 0 || nx >= rows ||
                                ny >= cols || grid[nx][ny] == 0) {
                                // Crossing to water or boundary adds 1 to perimeter
                                perimeter++;
                            } 
                            // If neighbor is Unvisited Land, enqueue for exploration
                            else if (!visited[nx][ny]) {
                                visited[nx][ny] = true;
                                queue.offer(new int[]{nx, ny});
                            }
                        }
                    }
                    
                    // Single connected island completely processed
                    return perimeter;
                }
            }
        }
        
        return 0;
    }
}