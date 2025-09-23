class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();

        // Use primitive boolean, defaults to false
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base Case 1: Empty string vs Empty pattern
        dp[0][0] = true;

        // Base Case 2: Non-empty string vs Empty pattern (already false by default)
        // for (int i = 1; i <= m; i++) {
        //     dp[i][0] = false;
        // }

        // Base Case 3: Empty string vs Non-empty pattern
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if (p.charAt(j - 1) == '*') {
                    // 1. '*' matches an empty sequence (dp[i][j-1])
                    // 2. '*' matches one or more chars (dp[i-1][j])
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
                // Case 3: Mismatch (already false by default)
                // else {
                //     dp[i][j] = false;
                // }
            }
        }
        return dp[m][n];
    }
}

// class Solution {
//     public boolean isMatch(String s, String p) {
//         int m = s.length(), n = p.length();
//         // DP table sized for 0-based indices i and j
//         Boolean[][] dp = new Boolean[m][n];
//         return f(m - 1, n - 1, s, p, dp);
//     }

//     private boolean f(int i, int j, String s, String p, Boolean[][] dp) {
//         // Base case: Both string and pattern are exhausted
//         if (i < 0 && j < 0) return true;

//         // Base case: Pattern is exhausted, but string is not
//         if (i >= 0 && j < 0) return false;

//         // Base case: String is exhausted, but pattern is not.
//         // This is the part that was corrected.
//         if (i < 0 && j >= 0) {
//             // Check if the remaining pattern can match an empty string
//             for (int k = 0; k <= j; k++) {
//                 if (p.charAt(k) != '*') {
//                     return false;
//                 }
//             }
//             return true;
//         }

//         // Memoization check
//         if (dp[i][j] != null) return dp[i][j];

//         // Case 1: Characters match or pattern has '?'
//         if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
//             return dp[i][j] = f(i - 1, j - 1, s, p, dp);
//         }

//         // Case 2: Pattern has '*'
//         if (p.charAt(j) == '*') {
//             // Two possibilities for '*':
//             // 1. '*' matches one character in s --> f(i - 1, j, ...)
//             // 2. '*' matches an empty sequence --> f(i, j - 1, ...)
//             return dp[i][j] = f(i - 1, j, s, p, dp) || f(i, j - 1, s, p, dp);
//         }

//         // Case 3: Mismatch
//         return false;
//     }
// }