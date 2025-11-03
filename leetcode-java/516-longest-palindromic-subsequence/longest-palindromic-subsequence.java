class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        StringBuilder s1 = new StringBuilder(s);
        String rs = s1.reverse().toString();
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == rs.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    int c1 = dp[i - 1][j];
                    int c2 = dp[i][j - 1];
                    dp[i][j] = Math.max(c1, c2);
                }
            }
        }
        return dp[n][n];
    }
}