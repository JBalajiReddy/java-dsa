class Solution {
    private int n;
    public boolean findRotation(int[][] mat, int[][] target) {
        n = mat.length;
        for (int cnt = 1; cnt <= 4; cnt++) {
            boolean equal = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] != target[i][j]) {
                        equal = false;
                        break;
                    }
                }
                if (!equal) {
                    break;
                }
            }
            if (equal) {
                return true;
            }
            rotate(mat);
        }
        return false;
    }

    private void rotate(int[][] mat) {
        //transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int t = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = t;
            }
        }

        //reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int t = mat[i][left];
                mat[i][left] = mat[i][right];
                mat[i][right] = t;

                left++;
                right--;
            }
        }
    }
}