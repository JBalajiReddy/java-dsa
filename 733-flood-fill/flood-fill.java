class Solution {
    public int[][] floodFill(int[][] image, int startRow, int startCol, int newColor) {
        int originalColor = image[startRow][startCol];
        if (originalColor != newColor) {
            dfs(image, startRow, startCol, newColor, originalColor);
        }
        return image;
    }

    public void dfs(int[][] image, int row, int col, int newColor, int originalColor) {
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length || image[row][col] != originalColor) {
            return;
        }

        image[row][col] = newColor;

        int[] dRow = { -1, 0, +1, 0 };
        int[] dCol = { 0, +1, 0, -1 };
        for (int i = 0; i < 4; i++) {
            dfs(image, row + dRow[i], col + dCol[i], newColor, originalColor);
        }
    }
}