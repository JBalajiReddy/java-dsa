class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n]; //LPS length of s[i..j]

        //length based tabulation pattern for strings
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = 1;
                    continue;
                } else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}

class Solution_LCS {
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

class Solution_topDown {
    int[][] t = new int[1001][1001];

    public int LPS(String s, int i, int j) {
        if (i > j)
            return 0;
        if (i == j)
            return 1;

        if (t[i][j] != -1)
            return t[i][j];
        if (s.charAt(i) == s.charAt(j))
            return t[i][j] = 2 + LPS(s, i + 1, j - 1);
        else
            return t[i][j] = Math.max(LPS(s, i + 1, j), LPS(s, i, j - 1));
    }

    public int longestPalindromeSubseq(String s) {
        int m = s.length();
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        return LPS(s, 0, m - 1); // Approach-1
    }
}