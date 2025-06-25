class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] r : matrix) {
            for (int i = 0; i < r.length; i++) {
                if (r[0] > target)
                    continue;
                if (r[0] <= target && r[r.length - 1] >= target && r[i] == target)
                    return true;
            }
        }
        return false;
    }
}