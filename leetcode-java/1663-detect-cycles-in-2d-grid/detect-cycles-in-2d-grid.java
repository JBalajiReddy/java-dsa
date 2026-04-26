public class Solution {
    int[] parent;
    public boolean containsCycle(char[][] grid) {
        if (grid == null || grid.length == 0) return false;
        
        int m = grid.length;
        int n = grid[0].length;
        parent = new int[m * n];
        
        // Initialize DSU: Every node is its own parent initially
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }
        
        // We only check Right and Down to prevent traversing the same undirected edge twice
        int[][] dirs = {{0, 1}, {1, 0}};
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                
                // Check valid forward neighbors
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    // If neighbor is within bounds and has the same character
                    if (nr < m && nc < n && grid[nr][nc] == grid[r][c]) {
                        
                        // Map 2D coordinates to 1D parent array indices
                        int currentIdx = r * n + c;
                        int neighborIdx = nr * n + nc;
                        
                        // Find the roots of both cells
                        int root1 = find(currentIdx);
                        int root2 = find(neighborIdx);
                        
                        // If they share the same root, they are already connected -> CYCLE!
                        if (root1 == root2) {
                            return true;
                        } else {
                            // Otherwise, Union the two sets
                            parent[root1] = root2;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    // Find operation with Path Compression
    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        // Path compression: update the parent to the ultimate root
        return parent[i] = find(parent[i]); 
    }
}

// class Solution {
//     private int n, m;
//     private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
//     private boolean[][] vis;
//     public boolean containsCycle(char[][] grid) {
//         n = grid.length;
//         m = grid[0].length;
//         vis = new boolean[n][m];
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 if (!vis[i][j]) {
//                     if (dfs(grid, i, j, -1, -1, grid[i][j])) {
//                         return true;
//                     }
//                 }
//             }
//         }
//         return false;
//     }
//     private boolean dfs(char[][] grid, int r, int c, int pr, int pc, char val) {
//         vis[r][c] = true;
//         for (int[] d : dirs) {
//             int nr = d[0] + r;
//             int nc = d[1] + c;
//             if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
//                 continue;
//             }
//             if (nr == pr && nc == pc) {
//                 continue;
//             }
//             if (grid[nr][nc] == val) {
//                 if (vis[nr][nc]) {
//                     return true;
//                 }
//                 if (dfs(grid, nr, nc, r, c, val)) {
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }
// }