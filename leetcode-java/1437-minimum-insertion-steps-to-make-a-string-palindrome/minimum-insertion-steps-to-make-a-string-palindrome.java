class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n]; //min insertions to make s[i..j] palindrome

        //length based tabulation pattern for strings
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = 0; //mp insertions, already palindrome
                    continue;
                } else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}