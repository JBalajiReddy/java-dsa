class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int columnBegin = 0;
        int columnEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && columnBegin <= columnEnd) {
            //traverse right
            for (int j = columnBegin; j <= columnEnd; j++) {
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;

            //traverse down
            for (int i = rowBegin; i <= rowEnd; i++) {
                res.add(matrix[i][columnEnd]);
            }
            columnEnd--;

            //traverse left
            if (rowBegin <= rowEnd) {
                for (int j = columnEnd; j >= columnBegin; j--) {
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            //traverse up
            if (columnBegin <= columnEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    res.add(matrix[i][columnBegin]);
                }
            }
            columnBegin++;
        }
        return res;
    }
}