class Solution {
    public int uniquePaths(int m, int n) {
        int[] prev = new int[n];
        
        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) curr[j] = 1;
                else {
                    int down = 0;
                    int right = 0;
                    if (i > 0) down = prev[j];
                    if (j > 0) right = curr[j - 1];
                    curr[j] = down + right;
                }
            }
            prev = curr;
        }
        return prev[n - 1];
    }
}

// class Solution {
//     public int uniquePaths(int m, int n) {
//         int[][] dp = new int[m + 1][n + 1];
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (i == 0 && j == 0) dp[i][j] = 1;
//                 else {
//                     int left = 0, up = 0;
//                     if (i > 0) up = dp[i - 1][j];
//                     if (j > 0) left = dp[i][j - 1];
//                     dp[i][j] = left + up;
//                 }
//             }
//         }
//         return dp[m - 1][n - 1];
//     }
// }