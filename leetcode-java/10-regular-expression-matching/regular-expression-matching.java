class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        // dp[i][j] means s[0...i-1] matches p[0...j-1]
        boolean[][] dp = new boolean[n + 1][m + 1];

        // Base Case: Empty string matches empty pattern
        dp[0][0] = true;

        // Handling patterns that match empty strings (e.g., "a*", "a*b*")
        // We look at row 0 (empty text).
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j - 1) == '*') {
                // If we see *, we can ignore the star and the char before it.
                // So dp[0][j] depends on dp[0][j-2].
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // Note: j is 1-based length, so p.charAt(j-1) is the current char
                
                if (p.charAt(j - 1) == '*') {
                    // --- Star Case ---
                    // Option 1: No Take (0 occurrences)
                    // Look back 2 columns in the pattern (skip 'char' and '*')
                    boolean no_take = dp[i][j - 2];

                    // Option 2: Take (1 or more occurrences)
                    // The char before * (p.charAt(j-2)) must match s.charAt(i-1)
                    char patternChar = p.charAt(j - 2);
                    boolean match = (patternChar == s.charAt(i - 1) || patternChar == '.');
                    // If they match, we check the cell directly above (dp[i-1][j])
                    // because we consume a text char but keep the pattern char.
                    boolean take = match && dp[i - 1][j];

                    dp[i][j] = no_take || take;
                } else {
                    // --- Regular Case ---
                    boolean match = (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                    // If chars match, look at the diagonal (prev text, prev pattern)
                    dp[i][j] = match && dp[i - 1][j - 1];
                }
            }
        }

        return dp[n][m];
    }
}


// class Solution {
//     private int n, m;
//     int[][] memo;
    
//     public boolean isMatch(String s, String p) {
//         n = s.length();
//         m = p.length();
//         memo = new int[n + 1][m + 1]; 

//         return solve(0, 0, s, p);
//     }

//     private boolean solve(int i, int j, String s, String p) {
//         if (j == m) {
//             return i == n; 
//         }

//         if (memo[i][j] != 0) {
//             return memo[i][j] == 1;
//         }

//         boolean match = ((i < n) && ((s.charAt(i) == p.charAt(j)) || (p.charAt(j) == '.')));

//         if (j + 1 < m && p.charAt(j + 1) == '*') {
//             boolean no_take = solve(i, j + 2, s, p);
//             boolean take    = match && solve(i + 1, j, s, p);
//             return (memo[i][j] = (take || no_take) ? 1 : -1) == 1;
//         } 

//         return (memo[i][j] = (match && solve(i + 1, j + 1, s, p)) ? 1 : -1) == 1;
//     }
// }