class Solution {
    int n, m;

    public int numMagicSquaresInside(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        if (n < 3 || m < 3)
            return 0;
        int cnt = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < m - 2; j++) {
                if (isMagic(grid, i, j))
                    cnt++;
            }
        }
        return cnt;
    }

    private boolean isMagic(int[][] grid, int r, int c) {
        Set<Integer> set = new HashSet<>();
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                int val = grid[i][j];
                if (val > 9 || val < 1 || !set.add(val)) 
                    return false;
            }
        }

        //Rows
        if (grid[r][c] + grid[r][c + 1] + grid[r][c + 2] != 15)
            return false;
        if (grid[r + 1][c] + grid[r + 1][c + 1] + grid[r + 1][c + 2] != 15)
            return false;
        if (grid[r + 2][c] + grid[r + 2][c + 1] + grid[r + 2][c + 2] != 15)
            return false;

        //Columns
        if (grid[r][c] + grid[r + 1][c] + grid[r + 2][c] != 15)
            return false;
        if (grid[r][c + 1] + grid[r + 1][c + 1] + grid[r + 2][c + 1] != 15)
            return false;
        if (grid[r][c + 2] + grid[r + 1][c + 2] + grid[r + 2][c + 2] != 15)
            return false;

        //Diagonals
        if (grid[r][c] + grid[r + 1][c + 1] + grid[r + 2][c + 2] != 15)
            return false;
        if (grid[r][c + 2] + grid[r + 1][c + 1] + grid[r + 2][c] != 15)
            return false;

        return true;
    }
}