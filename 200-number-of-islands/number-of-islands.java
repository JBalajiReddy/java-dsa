class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    recIslands(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void recIslands(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '0';

        recIslands(grid, i + 1, j);
        recIslands(grid, i, j + 1);
        recIslands(grid, i - 1, j);
        recIslands(grid, i, j - 1);

        // int dx[] = {-1, 0, 1, 0};
        // int dy[] = {0, -1, 0, 1};
        // for(int idx = 0; idx < 4; idx++){
        // recIslands(grid, i + dx[idx], j + dy[idx]);
        // }

    }
}