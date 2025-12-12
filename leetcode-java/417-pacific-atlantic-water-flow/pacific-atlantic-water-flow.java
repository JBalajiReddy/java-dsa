class Solution {
    private int[] dX = { -1, 0, 1, 0 }, dY = { 0, 1, 0, -1 };
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean vis[][] = new boolean[m][n];
                if (getReach(heights, i, j, vis) == 3) {
                    List<Integer> rc = new ArrayList<>();
                    rc.add(i);
                    rc.add(j);
                    res.add(rc);
                }
            }
        }
        return res;
    }

    private int getReach(int[][] h, int i, int j, boolean[][] visited) {
        int m = h.length, n = h[0].length;

        if (visited[i][j])
            return 0;
        visited[i][j] = true;

        int status = 0;

        for (int idx = 0; idx < 4; idx++) {
            int r = i + dX[idx];
            int c = j + dY[idx];

            if (r < 0 || c < 0) {
                status |= 1;
            } else if (r >= m || c >= n) {
                status |= 2;
            }
            // If inside grid, check Height + Recursion
            else if (h[i][j] >= h[r][c]) {
                status |= getReach(h, r, c, visited);
            }
        }

        return status;
    }
}