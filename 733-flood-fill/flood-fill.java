class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int nColor = image[sr][sc];
        if (nColor != color) {
            dfs(image, sr, sc, color, nColor);
        }
        return image;
    }

    public void dfs(int[][] image, int r, int c, int req_color, int nColor) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != nColor)
            return;

        image[r][c] = req_color;

        int[] dRow = { -1, 0, +1, 0 };
        int[] dCol = { 0, +1, 0, -1 };
        for (int i = 0; i < 4; i++)
            dfs(image, r + dRow[i], c + dCol[i], req_color, nColor);
    }
}
