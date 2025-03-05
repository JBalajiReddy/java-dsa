class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int nColor = image[sr][sc];
        if(nColor != color) {
            dfs(image, sr, sc, color, nColor);
        }
        return image;
    }

    public void dfs(int[][] image, int r, int c, int req_color, int nColor) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != nColor)
            return;

        image[r][c] = req_color;

        dfs(image, r + 1, c, req_color, nColor);
        dfs(image, r - 1, c, req_color, nColor);
        dfs(image, r, c + 1, req_color, nColor);
        dfs(image, r, c - 1, req_color, nColor);
    }
}