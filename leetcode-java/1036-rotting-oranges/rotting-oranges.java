class Solution {
    public int orangesRotting(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        
        Queue<int[]> q = new ArrayDeque<>();
        
        int fresh = 0; 
        int mins = 0; 

        // 1. Scan the grid to initialize state:
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[] { i, j }); // Multi-source starting points
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // Edge case: If there are no fresh oranges at start, 0 minutes are needed
        if (fresh == 0) {
            return 0;
        }

        // [Right, Down, Left, Up]
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        // 2. Perform Multi-Source BFS layer-by-layer:
        //    - Stopping condition includes `fresh > 0` to avoid an extra minute increment 
        //      when the queue has nodes that don't infect any new fresh oranges.
        while (!q.isEmpty() && fresh > 0) {
            // Snapshot queue size to process all oranges rotten during the CURRENT minute
            int size = q.size();
            
            for (int k = 0; k < size; k++) {
                int[] cell = q.poll();
                int row = cell[0], col = cell[1];
                
                // Explore all 4 adjacent neighbors
                for (int i = 0; i < 4; i++) {
                    int nR = dir[i][0] + row;
                    int nC = dir[i][1] + col;
                    
                    // Check boundary limits & verify if the adjacent neighbor is fresh
                    if (nR < r && nR >= 0 && nC < c && nC >= 0 && grid[nR][nC] == 1) {
                        grid[nR][nC] = 2;
                        q.offer(new int[] { nR, nC }); 
                        fresh--; 
                    }
                }
            }
            // Increment minutes elapsed only after processing the ENTIRE current BFS layer
            mins++;
        }

        return fresh == 0 ? mins : -1;
    }
}