// class Solution {
//     public void rotate(int[][] matrix) {
//         int n = matrix.length;
//         for (int i = 0; i < (n + 1) / 2; i++) {
//             for (int j = 0; j < n / 2; j++) {
//                 //4-way swap
//                 //bottom left
//                 int temp = matrix[n - 1 - j][i];
//                 //bottom left <- bottom right
//                 matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
//                 //bottom right <- top right
//                 matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
//                 //top right <- top left
//                 matrix[j][n - 1 - i] = matrix[i][j];
//                 //top left <- temp
//                 matrix[i][j] = temp;
//             }
//         }
//     }
// }



//90 = transpose + reverse row
// 180 = reverse row + reverse column
// 270 = transpose + reverse col

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
}