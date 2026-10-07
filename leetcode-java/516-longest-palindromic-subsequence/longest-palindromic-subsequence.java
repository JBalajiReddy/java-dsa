class Solution1 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        StringBuilder sb = new StringBuilder(s);
        String revS = sb.reverse().toString();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == revS.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }
}

class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Process starting index i from bottom to top
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1; // Base case: single character is length 1

            // Process ending index j from i+1 to end
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // Match: add 2 to the inner substring's LPS length
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    // Mismatch: take max of excluding left or right character
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        // Entire string s[0 ... n-1]
        return dp[0][n - 1];
    }
}