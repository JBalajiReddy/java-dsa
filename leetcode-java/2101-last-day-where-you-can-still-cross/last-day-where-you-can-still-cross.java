class Solution {
    public int latestDayToCross(int row, int col, int[][] cells) {
        int low = 1, high = cells.length;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canReach(mid, row, col, cells)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean canReach(int day, int row, int col, int[][] cells) {
        int[][] grid = new int[row][col];
        for (int i = 0; i <= day; i++) {
            int[] cell = cells[i];
            int r = cell[0] - 1;
            int c = cell[1] - 1;
            grid[r][c] = 1;
        }
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[row][col];
        for (int i = 0; i < col; i++) {
            if (grid[0][i] == 0) {
                q.add(new int[] { 0, i });
                vis[0][i] = true;
            }
        }

        int[] dX = { -1, 0, 1, 0 }, dY = { 0, 1, 0, -1 };
        while (!q.isEmpty()) {
            int[] rc = q.poll();
            int r = rc[0], c = rc[1];
            for (int i = 0; i < 4; i++) {
                int X = r + dX[i], Y = c + dY[i];
                if (X < 0 || X >= row || Y < 0 || Y >= col || vis[X][Y] == true || grid[X][Y] == 1) {
                    continue;
                }

                vis[X][Y] = true;
                if (X == row - 1)
                    return true;
                q.add(new int[]{ X, Y });
            }
        }
        return false;
    }
}