class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int s = strs.length;
        int[][][] dp = new int[s + 1][m + 1][n + 1];
        int max = -1;
        for (int i = 1; i <= s; i++) {

            int[] cnts = count(strs[i - 1]);
            int n0 = cnts[0], n1 = cnts[1];

            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int noPick = dp[i - 1][j][k];
                    int pick = 0;
                    if (j >= n0 && k >= n1) {
                        pick = 1 + dp[i - 1][j - n0][k - n1];
                    }
                    dp[i][j][k] = Math.max(pick, noPick);
                }
            }
        }

        return dp[s][m][n];
    }

    private int[] count(String s) {
        int c1 = 0, c0 = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '1') c1++;
            else c0++;
        }
        return new int[] { c0, c1 };
    }
}