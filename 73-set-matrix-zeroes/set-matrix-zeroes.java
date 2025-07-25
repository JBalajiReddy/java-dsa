class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    if (r == 0) 
                       firstRowZero = true;
                    if (c == 0) 
                       firstColZero = true;
                    matrix[r][0] = 0;
                    matrix[0][c] = 0;
                }
            }
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (matrix[r][0] == 0 || matrix[0][c] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }
        
        if (firstRowZero) {
            for (int c = 1; c < cols; c++) {
                matrix[0][c] = 0;
            }
        }

        if (firstColZero) {
            for (int r = 1; r < rows; r++) {
                matrix[r][0] = 0;
            }
        }
    }
}