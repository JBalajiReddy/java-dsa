class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstRowZero = false, firstColumnZero = false;

        //set markers in first row-column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0)
                        firstRowZero = true;
                    if (j == 0)
                        firstColumnZero = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        //replace inner matrix
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0)
                    matrix[i][j] = 0;
            }
        }

        //last remaining checks
        if (firstRowZero) {
            for (int j = 0; j < n; j++)
                matrix[0][j] = 0;
        }

        if (firstColumnZero) {
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        }
    }
}