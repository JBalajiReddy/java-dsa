class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int smallest = Integer.MAX_VALUE, cnt = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int val = matrix[i][j];
                if (val < 0) cnt++;
                int posVal = Math.abs(val);
                sum += posVal;
                smallest = Math.min(smallest, posVal);
            }
        }
        return (cnt % 2 == 0) ? sum : sum - 2 * smallest;
    }
}