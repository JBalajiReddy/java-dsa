class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return n + m - 2 * dp[n][m];
    }
}

// Deletions from word1 = length(word1) - length(LCS)
// Deletions from word2 = length(word2) - length(LCS)