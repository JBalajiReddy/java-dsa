class Solution {
    int n, m, r;
    String s1, s2, s3;

    public boolean isInterleave(String s1, String s2, String s3) {
        n = s1.length();
        m = s2.length();
        r = s3.length();
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        if (m + n != r)
            return false;

        Boolean[][] dp = new Boolean[n + 1][m + 1];
        return solve(0, 0, dp);
    }

    private boolean solve(int i, int j, Boolean[][] dp) {
        if (i == n && j == m && i + j == r)
            return true;
        if (i + j >= r)
            return false;
        if (dp[i][j] != null)
            return dp[i][j];
        boolean res = false;
        if (i < n && s1.charAt(i) == s3.charAt(i + j))
            res = i < n && solve(i + 1, j, dp);

        if (res == true) {
            return dp[i][j] = res;
        }

        if (j < m && s2.charAt(j) == s3.charAt(i + j))
            res = j < m && solve(i, j + 1, dp);
        return dp[i][j] = res;
    }
}