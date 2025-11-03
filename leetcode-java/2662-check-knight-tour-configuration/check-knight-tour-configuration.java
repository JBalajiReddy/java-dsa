class Solution {
    public boolean checkValidGrid(int[][] grid) {
        int n = grid.length;
        int dir[][] = { { 2, -1 }, { 2, 1 }, { -2, 1 }, { -2, -1 }, { 1, -2 }, { -1, -2 }, { -1, 2 }, { 1, 2 } }; //{x, y}

        int x = 0, y = 0;
        if (grid[x][y] != 0)
            return false;

        for (int next = 1; next < n * n; next++) {
            boolean found = false;
            for (int d[] : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < n && ny < n && nx >= 0 && ny >= 0 && grid[nx][ny] == next) {
                    x = nx;
                    y = ny;
                    found = true;
                    break;
                }
            }
            if (!found)
                return false;
        }
        return true;
    }
}