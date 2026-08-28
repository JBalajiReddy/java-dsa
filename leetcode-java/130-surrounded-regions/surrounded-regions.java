class Solution {
    public void solve(char[][] b) {
        int r = b.length, c = b[0].length;

        // Step 1: Scan top and bottom borders
        for (int j = 0; j < c; j++) {
            if (b[0][j] == 'O') dfs(b, 0, j);       // Top border
            if (b[r - 1][j] == 'O') dfs(b, r - 1, j); // Bottom border
        }

        // Step 2: Scan left and right borders
        for (int i = 0; i < r; i++) {
            if (b[i][0] == 'O') dfs(b, i, 0);       // Left border
            if (b[i][c - 1] == 'O') dfs(b, i, c - 1); // Right border
        }

        // Step 3: Flip surrounded 'O's to 'X' and restore 'T's to 'O'
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (b[i][j] == 'O') {
                    b[i][j] = 'X'; // Captured
                } else if (b[i][j] == 'T') {
                    b[i][j] = 'O'; // Safe
                }
            }
        }
    }

    private void dfs(char[][] b, int i, int j) {
        int r = b.length, c = b[0].length;
        
        // Base Cases: Out of bounds or not an unvisited 'O'
        if (i < 0 || i >= r || j < 0 || j >= c || b[i][j] != 'O') {
            return;
        }

        // Mark as safe
        b[i][j] = 'T';

        // Explore all 4 cardinal directions
        dfs(b, i, j + 1); // Right
        dfs(b, i + 1, j); // Down
        dfs(b, i, j - 1); // Left
        dfs(b, i - 1, j); // Up
    }
}