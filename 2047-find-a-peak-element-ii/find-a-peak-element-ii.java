class Solution {
    private int findRowWithMaxElement(int[][] mat, int m, int n, int col) {
        int mxVal = -1, idx = 0;
        for (int i = 0; i < m; i++) {
            if (mat[i][col] > mxVal) {
                mxVal = mat[i][col];
                idx = i;
            }
        }
        return idx;
    }

    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) >> 1; //guessed-col
            int row = findRowWithMaxElement(mat, m, n, mid);
            int curr = mat[row][mid]; //max element in the guessed-col
            int left = mid - 1 >= 0 ? mat[row][mid - 1] : -1;
            int right = mid + 1 < n ? mat[row][mid + 1] : -1;
            if (curr > left && curr > right) {
                return new int[] { row, mid };
            } else if (curr < left) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[] { -1, -1 };
    }
}