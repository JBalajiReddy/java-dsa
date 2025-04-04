//bottom-up
class Solution {
    public int longestPalindromeSubseq(String s) {
        StringBuilder str = new StringBuilder(s);
        return longestPalindrome(s, str.reverse().toString());
    }

    public int longestPalindrome(String s, String rS) {
        int n = s.length();
        int dp[][] = new int[n + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s.charAt(i - 1) == rS.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }
}

// top - down
// class Solution {
//     public int longestPalindromeSubseq(String s) {
//         int n = s.length();
//         int[][] dp = new int[n][n];
//         return longestPalindrome(s, 0, n - 1, dp);
//     }

//     public int longestPalindrome(String s, int start, int end, int[][] dp) {

//         if (start > end){
//             return 0;
//         }
//         if(start == end){
//             return 1;
//         }
//         if(dp[start][end] != 0) return dp[start][end];

//         if (s.charAt(start) == s.charAt(end)) {
//            dp[start][end] = 2 + longestPalindrome(s, start + 1, end - 1, dp);
//         } else {
//            dp[start][end] = Math.max(longestPalindrome(s, start + 1, end, dp), longestPalindrome(s, start, end - 1, dp));
//         }
//         return dp[start][end];
//     }
// }