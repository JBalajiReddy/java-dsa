class NumMatrix {
    int[][] pfx;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int m = matrix.length, n = matrix[0].length;
        pfx = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int left = (j > 0) ? pfx[i][j - 1] : 0;
                int top = (i > 0) ? pfx[i - 1][j] : 0;
                int topLeft = (i > 0 && j > 0) ? pfx[i - 1][j - 1] : 0;
                pfx[i][j] = matrix[i][j] + left + top - topLeft;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int total = pfx[row2][col2];
        int left = (col1 > 0) ? pfx[row2][col1 - 1] : 0;
        int top = (row1 > 0) ? pfx[row1 - 1][col2] : 0;
        int topLeft = (row1 > 0 && col1 > 0) ? pfx[row1 - 1][col1 - 1] : 0;

        return total - left - top + topLeft;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */