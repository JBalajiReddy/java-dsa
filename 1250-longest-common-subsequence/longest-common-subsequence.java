class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int dp[][] = new int[n + 1][m + 1];
        dp[0][0] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    int case1 = dp[i - 1][j];
                    int case2 = dp[i][j - 1];
                    dp[i][j] = Math.max(case1, case2);
                }
            }
        }
        return dp[n][m];
    }
}




// class Solution {
//     public int longestCommonSubsequence(String text1, String text2) {
//         int m = text1.length();
//         int n = text2.length();
//         int[][] dp = new int[m + 1][n + 1];
//         dp[0][0] = 0;
//         for (int i = 1; i <= m; i++) {
//             for (int j = 1; j <= n; j++) {
//                 if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
//                     dp[i][j] = dp[i - 1][j - 1] + 1;
//                 } else
//                     case 1 : Excluding the current character of text1: dp[i - 1][j]
//                     case 2 :  Excluding the current character of text2: dp[i][j - 1]
//                     dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
//             }
//         }
//         return dp[m][n];
//     }
// }

// TLE
// class Solution {
// public int longestCommonSubsequence(String text1, String text2) {
// int m = text1.length();
// int n = text2.length();
// int[][] dp = new int[m + 1][n + 1];
// return recurLCS(text1, text2, m, n, dp);
// }
// public int recurLCS(String a, String b, int m, int n, int[][] dp){
// if(dp[m][n] != 0) return dp[m][n];
// if(m == 0 || n == 0) return 0;

// //check from last indices add 1 to res if matched
// if(a.charAt(m - 1) == b.charAt(n - 1)){
// return dp[m][n] = 1 + recurLCS(a, b, m - 1, n - 1, dp);
// }
// return dp[m][n] = Math.max(recurLCS(a, b, m - 1, n, dp), recurLCS(a, b, m, n
// - 1, dp));
// }
// }