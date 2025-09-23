class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // DP table sized for 0-based indices i and j
        Boolean[][] dp = new Boolean[m][n];
        return f(m - 1, n - 1, s, p, dp);
    }

    private boolean f(int i, int j, String s, String p, Boolean[][] dp) {
        // Base case: Both string and pattern are exhausted
        if (i < 0 && j < 0) return true;
        
        // Base case: Pattern is exhausted, but string is not
        if (i >= 0 && j < 0) return false;

        // Base case: String is exhausted, but pattern is not.
        // This is the part that was corrected.
        if (i < 0 && j >= 0) {
            // Check if the remaining pattern can match an empty string
            for (int k = 0; k <= j; k++) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        }

        // Memoization check
        if (dp[i][j] != null) return dp[i][j];

        // Case 1: Characters match or pattern has '?'
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            return dp[i][j] = f(i - 1, j - 1, s, p, dp);
        }
        
        // Case 2: Pattern has '*'
        if (p.charAt(j) == '*') {
            // Two possibilities for '*':
            // 1. '*' matches one character in s --> f(i - 1, j, ...)
            // 2. '*' matches an empty sequence --> f(i, j - 1, ...)
            return dp[i][j] = f(i - 1, j, s, p, dp) || f(i, j - 1, s, p, dp);
        }
        
        // Case 3: Mismatch
        return false;
    }
}