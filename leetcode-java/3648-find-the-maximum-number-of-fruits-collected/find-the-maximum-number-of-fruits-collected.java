//Approach-1 - (Recursion + Memoization)
//T.C : O(n^2)
//S.C : O(n^2)
class Solution_TopDown {
    int n;
    int[][] t;

    // Can only move diagonally
    private int child1Collect(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += grid[i][i];
            grid[i][i] = 0;
            t[i][i] = 0;
        }
        return ans;
    }

    private int child2Collect(int i, int j, int[][] grid) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            return 0;
        }
        if (i == n - 1 && j == n - 1) {
            return 0;
        }

        // can't go beyond diagonal or left to diagonal (only have n-1 moves)
        if (i == j || i > j) {
            return 0;
        }

        if (t[i][j] != -1)
            return t[i][j];

        int leftcorner = grid[i][j] + child2Collect(i + 1, j - 1, grid);
        int middle = grid[i][j] + child2Collect(i + 1, j, grid);
        int rightcorner = grid[i][j] + child2Collect(i + 1, j + 1, grid);

        return t[i][j] = Math.max(middle, Math.max(rightcorner, leftcorner));
    }

    private int child3Collect(int i, int j, int[][] grid) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            return 0;
        }
        if (i == n - 1 && j == n - 1) {
            return 0;
        }

        // can't go beyond diagonal or right to diagonal (only have n-1 moves)
        if (i == j || j > i) {
            return 0;
        }
        if (t[i][j] != -1)
            return t[i][j];

        int topcorner = grid[i][j] + child3Collect(i - 1, j + 1, grid);
        int right = grid[i][j] + child3Collect(i, j + 1, grid);
        int rightcorner = grid[i][j] + child3Collect(i + 1, j + 1, grid);

        return t[i][j] = Math.max(right, Math.max(rightcorner, topcorner));
    }

    public int maxCollectedFruits(int[][] grid) {
        n = grid.length;
        t = new int[n][n];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }

        // First child
        int firstChildScore = child1Collect(grid);

        // Second child
        int secondChildScore = child2Collect(0, n - 1, grid);

        // Third child
        int thirdChildScore = child3Collect(n - 1, 0, grid);

        return (firstChildScore + secondChildScore + thirdChildScore);
    }
}

//Approach-2 - (Bottom Up)
//T.C : O(n^2)
//S.C : O(n^2)
class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        
        // t[i][j] stores the maximum fruits collected to reach cell [i][j]
        int[][] t = new int[n][n];

        // ------------------------------------------------------------------
        // Phase 1: Child 1 collects all diagonal elements
        // Child 1 moves strictly along (0,0) -> (1,1) -> ... -> (n-1, n-1)
        // ------------------------------------------------------------------
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += fruits[i][i];
        }

        // ------------------------------------------------------------------
        // Phase 2: Initialize DP Table and Filter Unreachable Cells
        // Unreachable condition (i + j < n - 1):
        // Children only have (n - 1) total moves, so stepping too far away
        // from the destination (n-1, n-1) prevents them from reaching it in time.
        // ------------------------------------------------------------------
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j && i + j < n - 1) {
                    // Unreachable cells for Child 2 (Upper Triangle)
                    t[i][j] = 0; 
                } else if (i > j && i + j < n - 1) {
                    // Unreachable cells for Child 3 (Lower Triangle)
                    t[i][j] = 0; 
                } else {
                    // Base fruit value available at cell [i][j]
                    t[i][j] = fruits[i][j]; 
                }
            }
        }

        // ------------------------------------------------------------------
        // Phase 3: Child 2 DP (Upper Triangle: i < j)
        // Moves row by row from top to bottom (row 1 to n-1).
        // Can reach [i][j] from [i-1][j-1], [i-1][j], or [i-1][j+1].
        // ------------------------------------------------------------------
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Fetch best path from the 3 valid preceding cells in row (i - 1)
                int prevTopLeft  = t[i - 1][j - 1];
                int prevTop      = t[i - 1][j];
                int prevTopRight = (j + 1 < n) ? t[i - 1][j + 1] : 0; // Handle grid boundary

                int maxPrevPath = Math.max(prevTopLeft, Math.max(prevTop, prevTopRight));
                
                // Accumulate best path onto current cell's base fruit value
                t[i][j] += maxPrevPath;
            }
        }

        // ------------------------------------------------------------------
        // Phase 4: Child 3 DP (Lower Triangle: i > j)
        // Moves column by column from left to right (col 1 to n-1).
        // Can reach [i][j] from [i-1][j-1], [i][j-1], or [i+1][j-1].
        // ------------------------------------------------------------------
        for (int j = 1; j < n; j++) {
            for (int i = j + 1; i < n; i++) {
                // Fetch best path from the 3 valid preceding cells in column (j - 1)
                int prevTopLeft    = t[i - 1][j - 1];
                int prevLeft       = t[i][j - 1];
                int prevBottomLeft = (i + 1 < n) ? t[i + 1][j - 1] : 0; // Handle grid boundary

                int maxPrevPath = Math.max(prevTopLeft, Math.max(prevLeft, prevBottomLeft));

                // Accumulate best path onto current cell's base fruit value
                t[i][j] += maxPrevPath;
            }
        }

        // ------------------------------------------------------------------
        // Final Result Calculation:
        // Child 1's total (result) +
        // Child 2's final step right before destination (t[n-2][n-1]) +
        // Child 3's final step right before destination (t[n-1][n-2])
        // ------------------------------------------------------------------
        return result + t[n - 2][n - 1] + t[n - 1][n - 2];
    }
}