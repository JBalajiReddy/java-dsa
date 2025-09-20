class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int row = 0, res = 0;
        int[] arr = new int[2];
        for (int i = 0; i < mat.length; i++) {
            int cnt = 0;
            for (int j = 0; j < mat[i].length; j++) {
                cnt += mat[i][j];
            }
            if (cnt > res) {
                res = cnt;
                arr[0] = i;
                arr[1] = cnt;
            }
        }
        return arr;
    }
}