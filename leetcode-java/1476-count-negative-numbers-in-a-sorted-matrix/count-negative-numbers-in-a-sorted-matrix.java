class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        int row = m - 1, col = 0;
        while (row >= 0 && col < n) {
            int ele = grid[row][col];
            if (ele < 0) {
                cnt += (n - col);
                row--;
            } else {
                col++;
            }
        } 
        return cnt;
    }
}

// class Solution {
//     public int countNegatives(int[][] grid) {
//         int m = grid.length, n = grid[0].length;
//         int cnt = 0;
//         for (int[] row : grid) {
//             int low = 0;
//             int high = n - 1;
//             while (low <= high) {
//                 int mid = low + (high - low) / 2; 
//                 if (row[mid] >= 0) {
//                     low = mid + 1;
//                 } else {
//                     high = mid - 1; 
//                 }
//             }
//             cnt += (n - low); 
//         }
//         return cnt;
//     }
// }